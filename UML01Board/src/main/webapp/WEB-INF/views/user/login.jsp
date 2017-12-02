<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<c:url var="url" value="/login-processing"/>
	<form action="${url}" method="post">
		<c:if test="${param.action == 'error' }">
			<p> 이메일 혹은 비밀번호를 잘못 입력하였습니다.</p>
		</c:if>
		<c:if test="${param.action == 'logout' }">
			<p>로그아웃 하였습니다.</p>
		</c:if>
		<input type = "email" name= "email" placeholder="이메일 입력" required>
		<input type = "password" name = "password" placeholder="비밀번호 입력" required>
		 <!-- 보이면 안될 정보를 서버에 전송하기 위해 쓰이는 방법 / 현재는 Security를 사용했기 때문 -->
		<input type = "hidden" name = "${_csrf.parameterName}" value="${_csrf.token}">
		<input type = "submit" value = "로그인">
	</form>
	<a href = "<c:url value='/join'/>">회원 가입</a>
</body>
</html>