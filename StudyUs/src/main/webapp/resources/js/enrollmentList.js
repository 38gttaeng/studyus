function updateEnrollment(enrollmentNo, status) {
    $.ajax({
        type: 'GET',
        url: '/enrollment/modify/status',
        contentType: 'application/text; charset=UTF-8',
        // 인자
        data: {
            'enrollmentNo': enrollmentNo,
            'status': status
            // , 구분자로 추가
        },
        dataType: "json",
        success: function(result) {
            location.reload();
        },
        error: function(result) {
            alert('서버와의 연결에 실패했습니다.');
        },
        complete: function(result) {
            console.log("신청서 비동기 수정 종료");
        }
    });
}