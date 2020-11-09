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
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		          
           <div class="col">
<h3>글쓰기 페이지</h3>
<form method="post" action="writeaction">
<table class="table table-boardered">
<tr>
<th>제목</th>
<td><input type="text" name="title" id="title" class="form-control"></td>
</tr>
<tr>
<th>내용</th>
<td><textarea rows="10" cols="20" name="bd_content" id="bd_content" class="form-control"></textarea></td>
</tr>
<tr>
<th>작성자</th>
<td><input type="text" name="writer" id="writer"></td>
</tr>
<tr>
<td colspan="2" class="text-center"><button type="submit" class="btn btn-primary">완료</button></td>
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
      <!-- /.col-lg-9 -->

    
    <!-- /.row -->

  
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>