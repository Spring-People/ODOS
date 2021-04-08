<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>문제 상세</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript"><%@include file="comment.js" %></script>

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
</head>

<script language="JavaScript">


    function goLink() {
        location.href = "https://www.acmicpc.net/problem/" + ${problem.num};
    }

    <!-- solved 관련-->
    function SolvedButton(){

        let check_number = ${authInfo.id};

        let data = {'check_number':check_number};

        $.ajax({
            type: 'PUT',
            url: '/api/problem/solved/${problem.id}',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                console.log(response);

                $('#SolvedButton').hide();
                $('#waiting').show();

            }
        });
    }
    function SolvedButton2(){

        let check_number = -1;

        let data = {'check_number':check_number};

        $.ajax({
            type: 'PUT',
            url: '/api/problem/solved/${problem.id}',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                console.log(response);

                $('#SolvedButton2').hide();
                $('#allsovled').show();
            }
        });
    }

    <!--댓글 관련-->
    function insertButton() {

    let text = $('#content').val();
    let problem_id = $('#problemId').val();

    console.log(text);
    console.log(problem_id);

    let data = {'problem_id':problem_id, 'text':text};

    $.ajax({
        type: 'POST',
        async: false,
        url: '/comment/insert',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            console.log(response);
        }

    });

    commentList();
    }

function tempHTML(comment) {
    return "<div class='comment'><label>" + comment.name +" "+ comment.commentTime + " </label><p>" + comment.text + "</p></div>";
    }

function commentList(){

    let problem_id = $('#problemId').val();
    let data = {'problem_id':problem_id};
    let commentContainer = $('#comment-container');

    $.ajax({
        type: 'POST',
        url: '/comment/list',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            let commentList = response;
            console.log(commentList.length);
            let comment = commentList[commentList.length-1];
            commentContainer.append(tempHTML(comment));
            }
        });
    }

function commentAllList(){

     let problem_id = $('#problemId').val();
     let data = {'problem_id':problem_id};
     let commentContainer = $('#comment-container');

     $.ajax({
         type: 'POST',
         url: '/comment/list',
         contentType: 'application/json',
         data: JSON.stringify(data),
         success: function(response) {
             console.log(response);

             let commentList = response;

             for(let i=0; i<commentList.length; i++)
             {
                 let comment = commentList[i];
                 commentContainer.append(tempHTML(comment));
             }

         }
     });
}

$(document).ready(function(){
    commentAllList(); //페이지 로딩시 댓글 목록 출력

    $('#waiting').hide();
    $('#allsovled').hide();
});

</script>
<body class="problemlistbody">
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
    <h2> ${problem.num}번 문제</h2>
    <div class="container problemlink">
        <a href="javascript:goLink()" style="text-decoration: none;">${problem.num}번 문제 풀기</a>
    </div>
</div>
<div class="container">
    <div class="problemmessage">
        <c:choose>
            <c:when test="${problem.solved eq 0}">
                <button id="SolvedButton" onclick="SolvedButton()" class="btn btn-success" name="SolvedButton" type="button">Solved</button>
                <p id="waiting" name="waiting">다른 팀원의 문제 풀이를 기다리고 있습니다</p>
            </c:when>

            <c:when test="${problem.solved eq authInfo.id}">
                <p>다른 팀원의 문제 풀이를 기다리고 있습니다</p>
            </c:when>

            <c:when test="${problem.solved eq -1}">
                <p>모든 팀원이 문제를 해결했습니다!</p>
            </c:when>

            <c:otherwise>
                <button id="SolvedButton2" onclick="SolvedButton2()" class="btn btn-success" name="SolvedButton2" type="button">Solved</button>
                <p id="allsovled" name="allsovled">모든 팀원이 문제를 해결했습니다!</p>
            </c:otherwise>

        </c:choose>
    </div>
</div>

<br/>
<div class="container">

    <div class="container">
        <div class="input-group">
             <input type="hidden" name="problemId" id="problemId" value="${problem.id}"/>
             <input type="text" class="form-control" id="content" name="content">
             <span class="input-group-btn">
                 <button id="insertButton" onclick="insertButton()" class="btn btn-default" name="insertButton" type="button">등록</button>
             </span>
         </div>
    </div>
    <br/>
    <div id="comment-container" class="container">
        <div>
            <label>댓글</label>
        </div>
        <div class="commentList"></div>
    </div>


</div>


<%@ include file="../bootstrap.jsp" %>
</body>
</html>