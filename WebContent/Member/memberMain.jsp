<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>메인페이지</title>
<link href="<%=request.getContextPath()%>/css/member.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script type="text/javascript">
       $(document).ready(function(){
         $('.slider').bxSlider({
            auto:  true,
            pause: "3000"
            })
       });
   </script>
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
	width: 1024px;
	height: 1500px;
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
	width: 1024px;
	height: 80px;
	border: 1px solid #E4E4E4;
}
</style>
</head>
<body>
<tiles:insertDefinition name="header" />
<input type="hidden" name="mId" value="${mId }">
<div style="width: 1440px; margin: auto; border:none;">
	<ul class="slider">
    <li><img class="main_slider" src="<%=request.getContextPath()%>${slider.intro1}" /></li>
    <li><img class="main_slider" src="<%=request.getContextPath()%>${slider.intro2}" /></li>
    <li><img class="main_slider" src="<%=request.getContextPath()%>${slider.intro3}" /></li>
    <li><img class="main_slider" src="<%=request.getContextPath()%>${slider.intro4}" /></li>
    <li><img class="main_slider" src="<%=request.getContextPath()%>${slider.intro5}" /></li>
	</ul>
</div>
	<div style="width: 1440px; margin: auto;">
	<h3>신상품</h3>
	<hr width="1440" style="border-color:black; border:1px solid " class="hr_my">
	<c:forEach var="item" end="3" items="${itemList}">
	<div id="list"><a href="#" style="text-decoration: none;">
		<ul id="item">
			<li><a href="/Kkuni/Item/Detail.do?mId=${mId }&itemNo=${item.itemNo }">
			<img src="${pageContext.request.contextPath}${item.tRealpath }" width=230px; height=300px;></a></li>
			<li id="subject">${item.itemId }</li>
			<li id="price">${item.price} 원 / 1일</li>
		</ul>
	</div>
	</c:forEach>  
	<h3>인기상품</h3>
	<hr width="1440" style="border-color:black; border:1px solid " class="hr_my">
	<c:forEach var="best" end="3" items="${itemBest}">
	<div id="list"><a href="#" style="text-decoration: none;">
		<ul id="item">
			<li><a href="/Kkuni/Item/Detail.do?mId=${mId }&itemNo=${best.itemNo }">
			<img src="${pageContext.request.contextPath}${best.tRealpath }" width=230px; height=300px;>
			</a></li>
			<li id="subject">${best.itemId }</li>
			<li id="price">${best.price}원</li>
		</ul>
	</div>
	</c:forEach>
</div>
<tiles:insertDefinition name="footer" />
</body>
</html>