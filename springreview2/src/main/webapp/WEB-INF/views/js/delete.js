$(document).ready(function() {
	
	var form = $("#deleteForm");
	
	
	

	

	
	
	/* 회원가입 버튼 클릭 시 */ 
	$("#btn_submit").on("click", function(){
		
		
			form.submit();
	});
	
	$("#btn_cancle").on("click", function(){
		
		var result = confirm("변경을 취소하시겠습니까?");
		if(result){
			location.href="/"; 
		} else{}
	});
	
});

