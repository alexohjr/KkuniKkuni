<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
}

.wrapper {
	width: 1200px;
	margin: auto;
	padding: 0;
	align: center;
	float: right;
}

.tablent {
	width: 1200px;
	border-top: 1px solid #444444;
	border-collapse: collapse;
}

.tdnt {
	border-bottom: 1px solid #444444;
	padding: 8px;
}

#submit {
	height: 25px;
}
</style>
</head>
<body>
	<tiles:insertDefinition name="header" />
	<div class="total">
		<tiles:insertDefinition name="navservice" />

		<div class="wrapper">
			<div>
            <h3>공지사항</h3>
              
         </div>
          <hr width="1200" style="border: 0.8px solid #e4e4e4;">
			<table class="tablent">
				<tr>
					<td class="tdnt" align="right">
						<form method="post" action="/Kkuni/Notice/Search.do">
							<input type="text" size="20" name="search" /> <input
								class="btn_p" id="submit" type="submit" value="검색" />
						</form>
					</td>
				</tr>
			</table>
			<c:if test="${count==0}">
				<table class="tablent">
					<tr>
						<td class="tdnt" align="center">게시판에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${count >0}">
				<table class="tablent">
					<tr height="50">
						<td class="tdnt" align="center" width="50">번 호</td>
						<td class="tdnt" align="center" width="250">제 목</td>
						<td class="tdnt" align="center" width="100">작성자</td>
						<td class="tdnt" align="center" width="150">작성일</td>
						<td class="tdnt" align="center" width="50">조 회</td>
						<td class="tdnt" align="center" width="50">삭 제</td>
					</tr>
					<c:forEach var="article" items="${articleList}">
						<tr>
							<td class="tdnt" align="center" width="50"><c:out
									value="${number}" /> <c:set var="number" value="${number-1}" />
							</td>
							<td class="tdnt" width="250"><a
								href="/Kkuni/Notice/NoticeDetail.do?noticeNo=${article.noticeNo}&pageNum=${currentPage}"
								style="text-decoration: none; color: black;">
									${article.nTitle}</a></td>
							<td class="tdnt" align="center" width="100">관리자</td>
							<td class="tdnt" align="center" width="150">${article.nDate}
							</td>
							<td class="tdnt" align="center" width="50">${article.viewCount}</td>
							<td class="tdnt" align="center">
						</tr>
					</c:forEach>
				</table>
				<div align="center">
					<c:set var="pageCount"
						value="${count/pageSize+(count%pageSize==0 ? 0:1)}" />
					<c:set var="pageBlock" value="${10}" />
					<fmt:parseNumber var="result" value="${currentPage / 10}"
						integerOnly="true" />
					<c:set var="startPage" value="${result *10 +1}" />
					<c:set var="endPage" value="${startPage + pageBlock-1}" />
					<c:if test="${endPage > pageCount}">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>
					<c:if test="${startPage>10}">
						<a href="/Kkuni/Notice/NoticeList.do?pageNum=${startPage-10}"
							style="color: black;">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="/Kkuni/Notice/NoticeList.do?pageNum=${i}"
							style="color: black;">[${i}]</a>
					</c:forEach>
					<c:if test="${endpage<pageCount}">
						<a href="Kkuni/Notice/NoticeList.do?pageNum=${startPage+10}"
							style="color: black;">[다음]</a>
					</c:if>
			</c:if>
		</div>
	</div>
	<tiles:insertDefinition name="footer" />
</body>
</html>