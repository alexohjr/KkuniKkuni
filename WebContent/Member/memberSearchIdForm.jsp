<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>아이디찾기</title>
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
		if(!document.userinput.eMail.value) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		if(!document.userinput.eName.value) {
			alert("이름을 입력해주세요.");
			return false;
		}
	}
</script>

</head>
<body>

<form method="post" name="userinput" action="/Kkuni/Member/SearchIdPro.do" onsubmit="return checkIt()">


<div>
<fieldset>

<h4><strong>아이디 찾기</strong></h4>
	<ul>
		<li>&emsp;&emsp;이메일 <input type="text" name="email" style="width:200px"></li><br>
		<li>&emsp;&emsp;이름  &emsp;<input type="text" name="name" style="width:200px"></li>
		<li>&emsp;&emsp;&emsp;&emsp;&emsp;
			<input type="submit" class="btn_p" value="아이디찾기">
			<input type="button" class="btn_b" value="취소" onclick="self.close()">
		</li>
	</ul>
</fieldset>
</div>

</form>
</body>
</html>