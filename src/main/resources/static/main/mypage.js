let currentId;
let teamId;

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/api/getMemberInfo',
        success: function (response) {
            console.log(response);
            currentId = response.id;
            teamId = response.groupId;
            console.log(currentId);
        }
    });
});

function updateReceiveProblemTime() {
    let time = $('#upload-time-select').val();
    let am_pm = $('input[name=am-pm]:checked').val();

    console.log(time);

    console.log(am_pm);

    let data = {'time': time, 'am_pm': am_pm};

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