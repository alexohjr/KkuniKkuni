<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>주문내역</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">

<style type="text/css">
.total{
width:1410px;
align: center;
margin: auto;
padding: 0;
overflow: hidden;
}


.wrapper {
   width: 1200px;
}

</style>
</head>
<body>
<tiles:insertDefinition name="header" />
<div class="total">
<div class="wrapper">
<tiles:insertDefinition name="nav" />
</div>
<div align="left">
	<h3 style="margin-left: 220px;">주문관리</h3>
	<hr style="margin-left: 220px; border: 0.8px solid #E4E4E4;">
</div>

<div align="right" style="margin-bottom: 15px;">
	<form method="post" action="/Kkuni/orderlist/Orderlist.do" >
		<input type="text" size="20" name="search" class="body_search" value="${search}" placeholder="상품명을 입력해주세요"/>
		<input type="hidden" name="memId" value="${memId}">
		<button class="btn btn_bodysearch"><i class="fa fa-search" style="color: white;"></i></button>
	</form>
</div>


<table class="table_size tablelist" style="width: 1150px; text-align: center;">
	<tr>
		<th width="70">주문번호</th>
		<th width="130">주문날짜</th>
		<th width="130">상품명</th>
		<th width="70">업체명</th>
		<th width="100">총 금액</th>
		<th width="250">대여기간</th>
		<th width="50">수량</th>
		<th width="100">상태</th>
	</tr>
	
	
		<c:if test="${count == 0}">
			<tr>
	   			<td colspan="8" align="center">
	     			<img src="<%=request.getContextPath()%>/images/orderlist.png" style="width: 100px; margin-top: 20px;">
	       			<h4>주문 내역이 없습니다.</h4>
	       		</td>
   			</tr>
   		</c:if>
   	
   	
	<c:forEach var="orderlist" items="${orderlists}">
		<tr>
			<td>${orderlist.orderNo}</td>
			<td>${orderlist.orderDate}</td>
			<td><a href="/Kkuni/orderlist/Detail.do?num=${orderlist.orderNo}&memId=${memId}">${orderlist.itemName}</a></td>
			<%-- <td><a href="'/Kkuni/orderlist/Detail.do?num='+${orderlist.orderNo}">KkuniKkuni토끼</a></td> --%>
			<td>${orderlist.eName}</td>
			<td>${orderlist.payTotal}원</td>
			<td>${orderlist.rentalD1} ~ ${orderlist.rentalD2}</td>
			<td>${orderlist.orderAmount}개</td>
			<c:choose>
			    <c:when  test="${orderlist.orderState eq '결제취소'}">
			        <td>
						<font color="#F15F5F" style="text-decoration: line-through;">결제취소</font>
					</td>
			    </c:when>
			    
			    <c:when  test="${orderlist.orderState eq '취소대기'}">
			        <td>
						<font color="#F15F5F">취소대기</font>
					</td>
			    </c:when>
			    
			    <c:when  test="${orderlist.orderState eq '배송중'}">
			       	<td>
						<input type="button" class="btn" value="택배조회" onclick="window.open('https://www.cjlogistics.com/ko/tool/parcel/tracking', '배송조회', 'width=#, height=#')">
					</td>
			    </c:when>
			    
			    <c:when  test="${orderlist.orderState eq '대여완료'}">
			        <td>
						<font color="#4374D9">대여완료</font>
					</td>
			    </c:when>
			
			    <c:otherwise>
			      	<td>
						결제완료
					</td>
			    </c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>

<div align="center" style="margin-top: 10px; margin-left: 250px;">
			<c:if test="${count > 0}">
				<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0?0:1)}" />
				<c:set var="pageBlock" value="${10}" />
				<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true" />
				<c:set var="startPage" value="${result * 10 + 1}" />
				<c:set var="endPage" value="${startPage + pageBlock - 1}" />
				
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}" />
				</c:if>
				
				<c:if test="${startPage > 10}">
					<c:if test="${empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${startPage-10}&memId=${memId}" style="color: black;">[이전]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${startPage-10}&memId=${memId}&search=${search}" style="color: black;">[이전]</a>
					</c:if>
				 </c:if>
				
				 <c:forEach var="i" begin="${startPage}" end= "${endPage}">
					<c:if test="${empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${i}&memId=${memId}" style="color: black;">[${i}]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${i}&memId=${memId}&search=${search}" style="color: black;">[${i}]</a>
					</c:if>
				 </c:forEach>
				
				 <c:if test="${endPage < pageCount}">
					<c:if test="${empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${startPage+10}&memId=${memId}" style="color: black;">[다음]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/Orderlist.do?pageNum=${startPage+10}&memId=${memId}&search=${search}" style="color: black;">[다음]</a>
					</c:if>
				 </c:if>
			</c:if>
		</div>

</div>
<tiles:insertDefinition name="footer" />
</body>
</html>