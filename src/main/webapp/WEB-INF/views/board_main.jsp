<%@page import="com.firstweb.Vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% UserVo vo = (UserVo)session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Retr0 Gallery</title>
</head>
<body>
	<h2>방명록</h2>
	<form action="/memo_ins" method="post">
	<input type="hidden" name="writter_id" value=<%= vo.getId() %>/>
	<input type="text" name="content"/>
	<input type="submit" value="올리기"/>
	</form>
</bod`y>
</html>