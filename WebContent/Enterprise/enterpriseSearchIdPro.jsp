<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<title>아이디 찾기</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/button.css">


<body bgcolor="${bodyback_c }"> 
<div align="center"> 
	<form method="post">
		<c:if test="${check == 1}">
		
		<p>${name }님의 아이디는 <b>${id }</b>입니다</p>
		<a href="javascript:this.close();">
		<input type="button" value="닫기">
		</a>
		</c:if>
		
		<c:if test="${check == -1}">
		이메일 또는 업체명이 틀렸습니다.
		<input type="button" value="다시 입력하기" onclick="history.go(-1)">
		</c:if>
	</form>
</div>
<!-- onclick="javascript:window.location='/Board/logon/searchId.do'" -->
</body>



</html>