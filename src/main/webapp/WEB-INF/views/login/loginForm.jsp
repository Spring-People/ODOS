<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title>로그인</title>
</head>
<body class="loginbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">
<div class="card align-middle" style="width:55rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
        <h2 class="card-title text-center" style="color:#113366;">로그인</h2>
    </div>
    <div class="card-body">
    <form:form modelAttribute="loginCommand" class="form-signin">
     <form:errors />
            <label for="inputEmail" class="sr-only">이메일</label>
            <form:input path="email" class="form-control" placeholder="Email" /> <br>
            <label for="inputPassword" class="sr-only">비밀번호</label>
            <form:password path="password" class="form-control" placeholder="Password" /> <br>
            <div class="checkbox">
            <label>
                <form:checkbox path="rememberEmail"/>이메일 저장
            </label>
            </div>
    <input type="submit" value="로그인" class="btn btn-lg btn-block btn-info" >
    </form:form>
    <br>
    <p><a href="<c:url value='/register/step1'/>">회원가입</a></p>
    </div>
</div>

<%@ include file="../bootstrap.jsp" %>
</body>
</html>