let currentId;
// html 로드 시 바로 실행

$(document).ready(function () {
    let list = $('#invitation-list');

    $.ajax({
        type: 'GET',
        url: '/login/api/receiveLoginInfo',
        success: function (response) {
            currentId = response.id;
            console.log(currentId);
        }
    });

    $.ajax({
        type: 'GET',
        url: '/api/getInvitation',
        success: function (response) {
            console.log(response);
            let itemDto = response;
            // list.append(addHTML(itemDto));
            $('#invitation-exist').removeClass('hide');
            $('#invitation-none').addClass('hide');
            $('#sender-name').text(itemDto.name);
            $('#receive-button').on('click', function() {
                let from_id = itemDto.id;
                let data = {'toId': currentId, 'fromId': from_id}
                $.ajax({
                    type: 'POST',
                    url: '/api/receiveInvitation',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log("메인으로 이동");
                        window.location.href = "/main";
                    }
                });
            });

        }
    });
});




