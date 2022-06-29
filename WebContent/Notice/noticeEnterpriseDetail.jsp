<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>
<link href="<%=request.getContextPath()%>/css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
<style>
table,td,tr,td{
	border-color:#e4e4e4;
}



</style>
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="enterprise_header" />
<tiles:insertDefinition name="enterprise_menu" />
<div style="background-color: white; width: 1680x; min-height: 900px; margin: 10px; margin: 80px 0px 0px 200px;">
	<div style="width:1500px; position: absolute; left: 300px; " >
			<table class="ta_line"  width="1500" align="center">
				<tr>
					<td colspan="8"><h2>공지사항</h2>
				</tr>
			</table>
			<hr width="1500">		
			<table class="ta_line"  width="1500" border="1" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2" align="center">분류</td>
					<td colspan="5">${nSort}
					<td style="background-color:#e4e4e4;" colspan="2" align="center">작성자</td>
					<td colspan="3" align="center">관리자</td>
				</tr>
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2" align="center" >제목</td>
					<td colspan="10">${article.nTitle}
					</td>
				</tr>
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2"align="center">첨부파일</td>
					<td colspan="10">${article.nFile}
					</td>
				</tr>
				<tr>
					<td colspan="10">
						<div rows="10" cols="30" style="overflow-x:hidden; overflow-y:auto; width: 100%; height:500px">${article.nContents}
						<img src="${pageContext.request.contextPath}${article.nRealpath}" width="100%" height="100%">
						
						</div>
					</td>
				</tr>
			</table>
			<div align="right">
				<input class="btn_b" type="button" value="목록" onclick="document.location.href='/Kkuni/Notice/NoticeEnterpriseList.do?pageNum=${pageNum}&eId=${eId}'">
			</div>
	</div>
</div>
</body>
</html>