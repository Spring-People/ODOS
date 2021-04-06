<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Detail</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript"><%@include file="comment.js" %></script>
</head>

<script language="JavaScript">

    function goLink() {
        location.href = "https://www.acmicpc.net/problem/" + ${problem.num};
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
    return "<div><p>작성자 : " + comment.name + comment.commentTime + " </p><p>내용 : " + comment.text + "</p></div>";
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
});

</script>
<body>


<h2> 문제 상세 </h2>

<div class="container">
    <div class="form-group">
        <label>문제 번호</label>
        <p>${problem.num}</p>
    </div>
    <div class="form-group">
        <label>문제</label>
        <a href="javascript:goLink()">${problem.num}번 문제 풀기</a>
    </div>

    <div class="container">
        <div class="input-group">
             <input type="hidden" name="problemId" id="problemId" value="${problem.id}"/>
             <input type="text" class="form-control" id="content" name="content">
             <span class="input-group-btn">
                 <button id="insertButton" onclick="insertButton()" class="btn btn-default" name="insertButton" type="button">등록</button>
             </span>
         </div>
    </div>

    <div id="comment-container" class="container">
        <div>
            <h3>댓글</h3>
        </div>
        <div class="commentList"></div>
    </div>


</div>


<%@ include file="bootstrap.jsp" %>
</body>
</html>