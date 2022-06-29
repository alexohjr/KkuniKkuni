<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>ID 중복확인</title>
<link rel="stylesheet" href="../css/button.css">
<style>
	.d {
		margin-right:auto;
		margin-left:auto;
	}


</style>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script language="javascript">

	function checkId() {
		var form = eval("document.checkForm");   
		var id = form.eId.value;
		
		if(form.eId.value.length <= 5) {
			alert("아이디는 6글자이상 사용가능합니다.");
			return false;
		} 
		
		var blank_pattern = /^[A-Za-z0-9]{4,10}$/;
		
		if(blank_pattern.test(document.checkForm.eId.value) != true){
	        alert("한글 및 특수문자는 사용불가능 합니다.");
	        return false;
	    }
	}
	
	function seteId() {
		opener.document.userinput.eId.value="${eId}";
		opener.document.userinput.idCheck.value="idCheck";
		opener.document.userinput.eId.readOnly = true;
		self.close();
	}
	
</script> 


</head>
<body>
<c:if test="${check == 1 }">
<div class="d">
<table width="350">
	<tr>
		<td height="50">${eId }는 이미 사용중인 아이디입니다. </td>
	</tr>
</table>
</div>
<div class="d">
<form name="checkForm" method="post" action="/Kkuni/Enterprise/ConfirmId.do" onsubmit="return checkId()">
<table width="350" border="0" cellspacing="0" cellpadding="5">
	<tr>
		<td align="center">
		<p>다른 아이디를 선택하세요.</p>
		<input type="text" size="10" maxlength="12" name="eId">
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
		<p>입력하신 ${eId }는 사용하실 수 있는 ID입니다.</p>
		<input type="button" class="btn_p" value="아이디 사용" onclick="seteId()">
		<input type="button" value="취소" onclick="self.close()">
		</td>
	</tr>
</table>
</div>
</c:if>
</body>
</html>
