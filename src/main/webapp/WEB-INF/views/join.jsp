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
	
	<form action="/join_req" method="POST" onsubmit="return JoinCheck()">
		<input type="hidden" id="jsonStr" name="jsonStr"><!-- SNS JSON 전송을 위한 히든 필드 -->
		ID : <input type="text" name="id" class="id" id="id" data-korea="아이디">
		<input type = "button" value = "중복확인" onclick = "IdCheck()"/><br />
		PW : <input type="text" name="pw" class="pw" data-korea="패스워드"><br />
		PW 확인 : <input type="text" class="pwCheck" data-korea="패스워드 확인란"><br />
		닉네임 : <input type="text" name="name" class="pw" data-korea="닉네임"><br />
		성별 : <input type = "radio" class = "gender" name="gender" value = "male"/>남자
		<input type = "radio" class = "gender" name="gender" value = "female"/>여자
		<br /> 취미<br />
		<input type="checkbox" class="like" name="like" value="soccer">축구
		<input type="checkbox" class="like" name="like" value="basketball">농구
		<input type="checkbox" class="like" name="like" value="baseball">야구
		<input type="checkbox" class="like" name="like" value="game">게임
		<br />
		SNS
		<div id="field"></div>
		<input type=button value="추가하기" onclick=add_item()>
		<input type=button value="제거하기" onclick=del_item()>
		<br />가입경로<br />
		<input type="radio" name="joinfrom" id="joinfrom" class="joinfrom" value="search">검색
		<input type="radio" name="joinfrom" id="joinfrom" class="joinfrom" value="recommend">추천
		<input type="radio" name="joinfrom" id="joinfrom" class="joinfrom" value="etc">기타<br>
		<input type="submit" value="submit"/>
		</form>
</body>

<script>
	function JoinCheck(){ //회원가입 Submit시 체크
		if(emptyCheck() && $("#id").prop("readonly") && checkBoxCheck()){ //빈칸이 없고 ID 중복확인이 완료되었나?
			MakeJson();
			return true;
		} else if($("#id").prop("readonly")==false){
			alert("ID 중복확인을 해주세요!");
			return false;
		}
		return false;
	}
	
	function IdCheck(){ //ID 중복확인
		var element = $("#id");
		$.ajax({
			url : '/join.ajax?id='+document.getElementById("id").value,
			type : 'get',
			success:function(data){
				if($("#id").val()==''){
					alert("ID를 입력하세요.");
					$("#id").focus();
				}else{
					alert(data)
					if(data=="사용가능"){
						element.attr("readonly", true);
						element.css("background-color", "lightgray")
					}else{
						alert("중복되어 사용이 불가능한 ID입니다.")
						element.val("");
						element.focus();
					}
				}
			}
		})
	}
	
	function emptyCheck(){//비어있는 텍스트필드 확인
		var value = true;
		$("input[type=text]").each(function(){
			if($(this).val().trim() == ''){
				alert($(this).attr("data-korea")+"를 입력하지 않으셨습니다.");
				$(this).focus()
				value=false;
				return false;
			}
		})
		return value;
	}
	
	function MakeJson(){//SNS 데이터를 JSON으로 변환하여 저장
		var jsonOb= {};
		var jsonKey="";
		var jsonValue="";
		for(var i=0;i<$("#sns_name").size();i++){
			jsonKey = $('#sns_name').val();
			jsonValue = $('#sns').val();
			jsonOb[jsonKey]= jsonValue;
		}
		
		var jsonStr = JSON.stringify(jsonOb);
		$("#jsonStr").val(jsonStr);
	}
	
	function add_item(){
		var div=document.createElement('div');
		div.innerHTML ="<input type=text class='sns_name' style='width:100px' data-korea='SNS 이름'>"+
		"<input type=text class='sns' data-korea='SNS 주소'><br />";
		document.getElementById('field').appendChild(div);
	}
	function del_item(){
		$(".sns_name").eq($(".sns_name").size()-1).parent().remove();
	}
	
	function checkBoxCheck(){//체크박스 및 라디오 확인
		var array=[".gender",".like",".joinfrom","성별","취미","가입 경로"];
		var flag = true;
		for(var i=0;i<3;i++){
			var arrayObject = $(array[i]);	
			var check="0";
			for(var j=0;j<arrayObject.length;j++){
				if($(arrayObject[j]).is(":checked")){
					check="1";
				}
			}
			if(check=="0"){
				unchecked = array[i+3];
				alert(unchecked + "을(를) 체크해 주세요!");
				flag = false;
				break;
			}
		}
		return flag;
	}
	
</script>

</html>