<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>문제 목록</title>
</head>
<body>
<h2> 문제 목록 </h2>

<div class="container">
    <table class="table table-hover">
        <tr>
            <th>No</th>
        </tr>
        <c:forEach var="problem" items="${problemList}">
            <tr>
                <td>${problem.num}</td>
            </tr>
        </c:forEach>

    </table>
</div>


<%@ include file="bootstrap.jsp" %>

</body>
</html>