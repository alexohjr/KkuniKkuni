<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
		  {"WebContent/images/user.png"
		    $(".list-item").not($(this).next(".list-item").slideToggle(500)).slideUp();
		  });
		});
</script>
</head>
<body>
	<div class="menu">

		<div class="admin_profile">
			<form action="/Kkuni/Enterprise/MainSliderForm.do">
			<img class="admin_img" src="<%=request.getContextPath() %>/images/user.png" /><br>
			<h4>관리자님<input type="image" src="../images/gear.png" style="width: 18px; margin: 0px -5px 2px 3px;"></h4>
			<a href="/Kkuni/Enterprise/LoginForm.do">로그아웃</a>
			<hr style="border: 0.5px solid #E4E4E4;">
			</form>
		</div>


		<div class="accordion">
			<div class="list"><a href="#">사용자메뉴<img src="<%=request.getContextPath() %>/images/arrow_white.png" style="margin: 10px 0px 0px 80px;"></a></div>
				<div class="list-item">
					<a href="/Kkuni/Member/AdminList.do">회원관리</a><br>
					<a href="/Kkuni/Enterprise/AdminList.do">업체관리</a><br>
				</div>
			<p class="list"><a href="/Kkuni/Item/AdminList.do">상품조회</a></p>
			<p class="list"><a href="/Kkuni/orderlist/OrderAdminList.do">주문관리</a></p>
			<p class="list"><a href="#">게시판관리<img src="<%=request.getContextPath() %>/images/arrow_white.png" style="margin: 10px 0px 0px 80px;"></a></p>
			<div class="list-item">
				<a href="/Kkuni/Notice/NoticeAdminList.do">공지사항</a><br>
				<a href="/Kkuni/Answer/AdminList.do">1:1문의글</a><br>
			</div>
			
		</div>
	</div>
</body>
</html>