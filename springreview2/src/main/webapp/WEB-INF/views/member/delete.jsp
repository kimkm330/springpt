<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
 <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>  


<!-- 회원가입 유효성 검사 -->
<script src="/js/join.js"></script>


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
		
		<div style="background-color: white; width:80%; padding: 5% 5%;">
					<form id="deleteForm" method="post" action="/member/delete">
						<div class="form-group">
							<input type="hidden" name="mem_id" value="${sessionScope.user.mem_id}" />
							회원 탈퇴 하시겠습니까?
						</div>
						<div class="form-group">
							<input type="submit" id="btn_submit" class="btn btn-danger" value="확인">
							<input type="button" id="btn_cancle" class="btn btn-default" value="취소" onclick="location.href='/';">
						</div>
					</form>
		</div>


		
		
</div>
</div>
</div>

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

 

</body>

</html>

