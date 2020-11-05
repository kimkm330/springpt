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

  <title>Boardwrite</title>

  <!-- Bootstrap core CSS -->
 <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>  


  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  



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
      <div class="col-lg-9">
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		
        <div class="row">
           <div class="col">
<form method="post" action="/board/modifyPage">
<input type="hidden" name="page" value="${cri.page }" />
<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
<input type="hidden" name="searchType" value="${cri.searchType }" />
<input type="hidden" name="keyword" value="${cri.keyword }" />
<input type="hidden" name="bno" value="${boardVO.bno }"/>
<table class="table table-boardered">
<tr>
<th>글번호</th>
<td><label>${boardVO.bno }</label></td>
</tr>
<tr>
<th>제목</th>
<td><input type="text" name="title" value="${boardVO.title }" /></td> 
</tr>
<tr>
<th>내용</th>
<td><textarea rows="10" cols="50" name="content">${boardVO.content }</textarea></td>
</tr>
<tr>
<th>작성자</th>
<td><input type="text" name="writer" value="${boardVO.writer }" readonly="readonly"/></td>
</tr>
<tr>
<td colspan="2" class="text-center">
<input class="btn btn-primary" type="submit" value="수정하기">
<input class="btn btn-danger" type="reset" value="취소">
</td>
</tr>
</table>
</form>
</div>
     
    </div>
    </div>
          
		<!-- 회원가입 -->
        </div>
        <!-- /.row -->

      </div>
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>