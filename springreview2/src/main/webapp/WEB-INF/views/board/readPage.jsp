<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">


<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>  
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

  <title>Boardread</title>

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
		
		// 후기 평점 가져오기
		var score = ${boardVO.score };
		$("#star_grade a").each(function(index, item){
			if(index<score){
				$(item).addClass('on');
			} else{
				$(item).removeClass('on');
			}
		});
	});
</script>
<style>
     #star_grade a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade a.on{
        color: black;
    }

</style>
</head>
<body>
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <div class="container">

    <div class="row">
      <div class="col-lg-3">

        <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>

      <div class="col-lg-9">
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		
        <div class="row">
		          
           <div class="col">
           
<h3>게시물조회</h3>
<form role="form" id="form1" method="post">
	<input type="hidden" name="bno" id="bno" value="${boardVO.bno }" />
	<input type="hidden" name="page" value="${cri.page }" />
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
	<input type="hidden" name="searchType" value="${cri.searchType }" />
	<input type="hidden" name="keyword" value="${cri.keyword }" />
	
</form>
<table class="table table-boardered">
<tr>
<th>제목</th>
<td><span class="form-control">${boardVO.title }</span></td>
</tr>

<tr>
<th>내용</th>
<td><textarea rows="10" cols="50" readonly="readonly" class="form-control">${boardVO.bd_content }</textarea></td>
</tr>
<tr>
<th>별점</th>
<td>
<div class="rating">
				<p id="star_grade">
					<a href="#">★</a>
					<a href="#">★</a>
					<a href="#">★</a>
					<a href="#">★</a>
					<a href="#">★</a>
				</p>
</div>
</td>
</tr>
<tr>
<th>작성자</th>
<td><label>${boardVO.writer }</label></td>
</tr>

<tr>
<td colspan="2" class="text-center">
<c:if test="${sessionScope.user != null}"> 
<button type="submit" id="btnModify" class="btn btn-primary">수정하기</button>
<button type="submit" id="btnDel" class="btn btn-primary">삭제하기</button>
</c:if>
<button type="submit" id="btnListAll" class="btn btn-primary">리스트(목록)</button>
</td>
</tr>
</table>


</div>
     
    </div>
    </div>
          
        </div>

      </div>
</body>

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>