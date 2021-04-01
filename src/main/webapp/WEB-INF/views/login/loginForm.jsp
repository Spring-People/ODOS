<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<form:form modelAttribute="loginCommand">
    <form:errors />
    <p>
        <label>이메일:<br>
            <form:input path="email" />
        </label>
    </p>
    <p>
        <label>비밀번호:<br>
            <form:password path="password" />
        </label>
    </p>
    <p>
        <label>비밀번호 저장:<br>
            <form:checkbox path="rememberEmail"/>
        </label>
    </p>
    <input type="submit" value="로그인">
</form:form>
<p><a href="<c:url value='/register/step1'/>">[회원가입]</a></p>
</body>
</html>