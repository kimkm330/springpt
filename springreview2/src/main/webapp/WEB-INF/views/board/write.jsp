<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>글쓰기 페이지</h3>
<form method="post" action="writeaction">
제목 : <input type="text" name="title"><br>
내용 : <textarea rows="5" cols="20" name="bd_content"></textarea><br>
작성자 : <input type="text" name="writer"><br>
<button type="submit">글쓰기</button>
</form>
</body>
</html>