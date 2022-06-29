<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.size{
		width: 1500px;
	}
	.myPage_category{
	width: 80px;
	margin: auto;
}
</style>


</head>  
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="enterprise_header" />
<tiles:insertDefinition name="enterprise_menu" />
<div style="background-color: white; width: 1705px;
			min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
	<div class="pageSize size">
	<br>
		<div align="left">
			<h2>똑똑친구</h2>
			<hr>
		</div>
		
		<div style="verflow:scroll; height:500px">
		<c:forEach var="List" items="${ChatList}">
		<table class="table_ho" width="1500"  style=" border:0.8px solid #e4e4e4">
			<tr>
				<td rowspan="2" width="100" height="100" >
				<a href="#" onclick="window.open('/Kkuni/Chat/ChatEnterprise.do?mId=${List.mId}&eId=${List.eId}','1:1채팅','width=1000,height=700,fullscreen=no')" style="text-decoration:none; color: black;">
				<img class="myPage_category" src="<%=request.getContextPath()%>/images/rantal.png"></a></td>
				<td>${List.eName }--${List.mId}</td>
			</tr>
			<tr>
				<td>${List.contents}</td>
			</tr>
		</table>
		</c:forEach>
		
		</div>
		</div>
	</div>
</body>
</html>