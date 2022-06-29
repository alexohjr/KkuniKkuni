<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="footer">

		<div class="link_text">
			<a href="#">회사소개</a>&emsp;&nbsp;&emsp;&nbsp;&nbsp;
			<a href="#">이용약관</a>&nbsp;&emsp;&emsp;&nbsp;&nbsp;
			<a href="#">전자금융거래약관</a>&nbsp;&emsp;&emsp;&nbsp;&nbsp;
			<a href="#">개인정보처리방침</a>&nbsp;&emsp;&emsp;&nbsp;&nbsp;
			<a href="/Kkuni/Enterprise/LoginForm.do">관리자페이지</a>
		</div>
				
		<table class="content">
			<tr>
				<td style="width: 300px;"><a href="/Kkuni/Member/MemberMain.do?mId=${mId }"><img class="footer_logo" src="<%=request.getContextPath()%>/images/logo_m.png"></a></td>
				<td class="footer_text">
						㈜KKUNIKKUNI<br>
						대표이사 : 꾸니 | 사업자등록번호 : 214-87-79183 | 통신판매업신고번호 : 제2018-서울종로-831호<br>
						사업장 소재지 : 서울특별시 중구 남대문로 120 대일빌딩 3층 D강의장<br>
						서울 본사 : 서울특별시 중구 남대문로 120 대일빌딩 3층 D강의장<br>
						개인정보보호책임자 : 꾸니<br>
						고객센터  9292-9292 | Kkuni@Kkuni.com<br>
				</td>
			</tr>
		</table>
</div>
</body>
</html>