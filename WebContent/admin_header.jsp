<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*"  %>
<%
	 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy년MM월dd일");
	 String today = formatter.format(new java.util.Date());
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
		<a href="/Kkuni/Admin/Main.do">
		<img class="header_logo" src="<%=request.getContextPath() %>/images/logo_b.png">
		</a>

	<div class="header_right">
		<span class="date"><%= today %></span>
		<a href="/Kkuni/Member/MemberMain.do"><img class="bt_home" src="<%=request.getContextPath() %>/images/home.png"></a>
	</div>
</div>
</body>
</html>