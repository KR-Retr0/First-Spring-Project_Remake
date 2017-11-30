<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Retr0 World!</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div aling="center">
		<form action="/login_req" method="POST" onsubmit="return login_check()">
			id : <input type="text" name="id" class="id"><br />
			pw : <input type="text" name="pw" class="pw"><br />
			<input type="button" value="회원가입" onclick="location.href='/join'"><input type="submit" value="로그인" onsubmit="login_check()">
		</form>
	</div>
</body>
<script>


	function login_check(){
		if($(".id").val()==""){
			alert("아이디를 입력하세요!");
			$(".id").focus();
			return false;
		}
		if($(".pw").val()==""){
			alert("패스워드를 입력하세요!");
			$(".pw").focus();
			return false;
		}
		return true;
	}
</script>
</html>