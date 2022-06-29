<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../css/adminlist.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../s_css/button.css">
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
<style>
#update {
	height:25px;
	margin-top: -4.5px;
}

#delete {
	height:25px;
}

#submit {
	height:25px;
}

.abc {
		color:black;
	}
	
	#h {
		text-align:left;
	}
	
	#search1 {
		text-align:right;
	}
</style>

</head>
<body style="background-color: #dbdbdb;">
	<tiles:insertDefinition name="enterprise_header" />
	<tiles:insertDefinition name="enterprise_menu" />
	<div style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">

		<br>
		<div class="pageSize size">
		<br>
		<div align="left">
			<h2>상품관리</h2>
			<hr>
		</div>

			<div align="right" style="margin-bottom: 15px;">
						<form method="post" action="/Kkuni/Item/EnterpriseList.do">
							<input type="text" size="20" name="search" class="body_search"/>
							<input type="hidden" name="eId" value="${eId}">
							<button class="btn btn_bodysearch"  placeholder="상품명으로 검색"><i class="fa fa-search"></i></button>
						</form>
			</div>
			
			<table  class="table_size size tablelist" style="text-align: center;">
				<tr>
					<th width="110">순번</th>
					<th width="400">상품번호</th>
					<th width="600">상품명</th>
					<th width="400">카테고리</th>
					<th width="500">가격(일)</th>
					<th width="300">수량</th>
					<th width="300">상태</th>
					<th width="150">수정</th>
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
   				
				<c:if test="${count > 0}">
					<c:forEach var="Item" items="${itemLists}">
						<tr>
							<td align="center">
							<c:out value="${number }"/>
							<c:set var="number" value="${number-1 }"/>
							</td>
							<td>${Item.itemNo}</td>
							<td>${Item.itemId}</td>
							<td>
							<c:if test="${Item.categoryNo eq 1}">
							유아용품 > 장난감
							</c:if>
							
							<c:if test="${Item.categoryNo eq 2}">
							유아용품 > 책
							</c:if>
							
							<c:if test="${Item.categoryNo eq 3}">
							유아용품 > 탈것
							</c:if>
							
							<c:if test="${Item.categoryNo eq 4}">
							스포츠/레저 > 캠핑
							</c:if>
							
							<c:if test="${Item.categoryNo eq 5}">
							스포츠/레저 > 레저
							</c:if>
							
							<c:if test="${Item.categoryNo eq 6}">
							스포츠/레저 > 등산
							</c:if>
							
							<c:if test="${Item.categoryNo eq 7}">
							스포츠/레저 > 헬스
							</c:if>
							
							<c:if test="${Item.categoryNo eq 8}">
							스포츠/레저 > 탈것
							</c:if>
							
							<c:if test="${Item.categoryNo eq 9}">
							음향기기 > 마이크
							</c:if>
							
							<c:if test="${Item.categoryNo eq 10}">
							음향기기 > 스피커
							</c:if>
							
							<c:if test="${Item.categoryNo eq 11}">
							음향기기 > 우퍼
							</c:if>
							
							<c:if test="${Item.categoryNo eq 12}">
							디지털가전 > 카메라
							</c:if>
							
							<c:if test="${Item.categoryNo eq 13}">
							디지털가전 > 노트북
							</c:if>
							
							<c:if test="${Item.categoryNo eq 14}">
							디지털가전 > 빔프로젝트
							</c:if>
							
							<c:if test="${Item.categoryNo eq 15}">
							공간대여 > 이벤트홀
							</c:if>
							
							<c:if test="${Item.categoryNo eq 16}">
							공간대여 > 파티룸
							</c:if>
							
							<c:if test="${Item.categoryNo eq 17}">
							공간대여 > 공연/강연
							</c:if>
							
							<c:if test="${Item.categoryNo eq 18}">
							공간대여 > 스튜디오
							</c:if>
							
							
							
							</td>
							<td>${Item.price}원</td>
							<td>${Item.amount}개</td>
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
							<td>
							<input type="button" value="수정" id="update" class="btn_p"  onclick="document.location.href='/Kkuni/Item/UpdateForm.do?itemNo=${Item.itemNo}&pageNum=${currentPage}'">
							</td>
							
							<td>
							<form method="post" name="deleteform" action="/Kkuni/Item/EnterpriseDelete.do">
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
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${startPage - 10}" class="abc">[이전]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${startPage - 10}&search=${search}" class="abc">[이전]</a>
			</c:if>
			
		 </c:if>
		
		 <c:forEach var="i" begin="${startPage}" end= "${endPage}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${i}" class="abc">[${i}]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${i}&search=${search}" class="abc">[${i}]</a>
			</c:if>
		 </c:forEach>
		
		 <c:if test="${endPage < pageCount}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${startPage + 10}" class="abc">[다음]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Item/EnterpriseList.do?eId=${eId }&pageNum=${startPage + 10}&search=${search}" class="abc">[다음]</a>
			</c:if>
		 </c:if>
		 
		 </c:if>
		</div>
	</div>

</body>
</html>

