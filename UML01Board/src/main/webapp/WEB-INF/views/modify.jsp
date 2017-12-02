<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정하기</title>
</head>
<body>
	<h1>수정하기</h1>
	<form action="modify.do" method="post" enctype="multipart/form-data">
		<div>
			<label>번호 ${ item.no }</label>
		</div>
		<div>
			<label>작성자 ${ item.id }</label>
		</div>
		<div>
			<label>제목 <input type="text" name="title" value="${ item.title }"></label>
		</div>
		<div>
			<label>내용 <textarea name="content">${ item.content }</textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment" multiple="multiple"></label>
		</div>
		<input type="hidden" name="no" value="${ item.no }">
		<input type="submit">
		<input type="reset">
		<a href="list.do">게시판 목록으로 이동</a>
	</form>
</body>
</html>