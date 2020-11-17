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

  <title>Boardwrite</title>

  
<script>

  Handlebars.registerHelper("checkRating", function(rating) {
		var stars = "";
		switch(rating){
			case 1:
				 stars="★☆☆☆☆";
				 break;
			case 2:
				 stars="★★☆☆☆";
				 break;
			case 3:
				 stars="★★★☆☆";
				 break;
			case 4:
				 stars="★★★★☆";
				 break;
			case 5:
				 stars="★★★★★";
				 break;
			default:
				stars="☆☆☆☆☆";
		}
		return stars;
	});
  
  $(document).ready(function(){
		
	  var score = 0;
		 $('#star_grade a').click(function(){
			 score = 0;
			 $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
			 $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과 그 앞 까지 별점에 on 클래스 추가 */
			 
			 $("#star_grade a").each(function(i, e){
					if($(this).attr('class')=='on'){
					score += 1;
							}
					$("#score").val(score);
						});
			     return false;
		 });
  
	$("#btnwrite").on("click", function(){
		
		var title = $("#title");
		var bd_content = $("#bd_content");
		var writer = $("#writer");
		var score = $("#score");
		
		/* 유효성 검사 */
		if(title.val()==null || title.val()==""){
			alert("제목을 입력해주세요");
			title.focus();
			return;

		} else if(bd_content.val()==null || bd_content.val()==""){
			alert("내용을 입력해주세요.");
			bd_content.focus();
			return;
			
		} else if(writer.val()==null || writer.val()==""){
			alert("작성자를 입력해주세요.");
			writer.focus();
			return;
		} else if(score.val()==null || score.val()==""){
			alert("별점을 입력해주세요.");
			score.focus();
			return;
		} 
		$("#writeform").submit();
		
	});
  })

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
<form method="post" action="writeaction" id="writeform">
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
			<input type="hidden" name="score" id="score">
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer" id="writer"></td>
	</tr>
	<tr>
		<td colspan="2" class="text-center"><button type="button" class="btn btn-primary" id="btnwrite">완료</button></td>
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