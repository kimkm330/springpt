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


<script type="text/javascript" src="/js/changePw.js"></script>


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
					<form id="changePwForm" method="post" action="changePw">
						<div class="form-group">
							<input type= "hidden" name="mem_id" value="${sessionScope.user.mem_id}" />
							
							<input type="password" class="form-control" id="mem_pw" class="form-control"
								placeholder="현재 비밀번호를 입력해주세요" style="max-width: 630px;">
							
							<input type="password" class="form-control" id="mem_pw_change" name="mem_pw" class="form-control"
								placeholder="변경할 비밀번호를 입력해주세요" style="max-width: 630px; margin: 7px 0px;">
							
							<input type="password" class="form-control" id="mem_pw_check" class="form-control"
								placeholder="변경할 비밀번호를 다시 입력해주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<input type="button" id="btn_submit" class="btn btn-primary" value="확인">
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

