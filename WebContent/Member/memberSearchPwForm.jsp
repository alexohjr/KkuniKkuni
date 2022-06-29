<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<style>
	ul {
		list-style:none;
	}
	
	div {
		margin-left:auto;
		margin-right:auto;
	}

	br {
		display: block;
		margin: 10px 0;
		line-height: 22px;
		content: " ";
	}

</style>

<script>
	function checkIt() {
		if(!document.userinput.id.value) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(!document.userinput.email.value) {
			alert("이메일을 입력해주세요.");
			return false;
		}
	}

</script>

</head>
<body>

<form method="post" name="userinput" action="/Kkuni/Member/SearchPwPro.do" onsubmit="return checkIt()">

<div>
<fieldset>
<h4><strong>&emsp;비밀번호 찾기</strong></h4>
<h6>&emsp;&nbsp;임시비밀번호가 이메일로 발송됩니다.</h6>
<ul>
	<li>&emsp;&emsp;아이디 <input type="text" name="id" style="width:200px"></li><br>
	<li>&emsp;&emsp;이메일 <input type="text" name="email" style="width:200px"></li>
	<li>&emsp;&emsp;&emsp;&emsp;&emsp;
		<input type="submit" class="btn_p" value="패스워드찾기">
		<input type="button" class="btn_b" value="취소" onclick="self.close()">
	</li>
</ul>
</fieldset>
</div>

</form>

</body>
</html>