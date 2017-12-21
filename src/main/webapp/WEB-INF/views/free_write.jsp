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
	<form action="/free_ins" method="post" enctype="multipart/form-data" onsubmit="return submitContents();">
		제목 : <input type="text" name="title" /><br />
		<input type="hidden" name="writter_id" value=<%= vo.getId() %> />
		<br />
		
		<br />
		
		<textarea name = "content" id="textAreaContent" rows="10" cols="100" data-korea="내용"></textarea><br />
		<input type="radio" name="permission" class="permission" value="0">공개
		<input type="radio" name="permission" class="permission" value="1">비공개
		<br /><input type="submit">
	</form>
</body>
<script>
	$('#content').focus(function(){
		$(this).text('');
	})
	
</script>
<!-- Smart Editor -->
<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>	 
 
<!-- Smart Editor -->
<script type="text/javascript">
 
var oEditors = [];
$(function(){
	
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "textAreaContent",
	    sSkinURI: "../se2/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});
	
});

 
//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", [ ]);
    
    return true;
 
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = "<img src='/picture/"+filepath+"'>";
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}
 
</script>

</html>