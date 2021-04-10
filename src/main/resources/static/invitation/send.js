// sendInvitation: 이메일 전달 요청
function sendInvitation() {
    let receiverEmail = $('#receiver-email').val();
    let data = {'to_email': receiverEmail};

    $.ajax({
        type: 'POST',
        url: '/api/sendInvitation',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            alert('성공적으로 초대장을 보냈습니다.');
        }
    });

}