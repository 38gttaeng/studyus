// 출석체크 전송
function insertAttendance(memberNo, studyNo) {
    $.ajax({
        type: 'GET',
        url: '/attendance/check',
        contentType: 'application/text; charset=UTF-8',
        // 인자
        data: {
            'memberNo': memberNo,
            'studyNo' : studyNo
            // , 구분자로 추가
        },
        dataType: "text",
        success: function(result) {
            if (result == 1) { // 출석체크 성공
                alert('출석이 확인되었습니다.');
            } else if (result == 2) { // 이미 오늘의 출석체크가 됨
                alert('이미 오늘의 출석을 확인하였습니다.');
            }
        },
        error: function(result) {
            alert('출석 확인에 실패하였습니다.');
        },
        complete: function(result) {
            // alert(result);
        }
    });
}