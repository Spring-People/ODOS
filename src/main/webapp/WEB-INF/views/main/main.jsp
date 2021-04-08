<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js" charset="UTF-8"></script>

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
<body class="mainbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center" onload="setUploadTime('${uploadTime}')">

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