<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<title>패스워드 찾기</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">


<body> 
<div align="center"> 
	<form method="post">
		<c:if test="${check == 1}">
		
		<p>${id }님의 임시비밀번호를</p>
		<p><strong>${email }</strong>으로 보냈습니다.</p>
		<a href="javascript:this.close();">
		<input type="button" value="닫기">
		</a>
		</c:if>
		
		<c:if test="${check == -1}">
		아이디 또는 이메일이 틀렸습니다.
		<input type="button" value="다시 입력하기" onclick="history.go(-1)">
		</c:if>
	</form>
</div>
<!-- onclick="javascript:window.location='/Board/logon/searchId.do'" -->
</body>



</html>