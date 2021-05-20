var hashtags = [];
var hashtagView = document.getElementById("hashtagView");
var hashtagInput = document.getElementById("inputHashtag");

// url 중복확인
function urlCheck (e) {
    // 공백입력시 즉시 제거
    e.value = e.value.replace(/\s/g,'');

    var inputUrl = e.value;
    var urlHelp = document.getElementById("urlHelp");

    // 중복확인 후 url의 사용 가능 여부를 저장, form submit시 확인
    e.setAttribute("data-available", false);

    // 길이제한 위반
    if (inputUrl.length < 5 || 32 < inputUrl.length) {
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
                } else {
                    urlHelp.style.color = 'limegreen';
                    urlHelp.innerHTML = '사용 가능한 url입니다.';
                    e.setAttribute("data-available", true);
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

// 스터디 등록 submit
function submitRegister() {
    var studyForm = document.getElementById("registerForm");

    // 해시태그를 input에 추가
    for (let i = 0; i < hashtags.length; i ++) {
        studyForm.innerHTML += '<input type="hidden" id="hashtagList" name="hashtagList" value="' + hashtags[i] + '">';
    }

    studyForm.setAttribute("action", "/study/register");
    studyForm.setAttribute("method", "POST");
    studyForm.submit();
}
