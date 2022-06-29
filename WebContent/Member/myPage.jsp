<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>MaPage</title>
<link href="<%=request.getContextPath()%>/css/member.css" rel="stylesheet" type="text/css">
<style>
table {
	
    border-collapse: collapse;
  }
  .td_logo {
    border-left: 1px solid #e4e4e4;
    padding: 10px;
  }
  .tr_list{
   border-bottom: 1px solid #e4e4e4;
   padding: 10px;
  }

 
</style>
</head>
<body>
<tiles:insertDefinition name="header" />

<section style="width:1440px">
<article>
<tiles:insertDefinition name="nav" />
<div style="width: 1210px; margin: auto; padding-left:450px;">
	<hr width="1210" style="border:0.8px solid #e4e4e4;">
	<table width="1210">
		<tr>
			<td rowspan="3"><a href="#"><img class="myPage_logo" src="<%=request.getContextPath()%>/images/mypage.png"></a></td>
			<td class="td">${mId }님 환영합니다.</td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/rantal.png"></a></td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/chat.png"></a></td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/delivery.png"></a></td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/interest.png"></a></td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/question.png"></a></td>
			<td align="center" class="td_logo"><a href="#" ><img class="myPage_category" src="<%=request.getContextPath()%>/images/review.png"></a></td>
		</tr>
		<tr>
			<td rowspan="2"><a href="/Kkuni/Member/ModifyForm.do?mId=${mId }">회원 정보 수정</a></td>
			<td class="td_logo" align="center" ><input class="btn_p" type="button" value="대여중"></td>
			<td align="center" ><input class="btn_p" type="button" value="똑똑친구"></td>
			<td align="center" ><input class="btn_p" type="button" value="배송중"></td>
			<td align="center" ><input class="btn_p" type="button" value="관심상품"></td>
			<td align="center" ><input class="btn_p" type="button" value="1:1문의"></td>
			<td align="center" ><input class="btn_p" type="button" value="나의후기"></td>
		</tr>
		<tr>
			<td class="td_logo" align="center"><a href="#">${orderCount}</a></td>
			<td align="center"><a href="#">${ChatCount}</a></td>
			<td align="center"><a href="#">${deliveryCount}</a></td>
			<td align="center"><a href="#">${cartCount }</a></td>
			<td align="center"><a href="#">${questionCount}</a></td>
			<td align="center"><a href="#">${reviewCount}</a></td>
	</table>
	<h4>대여정보</h4>
	<hr width="1210" style="border:0.8px solid #e4e4e4;">
	<c:if test="${orderCount == 0}">
	<div align="center">
		<a href="#" ><img src="<%=request.getContextPath()%>/images/orderlist.png" width=80 margin=auto></a><br>
			진행 중인 대여가 없습니다.
	</div>
	</c:if>
	<c:if test="${orderCount > 0 }">
	<table width="1210">
		<tr class="tr_list" height="50">
			<td align="center"  width="50" >순번</td>
			<td align="center" width="100">주문 번호</td>
			<td align="center" width="100">주문 날짜</td>
			<td align="center" width="100">상품명</td>
			<td align="center" width="100">업체명</td>
			<td align="center" width="50">총 가격</td>
			<td align="center" width="200">대여 기간</td>
			<td align="center" width="50">수량</td>
			<td align="center" width="100">택배조회</td>
		</tr>
		<c:forEach var="article" end="5" step="1" items="${articleList}">
		<tr class="tr_list" height="50" >
			<td align="center"  width="50" >
			<c:out value="${number}"/>
			<c:set var="number" value="${number-1}"/>
			</td>
			<td align="center" width="100">${article.orderNo}</td>
			<td align="center" width="100">${article.orderDate}</td>
			<td align="center" width="100">상품명</td>
			<td align="center" width="100">${article.eId}</td>
			<td align="center" width="50">${article.payTotal}</td>
			<td align="center" width="200">${article.rentalD1}-${article.rentalD2}</td>
			<td align="center" width="50">${article.orderAmount}</td>
			<td align="center" width="100"><a href="#">택배조회</a></td>
		 	</tr>
		  	</c:forEach>
		</c:if>
		</table>
</div>
</article>
</section>
<footer style="display:inline; float : bottom;">
<tiles:insertDefinition name="footer" />
</footer>
</body>
</html>