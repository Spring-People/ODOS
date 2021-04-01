<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인 완료</title>
</head>
<body>
<p><strong>${loginCommand.email}님</strong>
    로그인을 완료했습니다.</p>
<p><a href="<c:url value='/main'/>">[메인으로 이동]</a></p>
</body>
</html>