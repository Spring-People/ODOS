let currentId;
let teamId;

$(document).ready(function () {
    // 현재 로그인 정보 요청
    $.ajax({
        type: 'GET',
        url: '/api/getMemberInfo',
        success: function (response) {
            currentId = response.id;
            teamId = response.groupId;
        }
    });
});

// updateReceiveProblemTime: 시간 변경 요청
function updateReceiveProblemTime() {
    let new_upload_time = $('#upload-time-select').val();
    let am_pm = $('input[name=am-pm]:checked').val();
    let data = {'time': new_upload_time, 'am_pm': am_pm};

    $.ajax({
        type: "PUT",
        url: `/api/updateTeamUploadTime/${teamId}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.href="/main";
        }
    });



}