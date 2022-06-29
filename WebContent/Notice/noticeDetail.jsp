<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html >
<html>
<head>
<title>공지사항</title>
<link rel="stylesheet" href="../css/button.css">
<style>
.total {
	width: 1410px;
	align: center;
	margin: auto;
	padding: 0;
	overflow: hidden;
	margin-top: -18px;
}

.wrapper {
	width: 1200px;
	margin: auto;
	padding: 0;
	align: center;
	float: right;
	margin-bottom: 30px;
}

.tdqu {
	border: 1px solid #e4e4e4;
}

.trqu {
	border: 1px solid #e4e4e4;
}
</style>
</head>
<body>
	<tiles:insertDefinition name="header" />
	<div class="total">
		<tiles:insertDefinition name="navservice" />

		<div class="wrapper">
			<section style="margin-left: 10px;">
				<div>
					<h3>공지사항</h3>

				</div>
				<hr width="1200" style="border: 0.8px solid #e4e4e4;">
				<table width="1150"
					style="border: 1px solid #e4e4e4; margin-top: 20px;">
					<tr class="trqu">
						<td class="tdqu" style="width: 150px;" align="center">분류</td>
						<td class="tdqu" style="width: 400px;">${nSort}
						<td class="tdqu" style="width: 150px;" align="center">작성자</td>
						<td class="tdqu" align="center">관리자</td>
					</tr>
					<tr class="trqu">
						<td class="tdqu" align="center">제목</td>
						<td class="tdqu" colspan="3">${article.nTitle}</td>
					</tr>
					<tr class="trqu">
						<td class="tdqu" align="center">첨부파일</td>
						<td class="tdqu" colspan="3">${article.nFile}</td>
					</tr>
					<tr class="trqu">
						<td class="tdqu" colspan="4">
							<div rows="10" cols="30"
								style="overflow-x: hidden; overflow-y: auto; width: 100%; height: 500px">${article.nContents}
								<img
									src="${pageContext.request.contextPath}${article.nRealpath}"
									width="100%" height="100%">
							</div>
						</td>
					</tr>
				</table>
				<div align="right" style="width: 1150px;">
					<input class="btn_b" type="button" value="목록"
						onclick="document.location.href='/Kkuni/Notice/NoticeList.do?pageNum=${pageNum}'">
				</div>
		</div>
	</div>
</body>
</html>