<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<header>
    <div>
        <c:if test="${user == null}">
            <a href="user/login">로그인</a>
        </c:if>
        <c:if test="${user != null}">
            <span><b>${user.nickname}</b>님 환영합니다.</span>
            <span><a href="/user/modify">정보수정</a></span>
            <span><a href="/user/logout">로그아웃</a></span>
        </c:if>
    </div>
    <div>

    </div>
</header>