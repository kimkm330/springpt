<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<style>

#modify_dropdown {  
	display:none; /* 평상시에는 서브메뉴가 안보이게 하기 */ 
	height:auto; 
	padding:10px 0px; 
	margin:0px; 
	border:0px; 
	position:absolute; 
	width:130px; 
	z-index:200; 
}

#modify:hover #modify_dropdown{
	display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
}

</style>
    
    
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Start Bootstrap</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          
          <%-- 로그인 안 한 상태 --%>
          
				<c:if test="${sessionScope.user == null}">
				<li class="nav-item active">
            <a class="nav-link" href="/">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
					<li class="dropdown user user-menu">
						<!-- Menu Toggle Button -->
						<a href="/member/join"> <!-- class="dropdown-toggle" data-toggle="dropdown" --> 
							<span class="nav-link" style="color:white;">회원가입</span>
						</a>
					</li>
					<li class="dropdown user user-menu">
						<a href="/member/login"> 
							<span class="nav-link" style="color:white;">로그인</span>
						</a>
					</li>
				</c:if>
				
				<%-- 로그인 한 상태 --%>
				<c:if test="${sessionScope.user != null}"> 
					
					<li class="dropdown user user-menu"> 
						<p class="nav-link" style="color:#5F9EA0;">
							${sessionScope.user.mem_name}님 반갑습니다
						</p>
					</li>
					<li class="nav-item active">
            			<a class="nav-link" href="/">Home
              			<span class="sr-only">(current)</span>
            			</a>
          			</li>
					<li class="dropdown user user-menu">
						<a href="/member/logout"> 
							<span class="nav-link" style="color:white;">로그아웃</span>
						</a>
					</li>
					<li class="dropdown user user-menu" id="modify">
						<a href="#">
							<span class="nav-link" style="color:white;">회원정보 관리</span>
						</a>
						<ul class="dropdown-menu" id= "modify_dropdown" >
							<li ><a href="/member/checkPw?url=modify" style="color:black;">회원정보 수정</a></li>
							<li><a href="/member/checkPw?url=changePw" style="color:black;">비밀번호 변경</a></li>
							<li><a href="/member/checkPw?url=delete" style="color:black;">회원 탈퇴</a></li>
						</ul>
					</li>	
					<li class="dropdown user user-menu">
						<a href="/cart/list"> 
							<span class="nav-link" style="color:white;">장바구니</span>
						</a>
					</li>		
					<li class="dropdown user user-menu">
						<a href="/order/list">
							<span class="nav-link" style="color:white;">주문조회</span>
						</a>
					</li>		
				</c:if>
        </ul>
      </div>
    </div>
  </nav>