<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="../css/admin.css" rel="stylesheet" type="text/css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		  jQuery(".list-item").hide();
		  $(".list").click(function()
		  {
		    $(".list-item").not($(this).next(".list-item").slideToggle(500)).slideUp();
		  });
		});
</script>

</head>
<body>
	<div class="menu">

		<div class="admin_profile">
		 <form name="myform" action="/Kkuni/Enterprise/ModifyForm.do" method="post">
		 <input type="hidden" name="eId" value="${eId}">
			<img class="admin_img" src="../images/user.png" /><br>
			<h4><%-- <%=session.getAttribute("eId") %> --%>
			 <%=request.getParameter("eId") %><input type="image" src="../images/gear.png" style="width: 18px; margin: 0px -5px 2px 3px;"></h4>
				<a href="/Kkuni/Member/Logout.do">로그아웃</a>
			</form>
			<hr>
		</div>


		<div class="accordion">
			<div class="list"><a href="#">상품관리<img src="../images/arrow_white.png" style="margin: 10px 0px 0px 80px;"></a></div>
				<div class="list-item">
					<a href="/Kkuni/Item/EnterpriseList.do?eId=${eId}">상품조회</a><br>
					<a href="/Kkuni/Item/InsertForm.do?eId=${eId}">상품등록</a><br>
				</div>
			<p class="list"><a href="/Kkuni/orderlist/OrderEnterpriseList.do?eId=${eId}">주문조회</a></p>
			<p class="list"><a href="/Kkuni/Chat/ChatEnterPriseList.do?eId=${eId}">똑똑친구</a></p>
			<p class="list"><a href="#">게시판관리<img src="../images/arrow_white.png" style="margin: 10px 0px 0px 80px;"></a></p>
			<div class="list-item">
				<a href="/Kkuni/Notice/NoticeEnterpriseList.do?eId=${eId}">공지사항</a><br>
				<a href="/Kkuni/Question/EnterpriseList.do?eId=${eId}">1:1문의글</a><br>
			</div>
			
		</div>
	</div>
</body>
</html>