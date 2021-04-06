<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript"><%@include file="main.js" %></script>

    <!-- 추가 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- made CSS -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">

</head>
<script language="JavaScript">



    var now = new Date();
    var nowHour = now.getHours();
    var nowMin = now.getMinutes();
    var nowSec = now.getSeconds();
    var nowTotal = (nowHour * 3600) + (nowMin*60) + nowSec;

    var dest = "${uploadTime}";
    var destHour = dest.substr(0,2);
    var destMin = dest.substr(3,2);
    var destTotal = (destHour * 3600) + (destMin*60);

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

        document.getElementById("demo").innerHTML = Hour + "시간 " + Min + "분 " + Sec + "초";

        Check--;

        if(Check < 0) {
            clearInterval(s);
            document.getElementById("demo").innerHTML = "문제가 업로드 되었습니다";

            getTodayProblem();

        }
    }, 1000);
</script>
<body class="mainbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">
<c:if test="${empty authInfo}">
    <p>환영합니다.</p>
    <p>
        <a href="<c:url value="/register/step1" />">[회원 가입하기]</a>
        <a href="<c:url value="/login" />">[로그인]</a>
    </p>
</c:if>

<c:if test="${! empty authInfo}">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">

        <!-- Links -->
        <ul class="navbar-nav nav-right">
            <li role="menuitem" class="nav-item">
                <a class="nav-link" href="<c:url value="/problemlist" />">문제목록</a>
            </li>
            <li role="menuitem" class="nav-item">
                <a class="nav-link" href="/main/mypage.html">마이페이지</a>
            </li>
            <li role="menuitem" class="nav-item">
                <a class="nav-link" href="<c:url value="/logout" />">로그아웃</a>
            </li>
            <li role="menuitem" class="nav-item">
                <a class="nav-link" href="/main/mypage.html">${authInfo.name}님, 환영합니다.</a>
            </li>
        </ul>
    </nav>

    <div class="jumbotron text-center wait align-middle">
        <h3>문제 업로드 까지 남은 시간</h3>
        <div class="waiting" id = "demo"></div>
        <div class="waiting" id="problem-container">
            <a id="today-problem-link" href="#">오늘의 문제로 이동</a>
        </div>
    </div>



</c:if>

<%@ include file="../bootstrap.jsp" %>
</body>
</html>