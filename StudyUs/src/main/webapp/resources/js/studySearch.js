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
    for (var i = 0; i < hashtags.length; i ++) {
        var newHashtag = document.createElement("input");
        newHashtag.name = "hashtag";
        newHashtag.type = "hidden";
        newHashtag.value = hashtags[i];
        searchForm.appendChild(newHashtag);
    }

    window.name = hashtags.toString();

    searchForm.setAttribute("action", "/study/search/result");
    searchForm.setAttribute("method", "get");
    searchForm.submit();
}