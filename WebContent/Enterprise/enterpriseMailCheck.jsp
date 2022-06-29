<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>이메일 중복확인</title>
<link rel="stylesheet" href="../css/button.css">

<style>
	table {
		margin-right:auto;
		margin-left:auto;
	}
</style>

<script language="javascript">  
	
	function setsetMail() {
		opener.document.userinput.eMail.value="${eMail}";
		opener.document.userinput.mailChecked.value="Checked";
		opener.document.userinput.eMail.readOnly= true;
		opener.document.getElementById('emailcheck').style.display = "none";
		opener.document.getElementById('emailconfirm').style.display = "inline";
		self.close();
	}
	   
	function entMailCheck() {
		if(!document.checkForm.email.value) {
			alert("이메일을 입력하세요.");
			return false;
		}
	}
	
	/* <input type="button" name="emailcheck" value="중복체크" onclick="mailCheck()" style="display:inline"> */
</script>

</head>
<body>
<c:if test="${check == 1 }">
<table width="270">
	<tr>
		<td height="39">${eMail } 는 이미 사용중인 이메일입니다. </td>
	</tr>
</table>
<form name="checkForm" method="post" action="/Kkuni/Enterprise/MailCheck.do" onsubmit="return entMailCheck()">
<table width="270">
	<tr>
		<td align="center">
		<p>다른 이메일을 입력하세요.</p>
		<input type="text" size="30" name="eMail">
		<input type="submit" class="btn_p" value="중복확인">
		</td>
	</tr>
</table>
</form>
</c:if>

<c:if test="${check == -1 }">
<table width="270" border="0" cellspacing="0" cellpadding="5">
	<tr>
		<td align="center">
		<p>입력하신 ${eMail }은 사용하실 수 있습니다.</p>
		<input type="button" class="btn_p" value="이메일사용" onclick="setsetMail()"> 
		</td>
	</tr>
</table>
</c:if>
</body>
</html>
</body>
</html>