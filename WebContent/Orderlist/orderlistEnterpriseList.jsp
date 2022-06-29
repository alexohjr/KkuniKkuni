<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.size{
		width: 1500px;
	}
</style>

<script type="text/javascript">
	function insertParcelNum(orderNo) {
		url="/Kkuni/orderlist/InsertParcelNumForm.do?orderNo=" + orderNo;
		window.open(url,"post","toolbar=no,width=500,height=300,directories=no,status=yes,scrollbars=yes,menubar=no");
	}
	
	function updateDeliveried() {
		if(confirm("대여완료 처리하시겠습니까?") == true){
			return true;
		}
		else {
			return false;
		}
	}
	
	function updateCanceling() {
		if(confirm("결제취소 처리하시겠습니까?") == true){
			return true;
		}
		else {
			return false;
		}
	}
</script>
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="enterprise_header" />
<tiles:insertDefinition name="enterprise_menu" />
<div style="background-color: white; width: 1705px;
			min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
	<div class="pageSize size">
	<br>
		<div align="left">
			<h2>주문관리</h2>
			<hr>
		</div>
		
		<div align="right" style="margin-bottom: 15px;">
			<form method="post" action="/Kkuni/orderlist/OrderEnterpriseList.do" >
				<input type="hidden" name="eId" value="${eId}">
				<select name="searchn" style="width: 100px; height: 30px; border: 1px solid #E4E4E4; margin-top: 12px;">
					<option value="0">주문번호</option>
					<option value="1">상품명</option>
					<option value="2">대여자ID</option>
					<option value="3">대여자명</option>
				</select>
				<input type="text" class="body_search" name="search" value="${search}">
				<button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
			</form>
		</div>
		
		<table class="table_size size tablelist">
			<tr>
				<th width="70">주문번호</th>
				<th width="130">주문날짜</th>
				<th width="100">상품명</th>
				<th width="100">대여자ID</th>
				<th width="100">대여자명</th>
				<th width="100">총 금액</th>
				<th width="250">대여기간</th>
				<th width="50">상태</th>
				<th width="100">송장번호</th>
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
				<td>${orderlist.itemName}</td>
				<td>${orderlist.mId}</td>
				<td>${orderlist.mName}</td>
				<td>${orderlist.payTotal}원</td>
				<td>${orderlist.rentalD1} ~ ${orderlist.rentalD2}</td>
				
				<c:choose>
				    <c:when  test="${orderlist.orderState eq '배송중'}">
						<td>
							 <form action="/Kkuni/orderlist/updateOrderState.do" method="post" onsubmit="return updateDeliveried()">
							 <input type="hidden" name="orderNo" value="${orderlist.orderNo}">
							 <input type="hidden" name="OrderState" value="배송중">
							 <input type="hidden" name="pageNum" value="${pageNum}">
							 <input type="hidden" name="eId" value="${eId}">
							 <input type="submit" class="btn"  value="${orderlist.orderState}">
				      		</form>
				      	</td>	
					</c:when>
					
					<c:when  test="${orderlist.orderState eq '취소대기'}">
						<td>
							 <form action="/Kkuni/orderlist/updateOrderState.do" method="post" onsubmit="return updateCanceling()">
							 <input type="hidden" name="orderNo" value="${orderlist.orderNo}">
							  <input type="hidden" name="OrderState" value="취소대기">
							 <input type="hidden" name="pageNum" value="${pageNum}">
							 <input type="hidden" name="eId" value="${eId}">
							 <input type="submit" class="btn"  value="${orderlist.orderState}">
				      		</form>
				      	</td>	
					</c:when>
				
				    <c:otherwise>
				    	<td>
							${orderlist.orderState}
				      	</td>
					</c:otherwise>
				</c:choose>
				
				
				<c:choose>
				    <c:when test="${empty orderlist.parcelNum}">
						<td>
							<input type="button" class="btn" value="번호추가" onclick="insertParcelNum(${orderlist.orderNo})">
						</td>		    
					</c:when>
				
				    <c:otherwise>
				      	<td>
							<a onclick="insertParcelNum(${orderlist.orderNo})" style="color: black;">${orderlist.parcelNum}</a>
						</td>
				    </c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		</table>
		
			<div align="right">
				<input type="button" value="택배조회" class="btn btn_p" onclick="window.open('https://www.cjlogistics.com/ko/tool/parcel/tracking', '배송조회', 'width=#, height=#')">
			</div>
		</div>
		<div align="center" style="margin-top: 10px;">
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
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${startPage-10}&eId=${eId}" style="color: black;">[이전]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${startPage-10}&eId=${eId}&search=${search}&searchn=${searchn}" style="color: black;">[이전]</a>
					</c:if>
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end= "${endPage}">
					<c:if test="${empty search }">
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${i}&eId=${eId}" style="color: black;">[${i}]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${i}&eId=${eId}&search=${search}&searchn=${searchn}" style="color: black;">[${i}]</a>
					</c:if>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<c:if test="${empty search }">
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${startPage+10}&eId=${eId}" style="color: black;">[다음]</a>
					</c:if>
					<c:if test="${!empty search }">
						<a href="/Kkuni/orderlist/OrderEnterpriseList.do?pageNum=${startPage+10}&eId=${eId}&search=${search}&searchn=${searchn}" style="color: black;">[다음]</a>
					</c:if>
				</c:if>
		</c:if>
		</div>
	</div>
</body>
</html>