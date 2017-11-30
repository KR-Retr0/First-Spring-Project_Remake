<%@page import="com.firstweb.Vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% UserVo vo = (UserVo)session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 글쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<form action="/free_ins" method="post" onsubmit="return freeEmptyCheck()" enctype="multipart/form-data">
		제목 : <input type="text" name="title" /><br />
		<input type="hidden" name="writter_id" value=<%= vo.getId() %> />
		<br /><input type="file" name="image" value="사진"/>
				<input type="hidden" name="image_name" value="image">
		<br />
		<textarea id="content" name="content" cols="90" rows="10" data-korea="내용">내용을 입력하세요.</textarea><br />
		<input type="radio" name="permission" class="permission" value="0">공개
		<input type="radio" name="permission" class="permission" value="1">비공개
		<br /><input type="submit">
	</form>
</body>
<script>
	$('#content').focus(function(){
		$(this).text('');
	})
	
	function freeEmptyCheck(){
		var value = true;
		$("input[type=text]").each(function(){
			if($(this).val().trim() == ''){
				alert($(this).attr("data-korea")+"를 입력하지 않으셨습니다.");
				$(this).focus()
				value=false;
			}
		})
		$("textarea").each(function(){
			if($(this).val().trim() == ''){
				alert($(this).attr("data-korea")+"를 입력하지 않으셨습니다.");
				$(this).focus()
				value=false;
			}
		})

		if(value==false){
			return value;
		}
		var Object = $(".permission");	
		var check="0";
		for(var j=0;j<Object.length;j++){
			if($(Object[j]).is(":checked")){
				check="1";
				break;
			}
		}
		if(check=="0"){
			alert("공개범위를 체크해 주세요!");
			value = false;
			break;
		}
		return value;
	}
</script>
</html>