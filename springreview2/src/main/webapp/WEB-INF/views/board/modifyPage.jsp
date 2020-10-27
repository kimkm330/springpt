<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/board/modifyPage">
<input type="hidden" name="page" value="${cri.page }" />
<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
<input type="hidden" name="searchType" value="${cri.searchType }" />
<input type="hidden" name="keyword" value="${cri.keyword }" />
<input type="hidden" name="bno" value="${boardVO.bno }"/>
글번호 : <label>${boardVO.bno }</label><br>
제목 : <input type="text" name="title" value="${boardVO.title }" /><br> 
내용 : <textarea rows="10" cols="50" name="bd_content">${boardVO.bd_content }</textarea><br>
작성자 : <input type="text" name="writer" value="${boardVO.writer }" readonly="readonly"/><br>
<input type="submit" value="수정하기">
<input type="reset" value="취소">


</form>
</body>
</html>