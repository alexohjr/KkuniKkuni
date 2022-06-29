<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>이메일 중복확인</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>
	table {
		margin-right:auto;
		margin-left:auto;
	}
</style>
</head>
<body>
<c:if test="${check eq 1 }">
<table width="270">
	<tr>
		<td height="39">${mail } 는 이미 사용중인 이메일입니다. </td>
	</tr>
</table>
<form name="checkForm" method="post" action="/Kkuni/Member/MailCheck.do">
<table width="270">
	<tr>
		<td align="center">
		<p>다른 이메일을 입력하세요.</p>
		<input type="text" size="30" name="email">
		<input type="submit" class="btn_p" value="중복확인">
		</td>
	</tr>
</table>
</form>
</c:if>

<c:if test="${check eq -1 }">
<table width="270" border="0" cellspacing="0" cellpadding="5">
	<tr>
		<td align="center">
		<p>입력하신 ${mail }은 사용하실 수 있습니다.</p>
		<input type="button" class="btn_p" value="이메일 사용" onclick="setMail()">
		</td>
	</tr>
</table>
</c:if>
</body>
</html>
<script language="javascript">

	function setMail() {
		opener.document.userinput.email.value="${mail}";
		opener.document.userinput.mailChecked.value="Checked";
		opener.document.userinput.email.readOnly = true;
		opener.document.getElementById('emailcheck').style.display = "none";
		opener.document.getElementById('emailconfirm').style.display = "inline";
		self.close();
	}
	
</script>


</body>
</html>