<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>로그인</title>
<link rel="stylesheet" href="../css/button.css">
<style>
	
	body {
		/* line-height: 1.3em; */
		width: 1920px;
		height:1080px;
	}
		
	div {
		margin-left:auto;
		margin-right:auto;
	}

	img {
		margin-left:auto;
		margin-right:auto;
		width:40%;
	}
	
	#f1 {
		margin-left:auto;
		margin-right:auto;
		width:22%;
		height:300px;
		border:1px;
	}

	li {
		margin-left:auto;
		margin-right:auto;
	}
	
	ul {
		list-style:none;
	}
	
	br {
		display: block;
		margin: 10px 0;
		line-height: 22px;
		content: " ";
	}
	
	#login {
		width:120px;
		height:50px;
	}
	
	input[type=button] {
		height:25px;
	}

</style>
<script language="javascript">
	function begin(){
		document.userinput.userid.focus();
	}
	function checkIt() {
		if(!document.userinput.userid.value) {
			alert("이름을 입력하지 않으셨습니다");
			document.userinput.userid.focus();
			return false;
		}
		if(!document.userinput.userpwd.value) {
			alert("비밀번호를 입력하지 않으셨습니다");
			document.userinput.userpwd.focus();
			return false;
		}
	}
	
	function searchId() {

		url = "/Kkuni/Enterprise/SearchIdForm.do";

		window.open(url, "post", "toolbar=no, left=770%, top=400%, width=435, height=200, directories=no, status=yes, scrollbars=yes, menubar=no");
	}
	
	function searchPw() {
		url = "/Kkuni/Enterprise/SearchPwForm.do";
		window.open(url, "post", "toolbar=no, left=770%, top=400%, width=435, height=200, directories=no, status=yes, scrollbars=yes, menubar=no");
	}
</script>
</head>
<body>


<div align="center">
<a href="/Kkuni/Member/MemberMain.do"><img src="<%=request.getContextPath() %>/image/insertlogo2.png"></a>
</div>
<h2 align="center"></h2>

<form method="post" name="userinput" action="/Kkuni/Enterprise/LoginPro.do" onsubmit="return checkIt()">

<fieldset id="f1">
<br><br>
	<div>
	<h3>업체 로그인</h3>
	<table>
		<tr>
		<td><br><label>아이디 &emsp;&emsp;<input type="text" name="eId"></label><td>
		<td rowspan=2><input type="submit" id="login" class="btn_p" value="로그인"></td>
		</tr>
		<tr>
		<td><label>비밀번호  &emsp;<input type="password" name="ePw"></label></td>
		</tr>
		<tr>
		<td colspan=4>
			&emsp;&emsp;
			<input type="button" class="bt" value="아이디찾기" onclick="searchId()">
			<input type="button" class="bt" value="패스워드찾기" onclick="searchPw()">
			<input type="button" class="bt" value="업체등록" onclick="document.location.href='/Kkuni/Enterprise/InsertForm.do'">
		</td>
		</tr>
	</table>
	</div>
</fieldset>
</form>
</body>
</html>

