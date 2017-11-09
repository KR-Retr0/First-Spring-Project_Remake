<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Will you Join us?</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<b1>회원가입</b1>
	
	<form action="/join_req" method="POST" onclick="return JoinCheck()">
		ID : <input type="text" name="id" class="TextField" data-korea="아이디"><br />
		PW : <input type="text" name="pw" class="TextField" data-korea="패스워드"><br />
		PW 확인 : <input type="text" class="pwCheck"><br />
		닉네임 : <input type="text" name="name" class="TextField" data-korea="닉네임"><br />
		성별 : 
	</form>
</body>
</html>