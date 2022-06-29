<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="header">
		
		<div class="header_right">
		
		<c:if test="${mId eq null }">
		<a href="/Kkuni/Member/LoginForm.do" class="date">로그인</a> |
		<a href="/Kkuni/Member/InsertForm.do" class="date">회원가입</a> |
		</c:if>
		
		<c:if test="${mId != null }">
		<a href="/Kkuni/Member/Logout.do?mId=${mId }" class="date">로그아웃</a> |
		<a href="/Kkuni/Member/MyPage.do?mId=${mId }" class="date">마이페이지</a> |
		</c:if>
		
		<a href="/Kkuni/Notice/NoticeList.do" class="date">고객센터</a>
		</div>
		
		<form method="post" action="/Kkuni/Item/HeaderSearch.do"> 
			<div class="header_left">
				<a href="/Kkuni/Member/MemberMain.do"><img class="header_logo" src="<%=request.getContextPath()%>/images/logo_m.png"></a>
				
				<input type="text" name="search" class="text_search">
				<button class="btn_search"><i class="fa fa-search"></i></button>
			
			</div>
		</form>

		<div align="center">
			<a href="/Kkuni/Item/List.do?cNo=100"><button class="btn_header">유아용품</button></a>
			<a href="/Kkuni/Item/List.do?cNo=200"><button class="btn_header">스포츠/레저</button></a>
			<a href="/Kkuni/Item/List.do?cNo=300"><button class="btn_header">음향기기</button></a>
			<a href="/Kkuni/Item/List.do?cNo=400"><button class="btn_header" >디지털가전</button></a>
			<a href="/Kkuni/Item/List.do?cNo=500"><button class="btn_header">공간대여</button></a>
		</div>
</div>
</body>
</html>