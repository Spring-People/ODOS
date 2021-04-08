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

</head>
<body onload="setUploadTime('${uploadTime}')">

<c:if test="${empty authInfo}">
    <p>환영합니다.</p>
    <p>
        <a href="<c:url value="/register/step1" />">[회원 가입하기]</a>
        <a href="<c:url value="/login" />">[로그인]</a>
    </p>
</c:if>

<c:if test="${! empty authInfo}">
    <p>${authInfo.name}님, 환영합니다.</p>
    <p>
        <a href="<c:url value="/logout" />">[로그아웃]</a>
        <a href="<c:url value="/problemlist" />">[문제 목록]</a>
        <a href="/main/mypage.html">[마이 페이지]</a>
        <strong>문제 업로드 까지 남은 시간</strong>
    <div id = "demo"></div>
    <div id="problem-container">
        <a id="today-problem-link" href="#">오늘의 문제로 이동</a>
    </div>
    </p>

</c:if>
</body>
</html>