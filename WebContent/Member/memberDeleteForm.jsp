<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>회원탈퇴</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>
	li {
		list-style:none;
	}

	fieldset {
		position:absolute;
		margin-left:auto;
		margin-right:auto;
		top:30%;
		left:35%;
		width:30%;
		height:23%;
	}
	
	#reason {
		/* resize:none;
		width:400px;
		height:150px; */
		
		cols:50px;
		rows:10px;
	}
	
	.button {
		width:15%;
		height:10%;
	}
	
	ul {
		margin-right:auto;
		margin-left:auto;
	}
	.wrapper {
   width: 1200px;
   margin: auto;
   padding: 0;
   align: center;
   float: right;
}
.total{
width:1410px;
align: center;
margin: auto;
padding: 0;
overflow: hidden;
}

fieldset {
	border: 2px solid #e4e4e4;
  /*  border-bottom: 2px solid #e4e4e4;
   border-left:none;
   border-right:none; */
}
</style>

</head>
<body>
<tiles:insertDefinition name="header" />
<div class="total">
<tiles:insertDefinition name="nav" />
<div class="wrapper">
<form method="post" name="userinput" action="/Kkuni/Member/DeletePro.do">
	<fieldset>
		
		<legend><strong>회원탈퇴</strong></legend>
		<ul>
			<li>아이디&emsp;&emsp;&emsp; <input type="text" name="id"></li><br>
			<li>패스워드 &emsp;&emsp;<input type="password" name="pwd"></li><br>
			<li> 
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="submit" class="btn_p" value="탈퇴">&emsp;
				<input type="button" value="취소" class="button" onclick="javascript:history.go(-1)">
			</li>
		</ul>
	</fieldset> 
</div>
</div>
&emsp;&nbsp;
</form>
<tiles:insertDefinition name="footer" />
</body>
</html>