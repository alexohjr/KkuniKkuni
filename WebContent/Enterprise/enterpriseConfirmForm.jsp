<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>업체 인증</title>
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/custom.css">
<style>

body {
	line-height: 1.3em;
	width: 100%;
	height:1080px;
}

img {
	magin-left:auto;
	margin-right:auto;
	width:40%;
}

.f {
	margin-left:auto;
	margin-right:auto;
	width:32%;
}

ul {
	list-style:none;
}

br {
	display: block;
	margin: 10px 0;
	line-height: 22px;
	content: " ";
}

select {
	width:20%;
	height:25px;
}


input[type=button] {
	width:80px;
	height:25px;
}

input[type=submit] {
	width:100px;
	height:40px;
}

#cancel {
	width:100px;
	height:40px;
}

h3 {
	color:#FF0000;
}

</style>

</head>
<body>

<div align="center">
<img src="<%=request.getContextPath()%>/image/insertlogo2.png">
</div>

<form method="post" action="/Kkuni/Enterprise/ConfirmPro.do?pageNum=${pageNum }">
<input type="hidden" name="eId" value="${ent.eId }">
<h2 align="center">
		<strong>업체인증</strong>
	</h2>
	<c:if test="${ent.eConfirm eq 'confirm' }">
	<h3 align="center"><strong>인증이 완료된 업체입니다</strong></h3>
	</c:if>
	<br><br><br>
	<fieldset id="f1" class="f">
	<legend>기본정보</legend>
	<ul>
		<li>아이디&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
			${ent.eId }
		</li><br>
	</ul>
	</fieldset>
	<p>&emsp;</p>

	<fieldset id="f2" class="f">
	<legend>업체정보</legend>
	<ul>
		<li>업체명&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
			${ent.eName }
		</li><br>
		<li>사업자 번호&emsp;&emsp;&nbsp;&nbsp;
			${ent.registerNo }
		<li><br>
		<li>
			소재지&emsp;&emsp;&emsp;&emsp;&emsp;우편번호&emsp;&emsp;&nbsp;
			${ent.eZipcode }
		<li>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
			${address1 }
		<li>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
			${address2 }
		<li>
			이메일&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
			${ent.eMail }
		</li><br>
		<li>
			업종&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${ent.eType eq 'space' }">
			공간대여
		</c:if>
		
		<c:if test="${ent.eType eq 'item' }">
			물품대여
		</c:if>
		</li><br>
		<li>통신판매번호&emsp;&nbsp;&nbsp;&nbsp;
			${ent.tmkno }
	</ul>
	</fieldset>
	<p>&emsp;</p>
	
	<fieldset id="f3" class="f">
	<legend>담당자 정보</legend>
	<ul>
		<li>이름&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			${ent.tptbName }
		<li>연락처&emsp;&emsp;&emsp;&emsp;&emsp;
			${ent.tptbTel }
	</ul>
	</fieldset>
	<p>&emsp;</p>
	
	<fieldset id="f4" class="f">
	<legend>정산 정보</legend>
	<ul>
		<li>
		은행&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<c:if test="${ent.eBankName eq '기업' }">
		기업은행
		</c:if>
		
		<c:if test="${ent.eBankName eq '우리' }">
		우리은행
		</c:if>
		
		<c:if test="${ent.eBankName eq '농협' }">
		농협은행
		</c:if>
		
		<c:if test="${ent.eBankName eq '국민' }">
		국민은행
		</c:if>
		
		<c:if test="${ent.eBankName eq '신한' }">
		신한은행
		</c:if>
		</li><br>
		<li>계좌번호&emsp;&emsp;&emsp;&emsp;
		${ent.eAccountNo }
		</li><br>
		<li>예금주&emsp;&emsp;&emsp;&emsp;&emsp;
		${ent.eDepositor }
		</li><br>
	</ul>
	</fieldset>
	<p>&emsp;</p>
	
	<fieldset id="f5" class="f">
	<legend>첨부파일</legend>
	<ul>
		<li>사업자등록증&emsp;&emsp;&emsp;&emsp;
			<img src="${pageContext.request.contextPath }${ent.eFile}" width="10%" height="10%">
		</li>
		
		<li>&emsp;</li>
		<li>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<c:if test="${ent.eConfirm eq 'wait' }">
		<input type="submit" name="submit" value="인증" class="btn_p">
		</c:if> 
		<input type="button" name="cancel" id="cancel" value="목록" class="btn_b" onclick="javascript:history.go(-1)"></li>
	</ul>
	
	</fieldset><br>

</form>

</body>
</html>