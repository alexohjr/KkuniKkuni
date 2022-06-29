<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>주문상세</title>
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function cancel_check() {
	if(confirm("정말 주문을 취소하시겠습니까?") == true){
		return true;
	}
	else {
		return false;
	}
} 
</script>
</head>
<body>
<tiles:insertDefinition name="header" />
<div class="pageSize">
<div align="left">
	<h2>주문상세</h2>
	<hr>
	<h4>주문 정보</h4>
</div>
<form name=detailfrm action="/Kkuni/orderlist/Cancel.do?num=${num}" onsubmit="return cancel_check()">
<table class="table_size">
	<tr>
		<td>주문번호</td>
		<td>이미지</td>
		<td>상품명</td>
		<td>단가</td>
		<td>수량</td>
		<td>기간</td>
		<td>총 금액</td>
		<td>배송비</td>
	</tr>
	
	<tr><td colspan="8"><hr class="table_line"></td></tr>
	
	<tr>
		<td><input type="hidden" name="num" value="${orderlist.orderNo}">${orderlist.orderNo}</td>
		<td><img class="table_img" src="${pageContext.request.contextPath}${itemList.tRealpath }"></td>
		<td><a href="/Kkuni/Item/Detail.do?itemNo=${num}">${itemList.itemId}</a></td>
		<td>${itemTotal}원</td>
		<td>${orderlist.orderAmount}개</td>
		<td>${orderlist.rentalD1} ~ ${orderlist.rentalD2}</td>
		<td>${orderTotal}원</td>
		<td>무료</td>
	</tr>
</table>

<div align="left">
	<hr>
	<h4>배송지 정보</h4>
	<table class="table_size" style="height: 300px;">
		<tr>
			<td width="200" align="center">수령인</td>
			<td>
				<input type="text" readonly value="${memberlist.mName}">
				<input type="hidden" readonly name="memId" value="${memberlist.mId}">
			</td>
			
		</tr>
		
		<tr>
			<td width="200" align="center">연락처</td>
			<td><input type="text" readonly value="${memberlist.mTel}"></td>
		</tr>
		
		<tr>
			<td width="200" align="center">주소</td>
			<td><input type="text" readonly value="${memberlist.mZipcode}"></td>
		</tr>
		
		<tr>
			<td width="200"></td>
			<td>
				<input type="text" style="width: 300px;" readonly value="${address}"> &nbsp;&nbsp;
				<input type="text" readonly value="${addressDetail}">
			</td>
		</tr>
		
		<tr>
			<td width="200" align="center">배송메세지</td>
			<td><input type="text" readonly value="${orderlist.orderMeg}"></td>
		</tr>
	</table>
	
	<c:if test="${orderlist.orderState eq 'delivering' or orderlist.orderState eq 'deliveried'}">
	<h4>운송장 정보</h4>
	<table class="table_size" style="height: 100px;">
		<tr>
			<td width="200" align="center">운송장번호</td>
			<td><input type="text" readonly value="${orderlist.parcelNum}"></td>
		</tr>
	</table>
	</c:if>
</div>
<div align="right">
	<c:if test="${orderlist.orderState eq 'complete'}">
		<input type="submit" class="btn btn_p" value="결제취소">
	</c:if>
	
	<c:if test="${orderlist.orderState eq 'delivering'}">
		<input type="button" value="택배조회" class="btn btn_p" onclick="window.open('https://www.cjlogistics.com/ko/tool/parcel/tracking', '배송조회', 'width=#, height=#')">
	</c:if>
	<!-- <button class="btn_p"  onclick="document.location.href='/Kkuni/orderlist/Orderlist.do?'">배송조회</button> -->
	<input type="button" class="btn btn_b" onclick="document.location.href='/Kkuni/orderlist/Orderlist.do?memId=${memberlist.mId}'" value="목록">
</div>
</form>
</div>
<tiles:insertDefinition name="footer" />
</body>
</html>