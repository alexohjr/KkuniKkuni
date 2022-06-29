<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>1:1 문의</title>
<style type="text/css">
.wrapper {
	width: 1200px;
	margin: auto;
	padding: 0;
	align: center;
}

table {
	border-collapse: collapse;
	vertical-align: middle;
}

th {
	border-top: 2px solid #444444;
	border-bottom: 2px solid #444444;
	padding: 8px;
	text-align: center;
}

td,tr {
	border-top: 1.2px solid #444444;
	border-bottom: 1.2px solid #444444;
	padding: 8px;
}

li, ul {
	list-style-type: none;
	text-align: right;
}
</style>
</head>
<body>
<tiles:insertDefinition name="header" />
<tiles:insertDefinition name="nav" />
	<div class="wrapper">
		<section>

			<div>
				<h3>1:1 문의</h3>
					
			</div>

			<hr width="1200" style="border: 0.8px solid #e4e4e4;">
			<div id="help">
				<div style="text-align: center;">
					<img src="<%=request.getContextPath()%>/images/help.png">
				</div>

				<form method="post" action="/Kkuni/Question/Search.do" >
					<ul>
						<li><input type="text" name="search" size="15" maxlength="50" />
							<input type="submit" value="검색" />
							<input type="hidden" name="mId" value="${mId }">
						</li>
					</ul>
				</form>
			</div>
			<div margin="0">


				<table width="1200" text-align="center">
					<tr height="30">
						<th width="50">순 번</th>
						<th width="50">분 류</th>
						<th width="250">제 목</th>
						<th width="100">작성일</th>
						<th width="50">상 태</th>
					</tr>

					<tr height="30">
						<td align="center" width="50">1</td>
						<td align="center" width="50">계정</td>
						<td align="center" width="50">로그인이 안돼요</td>
						<td align="center" width="50">2018/08/27</td>
						<td align="center" width="50">답변대기</td>
					</tr>
					

				</table>

				<div style="width: 100%; margin-top: 10px;">
					<ul>
						<li><input type="button" value="등록" onclick="#">
						</li>
					</ul>
				</div>
				<div style="text-align: center; text-decoration: none;">
					<a href="#">[이전]</a> <a href="#">1</a> <a href="#">[다음]</a>
				</div>
			</div>
		</section>
	</div>

</body>
</html>