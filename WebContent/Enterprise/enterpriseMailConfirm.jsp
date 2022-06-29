<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>이메일 인증</title>
<link rel="stylesheet" href="../css/button.css">
<style>
	#submit {
		height:25px;
	}
	
</style>

<script>
	function checkT() {
		var userinput = eval("document.userinput");

		if (!userinput.code.value) {
			alert("인증번호를 입력해주세요");
			return false;
		}
		
		if (userinput.code.value != userinput.codeCheck.value) {
			alert("인증번호가 틀렸습니다.");
			return false;
		
		} else {
			opener.document.userinput.codeCheck.value = "codeChecked";
			opener.document.userinput.emailconfirm.value = "인증완료";
			opener.document.userinput.eMail.readOnly = true;
			opener.document.getElementById('emailconfirm').style.display = "none";
			opener.document.getElementById('confirmOk').style.display = "inline";
			self.close();
		}
	}
	
	function closee() {
		self.close();
	}
</script>
</head>

<body>


	<form method="post" name="userinput" onsubmit="return checkT()">
		&emsp;
		<div>
		<h3>이메일로 인증번호가 발송되었습니다.</h3>
		인증번호 <input type="text" name="code"> 
		<input type="submit" id="submit" class="btn_p" value="인증"> 
		<input type="hidden" name="codeCheck" value="${code}"> <br> 
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<input type="button" value="닫기" onclick="closee()">
		</div>
	</form>
</body>
</html>

