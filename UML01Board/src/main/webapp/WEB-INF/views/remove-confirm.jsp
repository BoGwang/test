<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제 확인</title>
</head>
<body>
	<h1>삭제 확인</h1>
	<p>정말로 삭제하시겠습니까?</p>
	<form action="remove.do" method="post">
		<input type="hidden" name="no" value="${ no }">
		<input type="submit" value="삭제하기">
	</form>
	<a href="list.do">게시판 목록으로 이동</a>
</body>
</html>