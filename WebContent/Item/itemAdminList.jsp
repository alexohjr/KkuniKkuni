<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../css/adminlist.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../s＿css/button.css">
<link rel="stylesheet" href="../s_css/list.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
	$(function() {
		$('ul.tab li').click(function() {
			var activeTab = $(this).attr('data-tab');
			$('ul.tab li').removeClass('current');
			$('.tabcontent').removeClass('current');
			$(this).addClass('current');
			$('#' + activeTab).addClass('current');
		})
	});
	
	function dltbutton() {
		if(confirm("정말 삭제하시겠습니까?") == true) {
			document.deleteform.submit();
		} else {
			return;
		}
	}
</script>
<style type="text/css">
#delete {
	height:25px;
	margin-top:-4.5px;
}

.btn_bodysearch{
	font-size:15px;
	width: 30px;
	height: 30px;
	border-radius: 0px 5px 5px 0px;
	margin-left: 0px;
	background-color: #FD999A;
}

.body_search {
	height: 27.5px;
	border: 0.1px solid #E4E4E4;
	margin-right: -7px;
}

.abc {
	color: black;
}
</style>
</head>
<body style="background-color: #dbdbdb;">
	<tiles:insertDefinition name="admin_header" />
	<tiles:insertDefinition name="admin_menu" />
	<div style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">

	<div class="pageSize size">
	<br>
		<div align="left">
			<h2>상품관리</h2>
			<hr>
		</div>

			<div align="right" style="margin-bottom: 15px;">
				<form method="post" action="/Kkuni/Item/AdminList.do">
					<input type="text" size="20" name="search" class="body_search" placeholder="상품명 검색"/>
					<button class="btn btn_bodysearch" style="border: none;"><i class="fa fa-search"></i></button>
				</form>
			</div>
			<table class="table_size size tablelist">
				<tr>
					<th width="110">순번</th>
					<th width="400">상품번호</th>
					<th width="600">상품명</th>
					<th width="400">가격(일)</th>
					<th width="500">수량</th>
					<th width="300">상태</th>
					<th width="300">업체명</th>
					<th width="150">삭제</th>
				</tr>
				
				<c:if test="${count == 0}">
				<tr>
		   			<td colspan="8" align="center">
		     			<img src="<%=request.getContextPath()%>/images/goods.png" style="width: 100px; margin-top: 20px;">
		       			<h4>등록된 상품이 없습니다.</h4>
		       		</td>
   				</tr>
   				</c:if>
   			
				<c:if test="${count>0}">
					<c:forEach var="Item" items="${itemLists}">
						<tr>
							<td align="center">
							<c:out value="${number }"/>
							<c:set var="number" value="${number-1 }"/>
							</td>
							<td>${Item.itemNo}</td>
							<td>
							
							<a href="/Kkuni/Item/Detail.do?itemNo=${Item.itemNo }&pageNum=${currentPage}" class="abc">
							${Item.itemId}
							</a>
							
							
							</td>
							<td>${Item.price}</td>
							<td>${Item.amount}</td>
							<td>
							
							<c:if test="${Item.itemstate eq 'yes'}">
							판매중
							</c:if>
							
							<c:if test="${Item.itemstate eq 'no'}">
							품절
							</c:if>
							
							<c:if test="${Item.itemstate eq 'out'}">
							대여중
							</c:if>
							
							</td>
							<td>${Item.eId}</td>
							<td>
							
							<form method="post" name="deleteform" action="/Kkuni/Item/AdminDelete.do">
							<input type="hidden" name="itemNo" value="${Item.itemNo }"/>
							<input type="hidden" name="pageNum" value="${currentPage }"/>
							<input type="submit" value="삭제" id="delete" class="btn_b" onclick="dltbutton();">
							</form>
							
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<br>
		<div align="center">
		<c:if test="${count > 0 }">
         <c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1 )}"/>
         <c:set var="pageBlock" value="${10 }"/> <!-- 페이징 넘버  -->
         <fmt:parseNumber var = "result" value ="${currentPage / 10}" integerOnly = "true" />
		 <c:set var = "startPage" value ="${result * 10 + 1}" />
		 <c:set var = "endPage" value ="${startPage + pageBlock-1}" /> <!-- 보여질 페이징 넘버 -->
		 <c:if test="${endPage > pageCount}">
			<c:set var = "endPage" value ="${pageCount}" />
		 </c:if>
		
		 <c:if test="${startPage > 10}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${startPage - 10}" class="abc">[이전]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${startPage - 10}&search=${search}" class="abc">[이전]</a>
			</c:if>
		 </c:if>
		
		 <c:forEach var="i" begin="${startPage}" end= "${endPage}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${i}" class="abc">[${i}]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${i}&search=${search}" class="abc">[${i}]</a>
			</c:if>
		 </c:forEach>
		
		 <c:if test="${endPage < pageCount}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${startPage + 10}" class="abc">[다음]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/AdminList.do?pageNum=${startPage + 10}&search=${search}" class="abc">[다음]</a>
			</c:if>
		 </c:if>
		</c:if>


		</div>
	</div>

</body>
</html>


<!-- <h4>
				 -->