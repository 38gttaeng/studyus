// 검색 폼
var searchForm = document.getElementById("searchForm");
// 입력한 해시태그 저장
var hashtags = [];
// 사용자가 입력해둔 해시태그 전체를 출력하는 태그
var hashtagView = document.getElementById("hashtagView");
// 검색어를 입력하는 input태그
var searchInput = document.getElementById("searchInput");
// 작성한 해시태그를 추가하는 버튼
var hashtagButton = document.getElementById("hashtagButton");
// 검색 실행버튼
var searchButton = document.getElementById("searchButton");
// 검색 결과를 출력하는 영역
var resultGrid = document.getElementById("search-result-grid");

/**
 * 비동기 로딩 요청이 이미 실행 중일 때 여러번 보내지 않기 위한 변수입니다.
 * loadAvailable이 true인 경우에만 다음 페이지를 비동기 요청합니다.
 * 요청 시작시 false로 변경됩니다.
 * 요청된 페이지 로딩 완료 후 true로 변경됩니다.
 */
var loadAvailable = true;

var firstSearched = false;

// 엔터키로 submit 제한
$(window).keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
});

function onSearchKeyUp(e) {
    // 공백 제거
    var currentInput = e.value.replace(/\s/g, '');
    // 공백 제거한 값을 value로 설정
    e.value = currentInput;

    // 해시태그 입력중인 경우
    if (currentInput.startsWith("#")) {
        hashtagButton.classList.remove("d-none");
        searchButton.classList.add("d-none");

    // 검색어를 입력중인 경우
    } else {
        hashtagButton.classList.add("d-none");
        searchButton.classList.remove("d-none");
    }
}

// 해시태그 추가 버튼
function onAddHashtag() {
    // 입력된 해시태그에서 공백제거
    var currentHashtag = document.getElementById("searchInput").value.replace(/\s/g,'');
    // (샵)# 제거
    var currentHashtag = currentHashtag.substring(1);
    
    // 해시태그 미입력시 return
    if (currentHashtag == '') {
        return;
    }
    
    // 이미 입력되었을시 inputfield 초기화 후 return
    if (hashtags.includes(currentHashtag)) {
        searchInput.value = '';
        return;
    }
    
    // 해시태그 리스트에 추가
    hashtags.push(currentHashtag);

    // 해시태그 화면에 출력
    hashtagView.innerHTML += '<span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="removeHashtag(this);">' + currentHashtag + '&nbsp;<i class="fas fa-times"></i></span>';
    
    // input 초기화
    searchInput.value = '';

    // 검색 버튼이 보이게 설정
    hashtagButton.classList.add("d-none");
    searchButton.classList.remove("d-none");
}

// 해시태그 제거
function removeHashtag(e) {
    // 해시태그 리스트에서 e 해시태그 제거
    hashtags.splice(hashtags.indexOf(e.textContent.replace(/\s/g,'')), 1);

    // e 태그를 문서에서 제거
    e.remove();
}

// 검색 실행
function onSearch() {
    resultGrid.innerHTML = '';

    for (var i = 0; i < hashtags.length; i ++) {
        var newHashtag = document.createElement("input");
        newHashtag.name = "hashtag";
        newHashtag.type = "hidden";
        newHashtag.value = hashtags[i];
        searchForm.appendChild(newHashtag);
    }

    firstSearched = true;

    document.getElementById("currentPage").value = 0;

    loadAdditionally();

    // searchForm.setAttribute("action", "/study/search/result");
    // searchForm.setAttribute("method", "get");
    // searchForm.submit();
}

$(window).scroll(function() {
    // 검색을 한 번이라도 실행한 이후에 스크롤 감지
    if (firstSearched == false) {
        return false;
    }

    if($(window).scrollTop() + $(window).height() >= $(document).height()){
        loadAdditionally();
    }
});

function loadAdditionally() {
    if (loadAvailable == false || firstSearched == false) {
        return false;
    }
    loadAvailable = false;

    $.ajax({
        type: 'GET',
        url: '/study/search/additional',
        contentType: 'application/json; charset:UTF-8',
        // 인자
        data: {
            'keyword' : document.getElementById("searchInput").value,
            'hashtags' : hashtags,
            'page' : document.getElementById("currentPage").value
            // , 구분자로 추가
        },
        dataType: "json",
        success: function(result) {
            // 검색결과 최종 페이지 도달
            if (result.length < 3) {
                document.getElementById("searchGuide").innerHTML = '마지막 검색 결과입니다.';
                firstSearched = false;
            }

            for (var i = 0; i < result.length; i ++) {
                // var studyContainer = studyContainerModel.clone(false).appendTo("#search-result-grid");
                // studyContainer.classList.remove("d-none");
                // studyContainer.getElementsByClassName("study-name").innerHTML = result[i].studyName;
                // studyContainer.getElementsByClassName("study-introduce").innerHTML = result[i].introduce;
                // studyContainer.getElementsByClassName("study-hashtags").innerHTML = result[i].hashtags;
                // studyContainer.getElementsByClassName("study-url").value = result[i].url;
                var sStudyName = result[i].studyName;
                var sIntroduce = result[i].introduce;
                var sHashtags = result[i].hashtagList;
                var hashtagString = '';
                var sUrl = result[i].url;

                if (sHashtags[i] != undefined) {
                    for (var j = 0; j < sHashtags.length; j ++) {
                        hashtagString += "#" + sHashtags[j].name + " ";
                    }
                }

                var studyText = '<div class="study-container col-lg-4 mb-4">' +
                                    '<div class="card h-100" data-toggle="modal" data-target="#exampleModal" onclick="onStudyContainerClicked(this);">' +
                                        '<img src="/resources/images/sample1.jpg" class="card-img-top" alt="...">' +
                                        '<div class="card-body">' +
                                            '<h5 class="card-title study-name">' + sStudyName + '</h5>' +
                                            '<p class="card-text study-introduce">' + sIntroduce + '</p>' +
                                            '<div class="card-text study-hashtags">' + hashtagString + '</div>' +
                                        '</div>' +
                                    '</div> ' +
				                    '<input type="hidden" class="url study-url" value="' + sUrl + '">' +
                                '</div>';
                resultGrid.innerHTML += studyText;
            }

            document.getElementById("currentPage").value = parseInt(document.getElementById("currentPage").value, 10) + 1;
        },
        error: function(result) {
            alert('검색 결과를 불러오지 못했습니다.');
        },
        complete: function(result) {
            loadAvailable = true;
        }
    });
}

function onStudyContainerClicked(e) {
    console.log(e.getElementsByClassName('study-name').innerHTML);
}