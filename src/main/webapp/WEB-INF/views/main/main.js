let todayProblemContainer;

$(document).ready(function() {
    todayProblemContainer = $('#problem-container');
    todayProblemContainer.hide();
});

function getTodayProblem() {
    let problems;

    $.ajax({
        type: 'GET',
        url: '/api/getProblems',
        success: function (response) {
            problems = response;
            let todayProblem = problems[0];
            console.log(todayProblem);
            let problemId = todayProblem.id;
            console.log(problemId);
            console.log('/problem/detail/${problemId}'+problemId);
            todayProblemContainer.show();
            $('#today-problem-link').attr('href', '/problem/detail/'+problemId);
        }

    });
}