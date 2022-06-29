<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>ID 중복확인</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>
	.d {
		margin-right:auto;
		margin-left:auto;
	}


</style>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<c:if test="${check == 1 }">
<div class="d">
<table width="350">
	<tr>
		<td height="50"><h3>${id}는 이미 사용중인 아이디입니다.</h3></td>
	</tr>
</table>
</div>
<div class="d">
<form name="checkForm" method="post" action="/Kkuni/Member/ConfirmId.do" onsubmit="return checkId()">
<table width="350" border="0" cellspacing="0" cellpadding="5">
	<tr>
		<td align="center">
		<h4>다른 아이디를 선택하세요.</h4>
		<input type="text" size="10" maxlength="12" name="userid">
		<input type="submit" class="btn_p" value="ID중복확인">
		</td>
	</tr>
</table>
</form>
</div>
</c:if>

<c:if test="${check == -1 }">
<div class="d">
<table width="400">
	<tr>
		<td align="center">
		<p>${id}는 사용하실 수 있는 ID입니다.</p>
		<input type="button" class="btn_p" value="아이디 사용" onclick="setid()">
		<input type="button" value="취소" onclick="self.close()">
		</td>
	</tr>
</table>
</div>
</c:if>
</body> 
</html>
<script language="javascript">

	function checkId() {
		var form = eval("document.checkForm");   
		var id = form.userid.value;
		
		if(form.userid.value.length <= 5) {
			alert("아이디는 6글자이상 사용가능합니다.");
			return false;
		} 
		/*  /^[A-Za-z0-9]{4,10}$/ */

		var blank_pattern = /^[A-Za-z0-9]{4,10}$/;
	    if( blank_pattern.test(form.userid.value) != true){
	       alert("한글 및 특수문자는 사용불가능 합니다.");
	       return false;
	    }
	}
	
	
	function setid() {
		opener.document.userinput.userid.value="${id}";
		opener.document.userinput.idCheck.value="idCheck";
		opener.document.userinput.userid.readOnly = true;
		self.close();
	}
	
</script>