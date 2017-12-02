<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
</head>
<body>
	<h1>글 상세보기</h1>
	<dl>
		<dt>번호</dt>
		<dd>${ item.dcode }</dd>
		<dt>음식 명</dt>
		<dd>${ item.name }</dd>
		<dt>가격</dt>
		<dd>${ item.price }</dd>
		<dt>내용</dt>
		<dd>${ item.description }</dd>
		<c:if test="${ !empty filename }">
			<dt>첨부파일</dt>
			<dd><a href="download.do?filename=${ item.image }">${ filename }</a></dd>
		</c:if>
	</dl>
	<a href="list.do">게시판 목록으로 이동</a>
	<a href="modify.do?no=${ item.dcode }">수정하기</a>
	<a href="remove.do?no=${ item.dcode }">삭제하기</a>
</body>
</html>