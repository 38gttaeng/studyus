// 검색 폼
var searchForm = document.getElementById("searchForm");
// 검색 전 입력한 해시태그 불러오기 및 추가 입력할 해시태그 저장
var hashtags = [];
// 사용자가 입력해둔 해시태그 전체를 출력하는 태그
var hashtagView = document.getElementById("hashtagView");
// 검색어를 입력하는 input태그
var searchInput = document.getElementById("searchInput");
// 작성한 해시태그를 추가하는 버튼
var hashtagButton = document.getElementById("hashtagButton");
// 검색 실행버튼
var searchButton = document.getElementById("searchButton");

/**
 * 비동기 로딩 요청이 이미 실행 중일 때 여러번 보내지 않기 위한 변수입니다.
 * loadAvailable이 true인 경우에만 다음 페이지를 비동기 요청합니다.
 * 요청 시작시 false로 변경됩니다.
 * 요청된 페이지 로딩 완료 후 true로 변경됩니다.
 */
var loadAvailable = true;

// 기존 검색결과 유지
preserveSearchProperties();
function preserveSearchProperties () {
    // 저장된 해시태그가 ""인 경우 return
    if (window.name.replace(/\s/g, '') == "") {
        return false;
    }

    // 해시태그 유지
    hashtags = window.name.split(',');
    // 기존 해시태그 화면에 출력
    for (var i = 0; i < hashtags.length; i ++) {
        hashtagView.innerHTML += '<span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="removeHashtag(this);">' + hashtags[i] + '&nbsp;<i class="fas fa-times"></i></span>';
    }
}

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

    // 브라우저에 해시태그 저장
    window.name = hashtags.toString();

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

    // 브라우저에 변경내용 저장
    window.name = hashtags.toString();

    // e 태그를 문서에서 제거
    e.remove();
}

// 새로운 검색 실행
function onSearch() {
    for (var i = 0; i < hashtags.length; i ++) {
        var newHashtag = document.createElement("input");
        newHashtag.name = "hashtag";
        newHashtag.type = "hidden";
        newHashtag.value = hashtags[i];
        searchForm.appendChild(newHashtag);
    }

    searchForm.setAttribute("action", "/study/search/result");
    searchForm.setAttribute("method", "get");
    searchForm.submit();
}

// 페이지 추가검색을 실행할 지점을 설정
// var end = $("#additionalLoadProperties").offset().top; 
// var viewEnd = $(window).scrollTop() + $(window).height(); 
// var distance = end - viewEnd; 

$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() >= $(document).height()){
        loadAdditionally();
    }
});

function loadAdditionally() {
    if (loadAvailable == false) {
        return false;
    }
    loadAvailable = false;

    $.ajax({
        type: 'GET',
        url: '/study/search/additional',
        // 인자
        data: {
            'keyword' : document.getElementById("searchInput").value,
            'hashtags' : hashtags,
            'page' : document.getElementById("currentPage").value
            // , 구분자로 추가
        },
        dataType: "text",
        success: function(result) {
            // 검색결과 최종 페이지 도달
            if (result.length < 3) {
                document.getElementById("searchGuide").innerHTML = '마지막 검색 결과입니다.';
                loadAvailable = false;
            }

            var result = JSON.parse(result);
            var printArea = document.getElementById("search-result-grid");

            // 검색결과를 출력할 element model
            // var studyContainerModel = document.getElementsByClassName("study-container d-none");

            for (var i = 0; i < result.length; i ++) {
                // var studyContainer = studyContainerModel.clone(false).appendTo("#search-result-grid");
                // studyContainer.classList.remove("d-none");
                // studyContainer.getElementsByClassName("study-name").innerHTML = result[i].studyName;
                // studyContainer.getElementsByClassName("study-introduce").innerHTML = result[i].introduce;
                // studyContainer.getElementsByClassName("study-hashtags").innerHTML = result[i].hashtags;
                // studyContainer.getElementsByClassName("study-url").value = result[i].url;
                var sStudyName = result[i].studyName;
                var sIntroduce = result[i].introduce;
                var sHashtags = result[i].hashtags;
                var sUrl = result[i].url;

                console.log(sHashtags == undefined);
                if (sHashtags == undefined) {
                    sHashtags = 'asdf';
                    console.log(sHashtags);
                }

                var studyText = '<div class="study-container col-lg-4 mb-4">' +
                                    '<div class="card h-100">' +
                                        '<img src="/resources/images/sample1.jpg" class="card-img-top" alt="...">' +
                                        '<div class="card-body">' +
                                            '<h5 class="card-title study-name">' + sStudyName + '</h5>' +
                                            '<p class="card-text study-introduce">' + sIntroduce + '</p>' +
                                            '<div class="card-text study-hashtags">' +
                                                + sHashtags +
                                            '</div>' +
                                        '</div>' +
                                    '</div> ' +
				                    '<input type="hidden" class="url study-url" value="' + sUrl + '">' +
                                '</div>';
                
                console.log(sHashtags);
                printArea.innerHTML += studyText;
            }

            document.getElementById("currentPage").value = parseInt(document.getElementById("currentPage").value, 10) + 1;
        },
        error: function(result) {
            console.log("비동기 검색 error");
        },
        complete: function(result) {
            loadAvailable = true;
            console.log("비동기 검색 완료. loadAvailable: " + loadAvailable);
        }
    });
}