<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/common.jsp" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Boardlist</title>

  <!-- Bootstrap core CSS -->
 <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>  


  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  

<script>
	$(document).ready(function(){
		$("#btnSearch").click(function(){
			
		});
	});

</script>

</head>

<body>
<!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">
	<!-- 카테고리 메뉴 -->
      <div class="col-lg-3">

        <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		          
           <div class="col">
           
           
<form id="searchForm" action="/board/listPage" method="get">
	<select name="searchType">
		<option value="n" <c:out value="${cri.searchType == null ? 'selected' : ''}" />>검색분류</option>
		<option value="t" <c:out value="${cri.searchType eq 't' ? 'selected' : ''}" />>제목</option>
		<option value="w" <c:out value="${cri.searchType eq 'w' ? 'selected' : ''}" />>작성자</option>
		<option value="c" <c:out value="${cri.searchType eq 'c' ? 'selected' : ''}" />>내용</option>
		<option value="tw" <c:out value="${cri.searchType eq 'tw' ? 'selected' : ''}" />>제목+작성자</option>
	</select>
	<input type="text" name="keyword" value="${cri.keyword }" />
<button type="submit" id="btnSearch">검색</button>

</form> 
<table class="table table-boardered">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:forEach items="${listPage }" var="boardVO">
	<tr>
		<td>${boardVO.bno }</td>
		<td><a href="/board/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${boardVO.bno }">${boardVO.title }</a></td>
		<td>${boardVO.writer }</td>
		<td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd"/></td>
	</tr>
	</c:forEach>
</table>
<!-- 페이지 번호달기 -->
<p>
<c:if test="${pageMaker.prev}"><!-- 이전 페이지 표시 -->
	<span><a href="${pageMaker.makeSearch(pageMaker.startPage - 1) }">[이전]</a></span>
</c:if>

<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	<span>
	    										<!-- <a class =active href="listPage?page=1&perPageNum=2&searchType&keyword=">1</a>
	     -->
		<a <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/> href="${pageMaker.makeSearch(idx)}">${idx}</a>
	</span>
</c:forEach>
<!--  다음 페이지 표시 -->
<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	<span><a href="${pageMaker.makeSearch(pageMaker.endPage +1) }">[다음]</a></span>
</c:if>
</p>
     <a href="/board/write"><button class="btn btn-primary">글쓰기</button></a>
</div>
    </div>
    </div>
          
		
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    
    <!-- /.row -->

  
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

</body>
</html>