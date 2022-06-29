<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ include file="color.jspf" %> --%>

<html>
<head>
<title>회원 정보수정</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>

div {
	margin-left: auto;
	margin-right: auto;
}

.f {
	margin-left: auto;
	margin-right: auto;
	width: 32%;
	height: 30%;
	border: 1px;
}

body {
	line-height: 1.3em;
	width:1920px;
	height:1080px;
}

img {
	margin-left: auto;
	margin-right: auto;
	width: 40%;
}

br {
	display: block;
	margin: 10px 0;
	line-height: 22px;
	content: " ";
}

select {
	height: 25px;
}

ul {
	list-style:none;
}

.gray {
	background-color:lightgrey;
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

</style>

<script src="js/bootstrap.js"></script>
<script language="JavaScript">
	
	function checkIt() {
		var userinput = eval("document.userinput");
		
		if(!userinput.userpwd.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if(!userinput.userpwd2.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}

		if(userinput.userpwd.value != userinput.userpwd2.value) {
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
	
		if(!userinput.tel.value) {
			alert("휴대폰 번호를 입력하세요");
			return false;
		}	
		
		if(!userinput.address.value) {
			alert("주소를 입력하세요");
			return false;
		}
	}
	
	function zipCheck() {
		url ="/Kkuni/Zipcode/ZipCheck.do"; 
		window.open(url,"post","toolbar=no, left=550, top=250, width=500, height=300 directories=no, status=yes, scrollbars=yes, menubar=no");
	}

	</script>
</head>
<body>
	

	<div align="center">
		<a href="/Kkuni/Member/MemberMain.do">
		<img src="<%=request.getContextPath()%>/image/insertlogo2.png">
		</a>
	</div>

	<form method="post" action="/Kkuni/Member/ModifyPro.do" name="userinput" onsubmit="return checkIt()">
	

		<h2 align="center">
			<strong>회원정보수정</strong>
		</h2>
		<br> <br> <br>

		<fieldset class="f">
		<legend>기본 정보</legend>
		<ul>
			<li>
				아이디&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<input type="text" name="userid" class="gray" value="${member.mId }" style="width:63%" readonly="readonly"> 
				<input type="hidden" name="mId" value="${mId }"> 
			</li><br>
			<li>
				패스워드&emsp;&emsp;&emsp;&nbsp;
				<input type="password" name="userpwd" value="${member.mPw }" style="width:63%" required>
			</li><br>
			<li>
				패스워드 확인&emsp;
				<input type="password" name="userpwd2" value="${member.mPw }" style="width:63%" required>
			</li><br> 
			<li>
				이름&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<input type="text" name="username" class="gray" value="${member.mName }"style="width:63%" readonly="readonly">
			</li><br>
			<li>
				생년월일&emsp;&emsp;&emsp;&nbsp; 
				<input type="text" name="birthyear" class="gray" value="${birthday}" size="10" style="width:16%" readonly="readonly">
			</li><br> 
			<li>
				성별&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<c:if test="${member.sex == 'F'}">
					<input type="text" name="sex" class="gray" style="width:10%" value="여자" readonly="readonly">
				</c:if>
				
				<c:if test="${member.sex == 'M' }">
					<input type="text" name="sex" class="gray" style="width:10%" value="남자" readonly="readonly">
				</c:if>
			</li>
		</ul>
		</fieldset>

		<fieldset class="f">
			<legend>추가 정보</legend>
			<ul>
			<li>
				휴대폰 번호&emsp;&emsp;
				<input type="tel" name="tel" value="${member.mTel }" style="width:20%">
			</li><br>
			<li>
				이메일&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<input type="email" name="email" class="gray" value="${member.mMail }" style="width:43%" readonly="readonly">
			</li><br>
			<li>
				기본 주소 &emsp;&emsp;&nbsp;&nbsp; 우편번호&nbsp;&nbsp;
				<input type="text" name="zipcode" value="${member.mZipcode }" size="7" required> 
				<input type="button" value="검색" onclick="zipCheck()"> 
			</li><br>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="text" name="address" value="${address1 }" style="width: 60%" required>
			</li><br>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<c:if test="${empty address2 }">
				<input type="text" name="addressdt" style="width: 60%" placeholder="상세주소를 입력해주세요.">
				</c:if>
	
				<c:if test="${!empty address2 }">
				<input type="text" name="addressdt" value="${address2 }" style="width: 60%" placeholder="상세주소를 입력해주세요.">
				</c:if>
			</li><br>
			<li>
			환불계좌
			<c:if test="${empty member.mBankName }">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk">기업은행</option>
				<option value="woori">우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			</c:if>
			
			<c:if test="${member.mBankName eq '기업'}">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk" selected>기업은행</option>
				<option value="woori">우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			</c:if>
			<c:if test="${member.mBankName eq '우리'}">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk" >기업은행</option>
				<option value="woori" selected>우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			</c:if>
			<c:if test="${member.mBankName eq '농협'}">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk" >기업은행</option>
				<option value="woori" >우리은행</option>
				<option value="nh" selected>농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			</c:if>
			<c:if test="${member.mBankName eq '국민'}">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk" >기업은행</option>
				<option value="woori">우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb" selected>국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			</c:if>
			<c:if test="${member.mBankName eq '신한'}">
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk" >기업은행</option>
				<option value="woori">우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh" selected>신한은행</option>
			</select>
			</c:if>
			
			<c:if test="${empty member.mAccountNo }">
			계좌번호&nbsp;<input type="text" name="accountNo" style="width:35%" placeholder="숫자만 입력해주세요.">&nbsp;
			</c:if>
			<c:if test="${!empty member.mAccountNo }">
			계좌번호&nbsp;<input type="text" name="accountNo" value="${member.mAccountNo }" style="width:35%" placeholder="숫자만 입력해주세요.">&nbsp;
			</c:if>
			
			<c:if test="${empty member.mDepositor }">
			예금주&nbsp;<input type="text" name="depositor" style="width:13%">
			</c:if>
			<c:if test="${!empty member.mDepositor }">
			예금주&nbsp;<input type="text" name="depositor" value="${member.mDepositor }" style="width:13%">
			</c:if>
			</li>
			<li>&emsp;</li>
			<li>&emsp;</li>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="submit" name="submit" value="수정" class="btn_p"> 
				<input type="button" name="cancel" id="cancel" value="취소" class="btn_b" onclick="javascript:history.go(-1)">
			</li>
				
		</ul>
		</fieldset>
		
		
	
	</form>

</body>
</html>