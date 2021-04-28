<%@ page import="dev.yhp.study.last_bbs.enums.user.RegisterResult" %>
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
    <script src="resources/scripts/register.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/parts/header.jsp" %>
<main>
    <form id="registerForm" method="post">
        <label>
            <span hidden>이메일</span>
            <input autofocus maxlength="50" name="email" placeholder="이메일" type="email" value="${vo.email}">
        </label>
        <br>
        <label>
            <span hidden>비밀번호</span>
            <input maxlength="50" name="password" placeholder="비밀번호" type="password">
        </label>
        <label>
            <span hidden>비밀번호 재확인</span>
            <input maxlength="50" name="passwordCheck" placeholder="비밀번호 재확인" type="password">
        </label>
        <br>
        <label>
            <span hidden>닉네임</span>
            <input maxlength="10" name="nickname" placeholder="닉네임" type="text">
        </label>
        <br>
        <label>
            <span hidden>이름</span>
            <input maxlength="10" name="nameFirst" placeholder="이름" type="text">
        </label>
        <label>
            <span hidden>중간 이름(선택)</span>
            <input maxlength="10" name="nameOptional" placeholder="중간 이름(선택)" type="text">
        </label>
        <label>
            <span hidden>성</span>
            <input maxlength="10" name="nameLast" placeholder="성" type="text">
        </label>
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
            <input maxlength="4" name="contactSecond" placeholder="연락처(중간)" type="number" value="${vo.contactSecond}">
        </label>
        <label>
            <span hidden>연락처(끝)</span>
            <input maxlength="4" name="contactThird" placeholder="연락처(끝)" type="number" value="${vo.contactThird}">
        </label>
        <br>
        <label>
            <span hidden>우편번호</span>
            <input maxlength="5" name="addressPost" readonly type="number">
        </label>
        <input name="AddressPostFindButton" type="button" value="우편번호 찾기">
        <br>
        <label>
            <span hidden>기본 주소</span>
            <input maxlength="100" name="addressPrimary" readonly type="text">
        </label>
        <br>
        <label>
            <span hidden>상세 주소</span>
            <input maxlength="100" name="addressSecondary" placeholder="상세 주소" type="text">
        </label>
        <br>
        <label>
            <span>[필수] 이용 약관</span>
            <br>
            <textarea readonly>이용 약관</textarea>
        </label>
        <br>
        <label>
            <input name="agreeTerm" type="checkbox">
            <label>이용 약관을 읽어보았으며 동의합니다.</label>
        </label>
        <br>
        <label>
            <span>[필수] 개인정보 처리방침</span>
            <br>
            <textarea readonly>개인정보 처리방침</textarea>
        </label>
        <br>
        <label>
            <input name="agreePrivacy" type="checkbox">
            <label>개인정보 처리방침을 읽어보았으며 동의합니다.</label>
        </label>
        <br>
        <label>
            <span>[선택] 개인정보 제3자 제공</span>
            <br>
            <textarea readonly>개인정보 제3자 제공</textarea>
        </label>
        <br>
        <label>
            <input name="agreePromotion" type="checkbox">
            <label>개인정보 제3자 제공에 대한 약관을 읽어보았으며 동의합니다.</label>
        </label>
        <br>
        <input type="submit" value="회원가입">
    </form>
    <div>
        <a href="login">로그인으로 돌아가기</a>
    </div>
</main>
<%@ include file="/WEB-INF/parts/footer.jsp" %>
<script>
    ${vo.result == RegisterResult.DUPLICATE_CONTACT ? "alert('입력하신 연락처는 이미 사용 중입니다.')" : ""}
    ${vo.result == RegisterResult.DUPLICATE_EMAIL ? "alert('입력하신 이메일은 이미 사용 중입니다.')" : ""}
    ${vo.result == RegisterResult.DUPLICATE_NICKNAME ? "alert('입력하신 닉네임은 이미 사용 중입니다.')" : ""}
    ${vo.result == RegisterResult.FAILURE ? "alert('알 수 없는 이유로 회원가입에 실패하였습니다.')" : ""}
</script>
</body>
</html>