<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>업체 메인</title>

<style type="text/css">
.wrapper {
	width: 1650px;
	height: 880px;
	margin: 10px;

}

#adminmain1 {
	width: 1000px;
	height: 410px;
	float: left;
margin: 10px 10px 10px 20px;
}

#adminmain2 {
	width: 600px;
	height: 410px;
	float: right;
	margin: 10px;
}

#adminmain3 {
	width: 800px;
	height: 410px;
	float: left;
margin: 10px 10px 10px 20px;
}

#adminmain4 {
	width: 800px;
	height: 410px;
	float: right;
	margin: 10px;
	
}

table {
	border-collapse: collapse;
	vertical-align: middle;
	width: 750px;
}

.thmd {
	border-top: 2px solid #e4e4e4;
	border-bottom: 2px solid #e4e4e4;
	padding: 8px;
	text-align: center;
}

.tdcltrmd {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
}

.trmd {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
	height: 35px;
}

#textcolor {
	float: right;
	color: black;
}

#plustext {
	align: right;
	margin-bottom: 10px;
}
</style>

<script>

   function reviewDetail(reviewNo) {
    
       url = "/Kkuni/Review/Detail.do?reviewNo="+reviewNo;
    
      window.open(url, "post", "toolbar=no, left=430%, top=200%, width=1070, height=650, directories=no, status=yes, scrollbars=no, menubar=no");
    }
   
   function insertFormUp(itemNo) {
        
      url = "/Kkuni/Review/InsertForm.do?itemNo="+itemNo;
      
      window.open(url, "post", "width=1070, height=650,left=430%, top=200%, directories=no, status=yes, scrollbars=no, menubar=no")
      
   }
   
   

</script>
</head>
<body style="background-color: #dbdbdb;">
	<tiles:insertDefinition name="enterprise_header" />
	<tiles:insertDefinition name="enterprise_menu" /> 
	<div
		style="background-color: white; width: 1680x; min-height: 900px; margin: 10px; margin: 80px 0px 0px 200px;">

		<div class="wrapper">

			<!-- 대여 게시판 -->
			<div id="adminmain1">
				<div id="div1">
					<h3 style="text-align: left; margin-bottom: -20px;">대여 현황</h3>
					<table id="plustext">
						<tr>
							<td><a id="textcolor" href="/Kkuni/orderlist/OrderEnterpriseList.do?eId=${eId}">더보기</a></td>
						</tr>
					</table>
					<table>
						<tr class="trmd">
							<th class="thmd" width="50">순 번</th>
							<th class="thmd" width="150">주문번호</th>
							<th class="thmd" width="150">주문날짜</th>
							<th class="thmd" width="150">상품명</th>
							<th class="thmd" width="150">대여자ID</th>
							<th class="thmd" width="100">현황</th>
						</tr>
						<c:if test="${ rentalCount == 0}">
						<td colspan="6">대여중인 상품이 없습니다.
						</td>
						</c:if>
						<c:if test="${ rentalCount> 0}">
						<c:forEach var="rental" items="${rentalList}" end="4">
						<tr>
								<td align="center" width="50">
								<c:out value="${rentalnumber}" />
								<c:set var="rentalnumber" value="${rentalnumber-1}" />	
								</td>
								<td align="center" width="150">
								${rental.orderNo}
								</td>
								<td align="center" width="150">
								${rental.orderDate}
								</td>
								<td align="center" width="150">
								${rental.itemNo}								
								</td>
								<td align="center" width="150">
								${rental.eId}
								</td>
								<td width="100">
								<c:if test="${rental.orderState == 'complete'}">
								결제완료
								</c:if>
								<c:if test="${rental.orderState == 'cancle'}">
								결제취소
								</c:if>
								<c:if test="${rental.orderState == 'delivering'}">
								배송중
								</c:if>
								<c:if test="${rental.orderState == 'deliveried'}">
								대여완료
								</c:if>
								</td>
							</tr>
						</c:forEach>
						</c:if>
					</table>
				</div>

			</div>


			<!--------------------- 달력 -------------------------------->
			<div id="adminmain2">
				<iframe src="../calender/calendar.html" scrolling="no" width="320"
					height="260" frameborder="0"></iframe>
			</div>
			<!-- --------------------------선 --------------------------------->
			<hr style="width:98%; border:1px solid #dbdbdb;">

			<!---------------------- 공지사항 -------------------------------------->
			<div id="adminmain3">
				<h3 style="text-align: left; margin-bottom: -20px;">공지사항</h3>
				<table id="plustext">
					<tr>
						<td><a id="textcolor" href="/Kkuni/Notice/NoticeEnterpriseList.do?eId=${eId}">더보기</a></td>
					</tr>
				</table>
				<table>
					<tr class="trmd">
						<th class="thmd" width="50">번 호</th>
						<th class="thmd" width="250">제목</th>
						<th class="thmd" width="100">작성자</th>
						<th class="thmd" width="100">작성일</th>
					</tr>
					<c:if test="${noticeCount == 0}">
					<tr>
						<td colspan="4">등록된 공지사항이 없습니다.</td>
					</tr>
					</c:if>
					<c:if test="${noticeCount > 0}">
					<c:forEach var="notice" items="${noticeList}" end="4">
						<tr>
							<td align="center" width="50">
							<c:out value="${notinumber}" />
							<c:set var="notinumber" value="${notinumber-1}" />
							</td>
							<td align="center" width="250">
							${notice.nTitle}
							</td>
							<td align="center" width="100">
							관리자
							</td>
							<td align="center" width="100">
							${notice.nDate}
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>

			<!---------------------------- 후기 ------------------------------->
			<div id="adminmain4">
				<h3 style="text-align: left; margin-bottom: -20px;">후기</h3>
				<table id="plustext">
					<tr>
						<td >　　</td>
					</tr>
				</table>
				<table>
					<tr class="trmd">
						<th class="thmd" width="50">순 번</th>
						<th class="thmd" width="250">제목</th>
						<th class="thmd" width="100">작성자</th>
						<th class="thmd" width="100">작성일</th>
					</tr>
					<c:if test="${reviewCount == 0}">
					<tr>
						<td colspan="4">등록된 리뷰가 없습니다.</td>
					</tr>
					</c:if>
					<c:if test="${reviewCount > 0}">
					<c:forEach var="review" items="${reviewList}" end="4">
						<tr>
							<td align="center" width="50">
							<c:out value="${reviewnumber}" />
							<c:set var="reviewnumber" value="${reviewnumber-1}" />
							 
							<td align="center" width="250">
							 <a href="javascript:reviewDetail(${review.reviewNo });"
                                       style="text-decoration: none; color: #4C4C4C; margin: 0;">
							${review.rContents}
							</a>
							</td>
							<td align="center" width="100">
							${review.mId}
							</td>
							<td align="center" width="100">
							${review.rDate}
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
</html>