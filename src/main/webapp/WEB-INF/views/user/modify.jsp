<%@ page import="dev.yhp.study.last_bbs.enums.user.RegisterResult" %>
<%@ page import="dev.yhp.study.last_bbs.enums.user.ModifyResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="resources/scripts/modify.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/parts/header.jsp" %>
<main>
    <form id="modifyForm" method="post">
        <span>이메일 : ${user.email}</span>
        <br>
        <label>
            <span hidden>기존 비밀번호</span>
            <input maxlength="50" name="password" placeholder="기존 비밀번호" type="password">
        </label>
        <br>
        <label>
            <span hidden>신규 비밀번호</span>
            <input maxlength="50" name="passwordNew" placeholder="신규 비밀번호" type="password">
        </label>
        <label>
            <span hidden>신규 비밀번호 재확인</span>
            <input maxlength="50" name="passwordNewCheck" placeholder="신규 비밀번호 재확인" type="password">
        </label>
        <br>
        <label>
            <span>닉네임 : </span>
            <input maxlength="10" name="nickname" placeholder="닉네임" type="text" value="${user.nickname}">
        </label>
        <br>
        <span>이름 : ${user.nameFirst} ${user.nameOptional} ${user.nameLast}</span>
        <br>
        <label>
            <span hidden>연락처</span>
            <select name="contactFirst">
                <option selected value="010">010</option>
                <option value="070">070</option>
            </select>
        </label>
        <label>
            <span hidden>연락처(중간)</span>
            <input maxlength="4" name="contactSecond" placeholder="연락처(중간)" type="number" value="${user.contactSecond}">
        </label>
        <label>
            <span hidden>연락처(끝)</span>
            <input maxlength="4" name="contactThird" placeholder="연락처(끝)" type="number" value="${user.contactThird}">
        </label>
        <br>
        <label>
            <span hidden>우편번호</span>
            <input maxlength="5" name="addressPost" readonly type="number" value="${user.addressPost}">
        </label>
        <input name="AddressPostFindButton" type="button" value="우편번호 찾기">
        <br>
        <label>
            <span hidden>기본 주소</span>
            <input maxlength="100" name="addressPrimary" readonly type="text" value="${user.addressPrimary}">
        </label>
        <br>
        <label>
            <span hidden>상세 주소</span>
            <input maxlength="100" name="addressSecondary" placeholder="상세 주소" type="text" value="${user.addressSecondary}">
        </label>
        <br>
        <input type="submit" value="정보 수정">
    </form>
    <div>
        <a href="/">수정 취소</a>
    </div>
</main>
<%@ include file="/WEB-INF/parts/footer.jsp" %>
<script>
    ${vo.result == ModifyResult.DUPLICATE_NICKNAME ? "alert('입력하신 닉네임은 이미 사용 중입니다.');" : ""}
    ${vo.result == ModifyResult.DUPLICATE_CONTACT ? "alert('입력하신 연락처는 이미 사용 중입니다.');" : ""}
    ${vo.result == ModifyResult.FAILURE ? "alert('알 수 없는 이유로 회원가입에 실패하였습니다.');" : ""}
    ${vo.result == ModifyResult.SUCCESS ? "alert('정보 수정에 성공하였습니다. 다시 로그인 해주세요.'); window.location.href = 'logout';" : ""}
</script>
</body>
</html>