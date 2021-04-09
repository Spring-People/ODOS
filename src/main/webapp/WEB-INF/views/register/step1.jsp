<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
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
</head>
<body class="loginbody" cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">

<div class="card align-middle" style="width:55rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
        <h2 class="card-title text-center" style="color:#113366;">회원가입</h2>
    </div>
    <div class="card-body">
        <div class="container register">
                <p style="margin-left: 3px;">One Day One Solve 회원가입을 진행합니다.</p>
        </div>
        <br/>
        <form action="step2" method="post">

            <label>
                <input type="checkbox" name="agree" value="true" style="margin-left: 7px;"> 동의
            </label>
            <br/>

            <input type="submit" value="다음" class="btn btn-lg btn-block btn-info" />

        </form>
    </div>
</div>

<%@ include file="../bootstrap.jsp" %>
</body>
</html>