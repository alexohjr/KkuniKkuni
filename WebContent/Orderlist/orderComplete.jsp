<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>결제완료</title>
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
</head>
<body>
<tiles:insertDefinition name="header" />
<div class="pageSize">
<div align="left">
	<h2>결제완료</h2>
	<hr>
</div>
<h1>결제가 완료되었습니다.</h1>
<button class="btn_p" onclick="document.location.href='/Kkuni/Item/Detail.do?mId=${memberlist.mId}&itemNo=${itemList.itemNo}'">목록</button><!-- /Kkuni/item/Mainlist.do -->
<button onclick="document.location.href='/Kkuni/orderlist/Orderlist.do'">주문내역</button>

<div align="left">
	<h4>주문자 정보</h4>
</div>
<table class="table_size" style="height: 100px;">
	<tr>
		<td width="100">주문자</td>
		<td class="table_td">${memberlist.mName}</td>
		<td width="150">휴대폰번호</td>
		<td class="table_td">${memberlist.mTel}</td>
		<td width="150">이메일</td>
		<td class="table_td">${memberlist.mMail}</td>
	</tr>
</table>

<div align="left">
	<hr>
	<h4>대여 상품</h4>
</div>

<table class="table_size">
	<tr>
		<td>이미지</td>
		<td>상품명</td>
		<td>단가</td>
		<td>수량</td>
		<td>기간</td>
		<td>총 금액</td>
		<td>배송비</td>
	</tr>
	
	<tr><td colspan="7"><hr class="table_line"></td></tr>
	
	<tr>
		<td><img class="table_img" src="${pageContext.request.contextPath}${itemList.tRealpath }"></td>
		<td><a href="/Kkuni/Item/Detail.do?itemNo=${itemList.itemNo}">${itemList.itemId}</a></td>
		<td>${itemTotal}원</td>
		<td>${orderlist.orderAmount}개</td>
		<td>${orderlist.rentalD1} ~ ${orderlist.rentalD2}</td>
		<td>${orderTotal}원</td>
		<td>무료</td>
	</tr>
	
</table>
<div align="right">
<h2>총 결제 금액 &nbsp;&nbsp; ${orderTotal}원</h2>
</div>
</div>
<tiles:insertDefinition name="footer" />
</body>
</html>