<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
</head>
<body>

	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>

	<div class='uploadedList'></div>

	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(".fileDrop").on("dragenter dragover", function(event) {
			
			event.preventDefault();
		});

		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			//선택자 .fileDrop 이 공간에 drop을 한 파일들을 가리키는 의미.
				var files = event.originalEvent.dataTransfer.files;
				
				var file = files[0];
	
				//console.log(file);
				
				// html5에서 지원하는 객체.<form>태그를 통하여 데이터전송하는 기능과 동일하다.
				var formData = new FormData();
				
				// drop한 파일을 객체에 추가하는 작업
				formData.append("file", file);
			
			$.ajax({
				  url: '/upload/uploadAjax',  //요청주소
				  data: formData,	   // 보낼 데이타(파일)
				  dataType:'text',     // 받아오는 데이타 형식
				  processData: false,	//
				  contentType: false,
				  type: 'POST',		   //데이터 전송방식
				  success: function(data){
					  
					  alert(data);
					  
					  var str ="";
					  
					  if(checkImageType(data)){
						  str ="<div><a href=displayFile?fileName="+getImageLink(data)+">"
								  +"<img src='displayFile?fileName="+data+"'/>"
								  +"</a><small data-src="+data+">X</small></div>";
					  }else{
						  str = "<div><a href='displayFile?fileName="+data+"'>" 
								  + getOriginalName(data)+"</a>"
								  +"<small data-src="+data+">X</small></div></div>";
					  }
					  
					  // 선택자 .uploadedList 에 해당하는 태그에 자식으로 str 변수의 내용을 추가작업
					  $(".uploadedList").append(str);
				  }
				});	
		});

		// "X" 를 클릭시 동작하는 코드
		$(".uploadedList").on("click", "small", function(event){
			
				 var that = $(this);
			
			   $.ajax({
				   url:"deleteFile",
				   type:"post",
				   data: {fileName:$(this).attr("data-src")},
				   dataType:"text",
				   success:function(result){
					   if(result == 'deleted'){
						// 서버에 파일을 삭제한 후, 웹브라우저 화면에서 파일이름 출력태그제거
						   that.parent("div").remove(); 
					   }
				   }
			   });
		});
		
		
/* 		
$(".fileDrop").on("drop", function(event) {
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

	//console.log(file);
	var formData = new FormData();
	
	formData.append("file", file);

	
	$.ajax({
		  url: '/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var str ="";
			  
			  console.log(data);
			  console.log(checkImageType(data));
			  
			  if(checkImageType(data)){
				  str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
						  +"<img src='displayFile?fileName="+data+"'/></a>"
						  +data +"</div>";
			  }else{
				  str = "<div><a href='displayFile?fileName="+data+"'>" 
						  + getOriginalName(data)+"</a></div>";
			  }
			  
			  $(".uploadedList").append(str);
		  }
		});			
});	 */


// /2019/04/30/6d27cb8f-6d87-49e8-94ce-373bb3fd4d7b_a90c6479-3f26-4558-9807-c5c480945b34_Array.exe
// UUID 로 생성한 파일명은 제외하고 링크로 사용할 내용을 원본파일명으로 하기위하여 존재하는 함수.
function getOriginalName(fileName){	

	if(checkImageType(fileName)){
		return;
	}
	
	var idx = fileName.indexOf("_") + 1 ;
	
	
	// a90c6479-3f26-4558-9807-c5c480945b34_Array.exe
	// UUID 로 생성한 파일명은 제외하고 원본파일명만 리턴
	return fileName.substr(idx);
	
}

// "/2019/04/30/s_99116f04-56d6-48bc-99d0-27c7d29a348a_31414f5c-6662-4dfb-8c3d-6b8c76ca5d4b_캡처.JPG"
function getImageLink(fileName){
	
	if(!checkImageType(fileName)){
		return;
	}
	var front = fileName.substr(0,12); //  /2019/04/30/
	
	// 99116f04-56d6-48bc-99d0-27c7d29a348a_31414f5c-6662-4dfb-8c3d-6b8c76ca5d4b_캡처.JPG"
	var end = fileName.substr(14); 
	
	return front + end; // s_ 문자열만 제외하고 다시 경로와 원본파일명으로 결합한 문자열
	
}




/* 		$(".fileDrop").on("drop", function(event) {
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			
			var file = files[0];

			//console.log(file);
			var formData = new FormData();
			
			formData.append("file", file);
			
			$.ajax({
				  url: '/uploadAjax',
				  data: formData,
				  dataType:'text',
				  processData: false,
				  contentType: false,
				  type: 'POST',
				  success: function(data){
					 	
					  alert(data);
					 
				  }
				});
			
		}); */
		
// "/2019/04/30/s_99116f04-56d6-48bc-99d0-27c7d29a348a_31414f5c-6662-4dfb-8c3d-6b8c76ca5d4b_캡처.JPG"
	function checkImageType(fileName){
		
		var pattern = /jpg|gif|png|jpeg/i;
		
		return fileName.match(pattern);
		
	}
		
		
	</script>

</body>
</html>