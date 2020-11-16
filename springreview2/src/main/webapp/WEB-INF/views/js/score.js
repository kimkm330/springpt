$(document).ready(function(){
	/* 상품후기 쓰기 버튼 클릭 시 */
	
		
		// 유효성 검사
		if(score==0){
			alert("별점을 선택해주세요.");
			return;
			
		}
		// DB작업
		$.ajax({
			url: '/board/writeaction',
			type:'post',
			dataType: 'text',
			data: {
				"score" : score,
			},
			success : function(data){
				alert("상품후기가 등록되었습니다.");
				// 별점 비움
				$("#star_grade a").parent().children("a").removeClass("on");  
				
			}
		});
		
				
		// 후기 평점 가져오기
		$("#star_grade_modal a").each(function(index, item){
			if(index<score){
				$(item).addClass('on');
			} else{
				$(item).removeClass('on');
			}
		});
		
		

	});
	
	
	 /* 
	  * Modal 창에서 별점 클릭 시, 색상 변경 
	  */
	 $('#star_grade_modal a').click(function(){
		 $(this).parent().children("a").removeClass("on"); 
		 $(this).addClass("on").prevAll("a").addClass("on"); 
		     return false;
	 });	
	 
	 
	 /* modal 창에서 수정버튼 클릭 시 */
	 $("#btn_modal_modify").on("click", function() {

		var rv_num = $(".modal-body").attr("data-rv_num"); 
	 	var rv_content = $("#replytext").val();
	 	var pdt_num = $("#pdt_num").val();

	 	// 선택된 별점 개수를 가져옴
	 	var rv_score = 0;
	 	$("#star_grade_modal a").each(function(i, e){
	 		if($(this).attr('class')=='on'){
	 			rv_score += 1;
	 		}
	 	});
	 	
	 	// DB작업
	 	$.ajax({
	 		url : '/review/modify',
	 		type : 'post',
	 		data : {
	 			"rv_num" : rv_num,
	 			"rv_content" : rv_content,
	 			"rv_score" : rv_score
	 		},
	 		dataType: 'text',
	 		success : function(result) {
 				alert("수정 되었습니다.");
 				getPage("/review/" + pdt_num + "/" + replyPage);
	 		}
	 	});
	 });