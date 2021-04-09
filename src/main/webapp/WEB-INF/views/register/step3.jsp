<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- made CSS -->
    <link href="${pageContext.request.contextPath}/css/loginForm.css" rel="stylesheet" type="text/css">
    <title>회원가입</title>
</head>
<body class="loginbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">

<div class="card align-middle" style="width:55rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
        <h2 class="card-title text-center" style="color:#113366;">${registerRequest.name}님 가입을 환영합니다!</h2>
    </div>
    <div class="card-body text-center">
        <p><a href="<c:url value='/login'/>">로그인 화면 이동</a></p>
    </div>
</div>

<%@ include file="../bootstrap.jsp" %>
</body>
</html>