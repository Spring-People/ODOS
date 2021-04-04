<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
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

        document.getElementById("demo").innerHTML = Hour + "시" + Min + "분" + Sec + "초";

        Check--;

        if(Check < 0) {
            clearInterval(s);
            document.getElementById("demo").innerHTML = "문제가 업로드 되었습니다";
        }
    }, 1000);
</script>
<body>
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
        <strong>문제 업로드 까지 남은 시간</strong>
        <div id = "demo"></div>
    </p>

</c:if>
</body>
</html>