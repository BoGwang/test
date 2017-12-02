<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="new.do" method="post" enctype="multipart/form-data">
		<div>
			<label>작성자 <input type="text" name="id"></label>
		</div>
		<div>
			<label>제목 <input type="text" name="title"></label>
		</div>
		<div>
			<label>내용 <textarea name="content"></textarea></label>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment" multiple="multiple"></label>
		</div>
		<input type="submit">
		<input type="reset">
		<a href="list.do">게시판 목록으로 이동</a>
	</form>
</body>
</html>