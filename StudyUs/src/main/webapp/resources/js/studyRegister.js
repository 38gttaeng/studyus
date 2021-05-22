var hashtags = [];
var hashtagView = document.getElementById("hashtagView");
var hashtagInput = document.getElementById("inputHashtag");

// url 중복 여부를 저장, form submit시 확인
var urlAvailable = false;

// url 중복확인
function urlCheck (e) {
    // 공백입력시 즉시 제거
    e.value = e.value.replace(/\s/g,'');

    var inputUrl = e.value;
    var urlHelp = document.getElementById("urlHelp");

    // 중복확인 후 url의 사용 가능 여부를 저장, form submit시 확인
    // urlAvailable = false;

    // 길이제한 위반
    if (inputUrl.length < 4 || 32 < inputUrl.length) {
        urlHelp.innerHTML = '4 ~ 32 자리의 영문 및 숫자만 가능합니다.';
        urlHelp.style.color = 'gray';
    } else {
        $.ajax({
            type: 'POST',
            url: '/study/check/url',
            data: {'inputUrl' : inputUrl},
            dataType: "text",
            success: function(result) {
                if (0 < result) {
                    urlHelp.style.color = 'crimson';
                    urlHelp.innerHTML = '이미 사용중인 url입니다.';
                    urlAvailable = false;
                } else {
                    urlHelp.style.color = 'limegreen';
                    urlHelp.innerHTML = '사용 가능한 url입니다.';
                    urlAvailable = true;
                }
            },
            error: function(result) {
                urlHelp.innerHTML = '서버와의 통신 오류';
            }
        });
    }
}

// 해시태그 추가 버튼
function onAddHashtagClicked() {
    // 입력된 해시태그에서 공백제거 후 저장
    var currentHashtag = hashtagInput.value.replace(/\s/g,'');

    // 해시태그 미입력시 return
    if (currentHashtag == '') {
        return;
    }

    // 이미 입력되었을시 inputfield 초기화 후 return
    if (hashtags.includes(currentHashtag)) {
        hashtagInput.value = '';
        return;
    }
    
    // 해시태그 리스트에 추가
    hashtags.push(currentHashtag);

    // 해시태그 화면에 출력
    hashtagView.innerHTML += '<span class="btn btn-primary btn-sm mr-2" style="margin: 4px;" onclick="onRemoveHashtagClicked(this);">' + currentHashtag + '&nbsp;<i class="fas fa-times"></i></span>';
    
    // input 초기화
    hashtagInput.value = '';
}

// 해시태그 제거
function onRemoveHashtagClicked(e) {
    // 해시태그 리스트에서 e 해시태그 제거
    hashtags.splice(hashtags.indexOf(e.textContent.replace(/\s/g,'')), 1);

    // e 태그를 문서에서 제거
    e.remove();
}

// 활동일시 등록 및 출력
$('#meetingDayModal').on('hidden.bs.modal', function () {
    var meetingDayInputList = document.getElementsByClassName("meetingDayInput");
    var meetingDayView = document.getElementsByClassName("meetingDayView");
    var meetingTimeView = document.getElementById("meetingTimeView");
    var startHour = document.getElementById("start-h");
    var startMinute = document.getElementById("start-m");
    var endHour = document.getElementById("end-h");
    var endMinute = document.getElementById("end-m");

    for (let i = 0; i < meetingDayInputList.length; i ++) {
        if (meetingDayInputList[i].checked) {
            meetingDayView[i].classList.add("btn-primary");
            meetingDayView[i].classList.remove("btn-light");
        } else {
            meetingDayView[i].classList.remove("btn-primary");
            meetingDayView[i].classList.add("btn-light");
        }
    }

    meetingTimeView.innerHTML = startHour.value + " : " + startMinute.value + "  ~  " + endHour.value + " : " + endMinute.value;
});

// 스터디 등록 실행
$('#registerForm').submit(function () {
    if (urlAvailable == false) {
        alert("중복된 URL입니다.");
        return false;
    }
});

//dep 스터디 등록 실행
function submitRegister() {
    var studyForm = document.getElementById("registerForm");
    var inputUrl = document.getElementById("inputUrl");
    var inputName = document.getElementById("inputName");

    // 유효성검사
    if (inputUrl.getAttribute("data-available") == false) {
        alert("중복된 URL입니다.");
        return;
    }

    // 해시태그를 input에 추가
    for (let i = 0; i < hashtags.length; i ++) {
        studyForm.innerHTML += '<input type="hidden" id="hashtagList" name="hashtagList" value="' + hashtags[i] + '">';
    }

    studyForm.setAttribute("action", "/study/register");
    studyForm.setAttribute("method", "POST");
    studyForm.submit();
}
