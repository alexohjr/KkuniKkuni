<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>상품상세페이지</title>
<link href="../css/header.css" rel="stylesheet" type="text/css">
<link href="button.css" rel="stylesheet" type="text/css">
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<script language='javascript'>
	function change(num) {
		var x = document.form;  
		var y = Number(x.order_amount.value) + num;
		if (y < 1)
			y = 1;
		x.order_amount.value = y;
	}
	
	function MemberCheck(memId) {
		if(memId == null) {
			alert("로그인 후 이용해주세요");
			return false;
		}
	}
	
	function HeartCheck() {
	      alert("로그인 후 이용해주세요.");
	      return false;
	}
</script>  

<script language="javascript" type="text/javascript">
	$(document).ready(
					function() {

						//******************************************************************************
						// 상세검색 달력 스크립트
						//******************************************************************************
						var clareCalendar = {
							monthNamesShort : [ '1월', '2월', '3월', '4월', '5월',
									'6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
							dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
							weekHeader : 'Wk',
							dateFormat : 'yy-mm-dd', //형식(20120303)
							autoSize : false, //오토리사이즈(body등 상위태그의 설정에 따른다)
							changeMonth : true, //월변경가능
							changeYear : true, //년변경가능
							showMonthAfterYear : true, //년 뒤에 월 표시
							buttonImageOnly : true, //이미지표시
							buttonText : '달력선택', //버튼 텍스트 표시
							buttonImage : '../images/calendar.png', //이미지주소
							showOn : "both", //엘리먼트와 이미지 동시 사용(both,button)
							yearRange : '1990:2020', //1990년부터 2020년까지
							minDate : 0 
						};
						$("#fromDt").datepicker(clareCalendar);
						$("#toDt").datepicker(clareCalendar);
						$("img.ui-datepicker-trigger")
								.attr("style",
										"margin-left:5px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
						$("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
					});
	
	function check() {
		if(!document.form.rentalD1.value) {
			alert("대여기간을 선택해주세요");
			return false;
		}
		
		if(!document.form.rentalD2.value) {
			alert("대여기간을 선택해주세요");
			return false;
		}
	}
	
</script>


<style>
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
	margin: auto;
	padding: 0;
	overflow: hidden;
	padding: 0;
}

#list1 {
	top: 10px;
	width: 500px;
	height: 500px;
	display: inline-block;
	margin: 0 auto;
	border: 1px solid #E4E4E4;
}

#list2 {
	top: 10px;
	width: 500px;
	height: 500px;
	display: inline-block;
	margin: 0 auto;
}

#adress {
	width: 1024px;
	height: 50px;
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

#raddressss {
   width: 100%;
   height: 50px;
   text-align: center;
   margin-top: 10px;
   margin-bottom: 10px;
}
input[type=button], input[type=submit], button{
	width: 100px;
	height: 40px;  
	border-radius: 5px;
	border: 0px;
	margin-top: 10px;
}

.btn_p {
	background-color: #DF7785;
	color: white;
}

.btn_b {
	background-color: #444F59;
	color: white;
}


</style>



</head>	
<body>  
	<tiles:insertDefinition name="header" />

	<div class="wrap">
		<section>
		<h2>${itemList.itemId}</h2>

		<div id="list1">
			<img src="${pageContext.request.contextPath}${itemList.thumbnail}" width=500px; height=500px;>
		</div>
		<div id="list2">
		<table class="heartimg" align="right">
				<tr>
					<c:if test="${!empty mId }">
						<c:choose>
							<c:when test="${Cartcount == 0 }">
								<td>
								<a href="/Kkuni/Cart/Insert.do?itemNo=${itemList.itemNo}&mId=${mId}">
								<input type="image" id="cartupdate" src="<%=request.getContextPath()%>/images/heart2.png">
								</a>
								</td>
							</c:when>
							
							<c:otherwise>
								<td>
								<a href="/Kkuni/Cart/Delete.do?itemNo=${itemList.itemNo }&mId=${mId}">
									<input type="image" id="cartupdate" src="<%=request.getContextPath()%>/images/heart.png">
								</a>
								</td>
							</c:otherwise>
						</c:choose>
					</c:if>
					
					<c:if test="${empty mId }"> 
	                  <td>
	                     <a href="javascript:HeartCheck()">
	                     <input type="image" id="cartupdate" src="<%=request.getContextPath()%>/images/heart2.png">
	                     </a>
	                  </td>
              		 </c:if>
				</tr>
			</table>
			<form name='form' action="/Kkuni/orderlist/Order.do" method="get" onsubmit="return MemberCheck(${memId});">
				<table style="width: 500px; height: 300px; margin: 50px 0px 0px 70px;">
					<tr>
						<td><span
							style="font-size: 20px; color: #000000; font-weight: bold;">가격</span></td>
						<td><span
							style="font-size: 20px; color: #B70000; font-weight: bold;">${itemList.price}원</span></td>
					</tr>
					<tr>
						<td><span
							style="font-size: 16px; color: #000000; font-weight: bold;">대여자정보</span></td>
						<td><span style="font-size: 16px; color: #000000;">${itemList.eId}</span>
							<input type="hidden" name="eId" value="${itemList.eId}">
							</td>
					</tr>
					<tr>
						<td><span
							style="font-size: 16px; color: #000000; font-weight: bold;">대여가능기간</span></td>
						<td><span style="font-size: 16px; color: #B70000;">최대 ${itemList.rentalP}일</span></td>
					</tr>
					<tr>
						<td><span style="font-size: 16px; color: #000000; font-weight: bold;">수량</span></td>
						<td>
							<table>
								<tr>
									<td><input type='text' name="order_amount" value='1' size='3' readonly></td>
									<td><img src='<%=request.getContextPath()%>/images/btn_count_up.gif'onclick='javascript_:change(1);'><br>
									<img src='<%=request.getContextPath()%>/images/btn_count_down.gif'onclick='javascript_:change(-1);'></td>
								</tr>
							</table>
					</tr>
					<tr>
						<td><span
							style="font-size: 16px; color: #000000; font-weight: bold;">대여기간</span></td>
						<td><span
							style="font-size: 16px; color: #000000; font-weight: bold;">
								<input name="rentalD1" type="text" id="fromDt" size="6" maxlength="8" title="시작일자" required>
								 ~ 
								<input name="rentalD2" type="text" id="toDt" size="6" maxlength="8" title="종료일자" required>
								<input name="itemNo" type="hidden" value="${itemList.itemNo}">
						</span></td>
					</tr>

				</table>

			<div align="center" style="width: 500px; height: 100px;">
				<table style="margin-top: 30px;">
					<tr>
						<td><input type="button" style="display: block;"
							class="btn_p" value="채팅문의" onclick="window.open('/Kkuni/Chat/ChatMember.do?mId=${mId}&eId=${itemList.eId}','1:1채팅','width=1000,height=700,fullscreen=no')"></td>
						
						<td>
									<input type="submit" style="display: block;" class="btn_b" value="예약">
								</td>
					</tr>
				</table>
			</div>
			</form>
		</div>
		</section>

		<table id=raddressss>
         <tr>
            <td width="300px"
               style="background: #E4E4E4; color: black; font-weight: bold; margin: 0;">반납지주소</td>
            <td style="border: 2px solid #E4E4E4;">(${itemList.rzipcode})
               ${itemList.raddress}</td>
         </tr>
      </table>
		<tiles:insertDefinition name="item_detail" />
	</div>

	<tiles:insertDefinition name="footer" />
</body>
</html>