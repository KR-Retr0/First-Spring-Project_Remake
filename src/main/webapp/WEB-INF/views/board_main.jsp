<%@page import="com.firstweb.Vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% UserVo vo = (UserVo)session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Retr0 Gallery</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h2>방명록</h2>
	<form action="/memo_ins" method="post" onsubmit="return textEmptyCheck()">
		<input type="hidden" name="writter_id" value=<%= vo.getId() %> />
		<input type="text" name="content" data-korea="내용"/>
		<input type="submit" value="올리기"/>
	</form>
	
	<h2>자유게시판</h2>
	<input type="button" value="글쓰기" onclick="location.href='/free_write'"/>
</body>
<script>
	function textEmptyCheck(){
		var value = true;
		$("input[type=text]").each(function(){
			if($(this).val().trim() == ''){
				alert($(this).attr("data-korea")+"를 입력하지 않으셨습니다.");
				$(this).focus()
				value=false;
			}else{
				alert(1);
			}
		})
		return value;
	}

</script>
</html>