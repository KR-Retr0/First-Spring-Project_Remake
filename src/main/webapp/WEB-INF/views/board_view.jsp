<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function(){
	$("#input_img").on("change",handleImgFileSelect);
});

function handleImgFileSelect(e){
	var files=e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert("확장자는 이미지 확장자만 가능");
			return;
		}
		sel_file=f;
		
		var reader = new FileReader();
		reader.onload=function(e){
			$("#img").attr("src",e.target.result);
		}
		reader.readAsDataURL(f);
	})
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="${image.path }">
	<form action = '/image_edit' method = "POST" enctype="multipart/form-data">
		<input type="file" name="image" id="input_img" value="사진"/>
		<input type="hidden" name="image_name" value="image">
		<div class="img_wrap">
			<img id="img"/>
		</div>
	</form>
</body>
</html>