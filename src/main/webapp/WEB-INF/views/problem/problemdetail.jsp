<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detail</title>
</head>

<script language="JavaScript">
    function goLink() {
        location.href = "https://www.acmicpc.net/problem/" + ${problem.num};

    }

    window.onpageshow = function(event) {

        //back forward cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
        if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
            //alert("check");
            window.location.href = '/problemlist';
        }
    }
</script>
<body>


<h2> 게시글 상세 </h2>

<div class="container">
    <div class="form-group">
        <label>문제 번호</label>
        <p>${problem.num}</p>
    </div>
    <div class="form-group">
        <label>문제</label>
        <a href="javascript:goLink()">${problem.num}번 문제 풀기</a>
    </div>

    <!-- 댓글작성 -->
    <div class="container">
        <form method="post" action="/comment/insert">
            <div class="input-group">
                <p>
                    <input type="hidden" name="problem_id" value="${problem.id}"/>
                </p>
                <p>
                    <textarea rows="5" cols="50" name="text" placeholder="내용을 입력하세요."></textarea>
                </p>
                <p>
                    <button class="btn btn-default" type="submit">등록</button>
               </p>
            </div>
        </form>
    </div>

    <!-- 댓글목록 -->
    <c:forEach var="comment" items="${commentList}">
        <div>
            <p>${comment.name} / <fmt:formatDate value="${comment.commentTime}" pattern="yyyy-MM-dd" /> </p>
            <p>${comment.text}</p>
        </div>
    </c:forEach>

</div>

<%@ include file="bootstrap.jsp" %>
</body>
</html>