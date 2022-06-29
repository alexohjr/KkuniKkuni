<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</style>

</head>
<body>

<c:if test="${check == 1 }">
	<script>
		alert("회원탈퇴가 완료되었습니다.");
		location.href="/Kkuni/Member/MemberMain.do";

		/* history.go(-1); */
	</script>
</c:if>

<c:if test="${check == -1 }">
	<script>
		alert("아이디 또는 비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>

&emsp;&nbsp;

</body>
</html>