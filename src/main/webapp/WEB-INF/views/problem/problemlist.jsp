<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
    <link href="${pageContext.request.contextPath}/css/problemlist.css" rel="stylesheet" type="text/css">

    <title>문제 목록</title>
</head>
<body class="problemlistbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">
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

<div class="container">
    <h3> 문제 목록 </h3>
</div>

<div class="container">
    <table class="table table-hover">
        <tr>
            <th>Number</th>
        </tr>
        <c:forEach var="problem" items="${problemList}">
            <tr onClick="location.href='problem/detail/${problem.id}'">
                <td>${problem.num}</td>
            </tr>
        </c:forEach>

    </table>
</div>


<%@ include file="../bootstrap.jsp" %>
</body>
</html>