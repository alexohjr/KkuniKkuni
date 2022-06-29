<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>관심상품</title>
<link href="../css/header.css" rel="stylesheet" type="text/css">

<style>
/* 목록의 글머리에 아무것도 표시하지 않음 */
ul {
	list-style-type: none;
	/* 전체 목록과 텍스트 가운데 정렬*/
}

#item {
	text-align: center;
}
/* 상품명인 '플리린제' 글자 볼드체*/
#subject {
	font-weight: bold;
}
/* 상품 설명인 '잎에 빨간 테두리' 글자 색상 빨간색*/
#price {
	font-weight: bold;
	color: #DB0000;
}

.total {
	width: 1410px;
	align: center;
	margin: auto;
	padding: 0;
	overflow: hidden;
}

.wrap {
	width: 1200px;
	margin: 0px;
	padding: 0;
	margin-left: auto;
}

#list {
	top: 10px;
	width: 24%;
	height: 400px;
	display: inline-block;
	margin: 0 auto;
}
</style>
</head>
<body>

	<tiles:insertDefinition name="header" />
	<div class="total">
		<tiles:insertDefinition name="nav" />
		<div class="wrap">
			<div>
				<h3>관심상품</h3>
			</div>
			<c:if test="${count ne 0 }">
			<h6 style="float: right; margin-top: -24; margin-bottom: 12px;">
			관심상품에 총 ${count}개의 상품이 있습니다.</h6>
			</c:if>



			<hr width="100%" style="border: 0.8px solid #e4e4e4;">

			<c:forEach var="cart" items="${cartList }">
			<div id="list">
				<ul id="item">
					<li><a href="/Kkuni/Item/Detail.do?mId=${mId }&itemNo=${cart.itemNo }&pageNum=${currentPage}">
							<img src="${pageContext.request.contextPath}${cart.thumbnail}"
							width=230px; height=300px;>
					</a></li>
					<li id="subject">${cart.itemId }</li>
					<li id="price">${cart.price } 원 / 1일</li>
				</ul>
			</div>
			</c:forEach>


			<div align="center">
				<c:if test="${count > 0}">
			<c:set var="pageCount"
				value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
			<c:set var="pageBlock" value="${10}" />
			<fmt:parseNumber var="result" value="${currentPage / 10}"
				integerOnly="true" />
			<c:set var="startPage" value="${result * 10 + 1}" />
			<c:set var="endPage" value="${startPage + pageBlock-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>

			<c:if test="${startPage > 10}">
				<a href="/Kkuni/Cart/List.do?mId=${mId }&pageNum=${startPage - 10 }">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Kkuni/Cart/List.do?mId=${mId }&pageNum=${i}">[${i}]</a>
			</c:forEach>

			<c:if test="${endPage < pageCount}">
				<a href="/Kkuni/Cart/List.do?mId=${mId }&pageNum=${startPage + 10}">[다음]</a>
			</c:if>
		</c:if>
			</div>
		</div>
	</div>
	<tiles:insertDefinition name="footer" />
</body>