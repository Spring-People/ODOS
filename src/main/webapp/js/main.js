let todayProblemContainer;
let groupId;
let clockShow = true;
let check;
let todayProblemIdx;
let uploadTime;
let nowTotal;
let destTotal;

$(document).ready(function() {
    todayProblemContainer = $('#problem-container');
    todayProblemContainer.hide();
    // $('demo').hide();
    setTodayProblem();
});

function setCheck(val) {
    check = val;
}

function setClockShow(val) {
    if (val === true) clockShow = true;
    else clockShow = false;
}

function getClockShow() { return clockShow; }

function setTodayProblem() {
    let problems;
    let problemListLength;

    $.ajax({
        type: 'GET',
        url: '/api/getProblems',
        success: function (response) {
            console.log("time" + uploadTime);
            problems = response;
            console.log(problems);
            groupId = problems[0].groupId;
            console.log('그룹 아이디' + groupId);

            todayProblemIdx = getTodayProblemIdx(problems);
            problemListLength = problems.length;

            if (todayProblemIdx > problemListLength) {
                // 1. 문제 다 풀었을 때
                console.log('case 1');
                todayProblemContainer.hide();
                showTime();
            } else if (todayProblemIdx === problemListLength) {
                // 2. 문제 안 밀렸을 때
                console.log('case 2');

                setProblem(problems,todayProblemIdx - 1);
                checkLimit();

                if (nowTotal < destTotal) {
                    console.log('시간 보기');
                    showTime();
                } else {
                    $('demo').hide();
                    todayProblemContainer.show();
                }
            } else {
                console.log('case 3');

                // 3. 문제 밀렸을 때
                setProblem(problems, todayProblemIdx - 1);
                todayProblemContainer.show();
                checkLimit();

            }
        }
    });
}

function checkLimit() {
    $.ajax({
        type: 'GET',
        url: '/api/getTeamInfo/'+groupId,
        success: function (response) {
            console.log(response);
            let solveLimit = response.solveLimit;

            if (solveLimit == 2) {
                console.log("오늘 2문제 다 풀었음");
                showTime();
                todayProblemContainer.hide();
            } else {
                todayProblemIdx += 1;
                //setTodayProblem();
            }
        }
    });
}

function setProblem(problems, idx) {
    let todayProblem = problems[idx];
    console.log('오늘의 문제' + todayProblem);
    let problemId = todayProblem.id;
    console.log(problemId);
    $('#today-problem-link').attr('href', '/problem/detail/'+problemId);

}

function getTodayProblemIdx(problems) {
    let i = 0;
    for (; i<problems.length; i++) {
        if (problems[i].solved != -1)
            return i + 1;
    }
    return i + 1;
}

function setUploadTime(time) {
    uploadTime = time;
    //getTime();
    var now = new Date();
    var nowHour = now.getHours();
    var nowMin = now.getMinutes();
    var nowSec = now.getSeconds();
    nowTotal = (nowHour * 3600) + (nowMin*60) + nowSec;

    var dest = uploadTime;
    console.log('dest' + dest);
    var destHour = dest.substr(0,2);
    var destMin = dest.substr(3,2);
    destTotal = (destHour * 3600) + (destMin*60);
}

function showTime() {
    console.log('getTime');


    var Hour;
    var Min;
    var Sec;
    var Check;

    if(nowTotal > destTotal)
        destTotal += (24*3600);
    Check = destTotal - nowTotal;

    var s = setInterval(function (){

        Hour = Check/3600;
        Hour = Math.floor(Hour);
        Min = (Check%3600)/60;
        Min = Math.floor(Min);
        Sec = (Check%3600)%60;

        document.getElementById("demo").innerHTML = Hour + "시" + Min + "분" + Sec + "초";

        Check--;
        setCheck(Check);

        if(Check == 0) {
            document.getElementById("demo").innerHTML = "문제가 업로드 되었습니다";
            todayProblemContainer.show();
            clearInterval(s);
        }
    }, 1000);
}