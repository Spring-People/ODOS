let currentId;

// html 로드 시 바로 실행
/*
$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/api/getInfo',
        success: function (response) {
            console.log(response);
            currentId = response.id;
        }
    });
});
*/

function sendInvitation() {
    let email = $('#receiver-email').val();
    console.log("receiver", email);
    // console.log("sender Id", currentId);

    let data = {'to_email': email};

    $.ajax({
        type: 'POST',
        url: '/api/sendInvitation',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response);
            console.log("모달 띄우");
        }
    });

}