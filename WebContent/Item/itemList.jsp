<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>상품리스트</title>
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
			<h1>
				<c:if test="${cNo == 1 || cNo == 2 || cNo == 3 || cNo == 100}">
				<span style="color: #353535;">유아용품</span>
				</c:if>
				<c:if test="${cNo == 200 || cNo == 4 || cNo == 5 || cNo == 6 || cNo == 7 || cNo == 8}">
				<span style="color: #353535;">스포츠/레저</span>
				</c:if>
				<c:if test="${cNo == 300 || cNo == 9 || cNo == 10 || cNo == 11}">
				<span style="color: #353535;">음향기기</span>
				</c:if>
				<c:if test="${cNo == 400 || cNo == 12 || cNo == 13 || cNo == 14}">
				<span style="color: #353535;">디지털가전</span>
				</c:if>
				<c:if test="${cNo == 500 || cNo == 15 || cNo == 16 || cNo == 17 || cNo == 18}">
				<span style="color: #353535;">공간대여</span>
				</c:if>
			</h1>


			<article>

				<div id=nav>
					<nav id="main_lnb">
						<ul id=id_nav>
							<c:if test="${cNo == 1 || cNo == 2 || cNo == 3 || cNo == 100}"> <!-- 유아용품  -->
								<li><a href="/Kkuni/Item/List.do?cNo=${100 }"> 전체보기 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${1 }"> 장난감 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${2 }"> 책 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${3 }"> 탈것 </a></li>
							</c:if>
							
							
							<c:if test="${cNo == 200 || cNo == 4 || cNo == 5 || cNo == 6 || cNo == 7 || cNo == 8}"> <!-- 스포츠 -->
								<li><a href="/Kkuni/Item/List.do?cNo=${200 }"> 전체보기 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${4 }"> 캠핑 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${5 }"> 레저 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${6 }"> 등산 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${7 }"> 헬스 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${8 }"> 탈것 </a></li>
							</c:if>
							
							<c:if test="${cNo == 300 || cNo == 9 || cNo == 10 || cNo == 11}"> <!-- 음향기기  -->
								<li><a href="/Kkuni/Item/List.do?cNo=${300 }"> 전체보기 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${9 }"> 마이크 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${10 }"> 스피커 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${11 }"> 우퍼 </a></li>
							</c:if>
							
							<c:if test="${cNo == 400 || cNo == 12 || cNo == 13 || cNo == 14}"> <!-- 디지털가전  -->
								<li><a href="/Kkuni/Item/List.do?cNo=${400 }"> 전체보기 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${12 }"> 카메라 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${13 }"> 노트북 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${14 }"> 빔프로젝터 </a></li>
							</c:if>
							
							<c:if test="${cNo == 500 || cNo == 15 || cNo == 16 || cNo == 17 || cNo == 18}"> <!-- 공간대여  -->
								<li><a href="/Kkuni/Item/List.do?cNo=${500 }"> 전체보기 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${15 }"> 이벤트홀 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${16 }"> 파티룸 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${17 }"> 공연/강연 </a></li>
								<li><a href="/Kkuni/Item/List.do?cNo=${18 }"> 스튜디오 </a></li>
							</c:if>
						</ul>
					</nav>
				</div>
				<h4 style="float: left;">총 ${count}개의 상품이 있습니다.</h4>

				<hr width="1440px" color="#E4E4E4" align="center">
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
         <a href = "/Kkuni/Item/List.do?pageNum=${startPage - 10}&cNo=${cNo}" class="abc">[이전]</a>
       </c:if>
      
       <c:forEach var="i" begin="${startPage}" end= "${endPage}">
         <a href = "/Kkuni/Item/List.do?pageNum=${i}&cNo=${cNo}" class="abc">[${i}]</a>
       </c:forEach>
      
       <c:if test="${endPage < pageCount}">
         <a href = "/Kkuni/Item/List.do?pageNum=${startPage + 10}&cNo=${cNo}" class="abc">[다음]</a>
       </c:if>
      </c:if>
       </div>
		</section>
	</div>
	<tiles:insertDefinition name="footer" />
</body>