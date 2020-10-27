<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 조회</title>
<script>
	$(document).ready(function(){
		var formObj = $("#form1");
		
		//수정페이지 이동
		$("#btnModify").click(function(){
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		//삭제클릭
		$("#btnDel").click(function(){
			
			var bno = $("#bno").val();
			if(confirm("게시물  번호" + bno + " 번을 삭제하겠읍니까?"))
			{
				formObj.attr("action", "/board/removePage");
				formObj.attr("method", "get");
				formObj.submit();
			}
		});
		
		//리스트클릭
		$("#btnListAll").click(function(){
			//location.href = "/board/listPage";
			
			formObj.attr("action", "/board/listPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
	});
</script>
</head>
<body>
<h3>게시물조회</h3>
<form role="form" id="form1" method="post">
	<input type="hidden" name="bno" id="bno" value="${boardVO.bno }" />
	<input type="hidden" name="page" value="${cri.page }" />
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
	<input type="hidden" name="searchType" value="${cri.searchType }" />
	<input type="hidden" name="keyword" value="${cri.keyword }" />
	
</form>
제목 : <span>${boardVO.title }</span><br>
내용: <textarea rows="10" cols="50" readonly="readonly">
	${boardVO.bd_content }
	</textarea><br>
작성자 : <label>${boardVO.writer }</label>
</body>
<br>
<button type="submit" id="btnModify">수정하기</button>
<button type="submit" id="btnDel">삭제하기</button>
<button type="submit" id="btnListAll">리스트(목록)</button>
</html>