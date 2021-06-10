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
// 검색 결과를 Json 형태로 저장하는 변수
var studyList = [];
// 모달 활동요일
var meetingDayViewList = document.getElementsByClassName("meetingDayView");
// 현재 페이지
var currentPage = 0;

/**
 * 비동기 로딩 요청이 이미 실행 중일 때 여러번 보내지 않기 위한 변수.
 * loadAvailable이 true인 경우에만 다음 페이지를 비동기 요청.
 * 요청 시작시 false로 변경.
 * 요청된 페이지 로딩 완료 후 true로 변경.
 */
var loadAvailable = true;
/**
 * 검색을 한 번이라도 실행했는지 확인하는 변수.
 * 검색버튼으로 검색을 실행하면 true로 변경.
 * true인 상태에서만 비동기 검색이 실행.
 * 검색결과 마지막 페이지 도달시 false로 변경.
 */
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
        return false;
    }
    
    // 이미 입력되었을시 inputfield 초기화 후 return
    if (hashtags.includes(currentHashtag)) {
        searchInput.value = '';
        return false;
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

    currentPage = 0;

    loadAdditionally();
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
            'page' : currentPage
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
                var sStudyName = result[i].studyName;
                var sIntroduce = result[i].introduce;
                var sHashtags = result[i].hashtagList;
                var filename = result[i].filename;
                var hashtagString = '';

                console.log(result[i].filename);
                console.log(filename);

                if (sHashtags[i] != undefined) {
                    for (var j = 0; j < sHashtags.length; j ++) {
                        hashtagString += "#" + sHashtags[j].name + " ";
                    }
                }

                var studyText = '<div class="study-container col-lg-4 mb-4">' +
                                    '<div class="card h-100" data-toggle="modal" data-target="#exampleModal" onclick="onStudyContainerClicked(' + (i + studyList.length) + ');">' +
                                        '<img src=' + location.protocol + "//" + location.hostname + (location.port ? ':' + location.port: '') + '/resources/studyUploadFiles/' + filename + ' class="card-img-top" alt="...">' +
                                        '<div class="card-body">' +
                                            '<h5 class="card-title study-name">' + sStudyName + '</h5>' +
                                            '<p class="card-text study-introduce">' + sIntroduce + '</p>' +
                                            '<div class="card-text study-hashtags">' + hashtagString + '</div>' +
                                        '</div>' +
                                    '</div> ' +
                                '</div>';
                resultGrid.innerHTML += studyText;
            }
            
            // result를 studyList에 추가
            studyList = studyList.concat(result);

            // 현재 페이지수 1 추가
            currentPage++;
        },
        error: function(result) {
            alert('검색 결과를 불러오지 못했습니다.');
        },
        complete: function(result) {
            loadAvailable = true;
        }
    });
}

// 모달 display
function onStudyContainerClicked(i) {
    var s = studyList[i].start;
    var e = studyList[i].end;
    var studyUrl = studyList[i].url;

    // 스터디명 출력
    document.getElementById("modalLabel").innerHTML = studyList[i].studyName;

    // 스터디 소개 출력
    document.getElementById("study-introduce").innerHTML = studyList[i].introduce;

    // 활동요일 출력
    var meetingDayValues = [];
    meetingDayValues.push(studyList[i].monday);
    meetingDayValues.push(studyList[i].tuesday);
    meetingDayValues.push(studyList[i].wednesday);
    meetingDayValues.push(studyList[i].thursday);
    meetingDayValues.push(studyList[i].friday);
    meetingDayValues.push(studyList[i].saturday);
    meetingDayValues.push(studyList[i].sunday);
    for (var i = 0; i < 7; i ++) {
        if (meetingDayValues[i] == 0) {
            meetingDayViewList[i].classList.add("btn-light");
            meetingDayViewList[i].classList.remove("btn-primary");
        } else if (meetingDayValues[i] == 1) {
            meetingDayViewList[i].classList.remove("btn-light");
            meetingDayViewList[i].classList.add("btn-primary");
        }
    }

    // 활동시간 출력
    document.getElementById("meetingTimeView").innerHTML = (s + ' ~ ' + e);

    // 가입신청 버튼 설정
    document.getElementById("apply-button").setAttribute("onclick", "applyStudy('" + studyUrl + "');");
}

// 모달 가입신청 클릭
function applyStudy(studyUrl) { 
    $.ajax({
        type: 'GET',
        url: '/enrollment/register',
        contentType: 'application/json; charset=UTF-8',
        // 인자
        data: {
            'url': studyUrl,
            'greeting' : document.getElementById("modal-greeting").value
            // , 구분자로 추가
        },
        dataType: "json",
        success: function(result) {
            switch (result) {
                case -1:
                    alert('로그인이 필요합니다.');
                    window.location.href='/member/loginView';
                    break;
                case 0:
                    alert('DB 오류로 인해 실패하였습니다.');
                    break;
                case 1:
                    alert('가입 신청이 완료되었습니다.');
                    break;
                case 2:
                    alert('이미 가입한 스터디입니다.');
                    break;
                case 3:
                    alert('이미 가입신청 후 승인 대기중인 스터디입니다.');
                    break;
            }
            
            // 가입신청 모달 닫기
            $('#exampleModal').modal('hide');
        },
        error: function(result) {
            alert('가입 신청에 실패했습니다.');
        },
        complete: function(result) {
            // 가입신청 모달 초기화
            document.getElementById("modal-greeting").value = '';
            for (var i = 0; i < meetingDayViewList.length; i ++) {
                meetingDayViewList[i].classList.remove("btn-primary");
                meetingDayViewList[i].classList.add("btn-light");
            }
        }
    });
}