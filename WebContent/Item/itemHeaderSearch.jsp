<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>상품리스트</title>
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

.wrap {
	width: 1440px;
	min-height: 500px;
	margin: auto;
	padding: 0;
}

#list {
	top: 10px;
	width: 24%;
	height: 400px;
	display: inline-block;
	margin: 0 auto;
}

ul {
	display: table;
	margin: auto;
	padding: 0;
}

#id_nav {
	float: left;
	margin: 20px 0px 0px 20px;
}

#main_lnb>ul {
	overflow: hidden;
}

#main_lnb>ul>li {
	float: left;
}

#main_lnb>ul>li>a {
	display: block;
	padding: 10px 20px;
	border: 1px solid #E4E4E4;
	text-decoration: none;
	color: #3E4149;
}

#main_lnb>ul>li>a:hover {
	background: #FD999A;
	color: white;
	text-decoration: none;
}

#main_lnb>ul>li:first-child>a {
	border-radius: 8px 0 0 8px;
}

#main_lnb>ul>li:last-child>a {
	border-radius: 0 8px 8px 0;
}

#nav {
	border-radius: 5px 0 0 5px;
	width: 1440px;
	height: 80px;
	border: 1px solid #E4E4E4;
}
</style>
</head>
<body>

	<tiles:insertDefinition name="header" />
	<div class="wrap">
		<section>
			<article>
				<h4 style="float: left;">검색결과  총 ${count}개의 상품이 있습니다.</h4>
				<hr style=" width : 1440px;" color="#E4E4E4" align="center">
			</article>
			<article>

				<c:forEach var="Item" items="${itemLists}">
					<div id="list">
						<a href="#" style="text-decoration: none;"></a>
						<ul id="item">
							<li>
							<a href="/Kkuni/Item/Detail.do?mId=${mId }&itemNo=${Item.itemNo }&pageNum=${currentPage}">
							<img src="${pageContext.request.contextPath}${Item.tRealpath}" width=230px; height=300px;>
							</a>
							</li>
							<li id="subject">${Item.itemId}</li>
							<li id="price">${Item.price} 원 / 1일</li>
						</ul>
					</div>
				</c:forEach>
			</article>
			<div align="center">
         <c:if test="${count > 0 }">
         <c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1 )}"/>
         <c:set var="pageBlock" value="${12 }"/> <!-- 페이징 넘버  -->
         <fmt:parseNumber var = "result" value ="${currentPage / 12}" integerOnly = "true" />
       <c:set var = "startPage" value ="${result * 10 + 1}" />
       <c:set var = "endPage" value ="${startPage + pageBlock-1}" /> <!-- 보여질 페이징 넘버 -->
       <c:if test="${endPage > pageCount}">
         <c:set var = "endPage" value ="${pageCount}" />
       </c:if>
      
      <c:if test="${startPage > 10}">
         <a href = "/Kkuni/Item/HeaderSearch.do?pageNum=${startPage - 10}&search=${search}" class="abc">[이전]</a>
       </c:if>
      
       <c:forEach var="i" begin="${startPage}" end= "${endPage}">
         <a href = "/Kkuni/Item/HeaderSearch.do?pageNum=${i}&search=${search}" class="abc">[${i}]</a>
       </c:forEach>
      
       <c:if test="${endPage < pageCount}">
         <a href = "/Kkuni/Item/HeaderSearch.do?pageNum=${startPage + 10}&search=${search}" class="abc">[다음]</a>
       </c:if>
      </c:if>
       </div>
		</section>
	</div>
	<tiles:insertDefinition name="footer" />
</body>