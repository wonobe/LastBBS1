<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <script src="resources/scripts/login.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/parts/header.jsp" %>
<main>
    <form id="loginForm" method="post">
        <label>
            <span hidden>이메일</span>
            <input type="email" name="email" maxlength="50" placeholder="Email" autofocus>
        </label>
        <label>
            <span hidden>비밀번호</span>
            <input type="password" name="password" maxlength="50" placeholder="Password">
        </label>
        <label>
            <input name="autoSign" type="checkbox">
            <span>자동 로그인</span>
        </label>
        <input type="submit" value="로그인">
    </form>
    <div><a href="register">Register</a></div>
    <div><a href="forgot-email">Forgot Email?</a></div>
    <div><a href="forgot-password">Forgot Password?</a></div>
</main>
<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>