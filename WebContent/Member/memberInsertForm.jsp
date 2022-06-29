<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="color.jspf" %> --%>

<html>
<head>
<title>회원 가입</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>

body {
	line-height: 1.3em;
	width: 1920px;
	height:1080px;
}

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

ul {
	list-style:none;
}

select {
	height: 25px;
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

#confirmOk {
	color:#0000ff;
}

b {
	color:#FF0000;
}
</style>


<script src="js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script language="JavaScript">
	
	function checkIt() {
		var userinput = eval("document.userinput");
		
		if(!userinput.userid.value) {
			alert("ID를 입력하세요");
			return false;
		}
		
		if(userinput.idCheck.value != "idCheck") {
			alert("ID 중복체크를 해주세요.")
			return false;
		}
		
		if(userinput.birthyear.value.length <= 3) {
			alert("생년월일을 제대로 입력해주세요.");
			return false;
		}
		
		if(userinput.mailChecked.value != "Checked") {
			alert("메일 중복체크를 해주세요.")
			return false;
		}
		
		if(userinput.codeCheck.value != "codeChecked") {
			alert("이메일인증을 해주세요.");
			return false;
		}
		
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
		
		if(!userinput.username.value) {
			alert("이름을 입력하세요");
			return false;
		}
		
		if(!userinput.bithyear.value || !userinput.birthday.value) {
			alert("생년월일를 입력하세요");
			return false;
		}
	
		if(!userinput.tel.value) {
			alert("휴대폰 번호를 입력하세요");
			return false;
		}	
	
		if(!userinput.email.value) {
			alert("이메일을 입력하세요");
			return false;
		}
		
		if(!userinput.address.value) {
			alert("주소를 입력하세요");
			return false;
		}
		
		if(!userinput.tel.value) {
			alert("휴대폰번호를 입력하세요.");
			return false;
		}
	}
	
	// 아이디 중복 여부를 판단
	
	function openConfirmid(userinput) {
		// 아이디를 입력했는지 검사
		var id = userinput.userid.value;
		var blank_pattern = /^[A-Za-z0-9]{6,10}$/;

		if(userinput.userid.value == "") {
			alert("아이디를 입력하세요");
			return false;
		}
		if(userinput.userid.value.length <= 5) {
			alert("아이디는 6글자이상 사용가능합니다.");
			return false;
		}
		if( blank_pattern.test(id) != true){
	          alert("한글 및 특수문자는 사용불가능 합니다.");
	          return false;
	    }
		
		// url과 사용자 입력 id를 조합합니다.
		url = "/Kkuni/Member/ConfirmId.do?userid="+userinput.userid.value;
			
		
		// 새로운 윈도우를 엽니다.
		open(url, "confirm", "toolbar=no, left=770%, top=400%, location=no,status=no,menubar=no,scrollbars=no,resizeable=no,width=400,height=200");
	}
	
	function zipCheck() {
		url ="/Kkuni/Zipcode/ZipCheck.do"; 
		window.open(url,"post","toolbar=no, left=770%, top=400%, width=500, height=300 directories=no, status=yes, scrollbars=yes, menubar=no");
	} 
	
	function mailConfirm() {
		
		var userinput = eval("document.userinput");
		if(userinput.email.value == "") {
			alert("이메일을 입력하세요.");
			return false;
		}
		
		if(userinput.mailChecked.value != "Checked") {  
			alert("이메일 중복체크를 해주세요.");
			return false;
		}
		url = "/Kkuni/Member/MailConfirm.do?email="+userinput.email.value;
		window.open(url,"post","toolbar=no, left=770%, top=400%, width=390, height=200 directories=no, location=no, status=no, scrollbars=yes, menubar=no");
	}
	
	function mailCheck() {
		var userinput = eval("document.userinput");
		if(userinput.email.value == "") {
			alert("이메일을 입력하세요.");
			return false;
		}
		url = "/Kkuni/Member/MailCheck.do?email="+userinput.email.value;
		window.open(url,"post","toolbar=no, left=770%, top=400%, width=435px, height=200px directories=no, location=no, status=no, scrollbars=yes, menubar=no");
	}
	
	</script>


</head>
<body>
	

	<div align="center">
		<a href="/Kkuni/Member/MemberMain.do">
		<img src="<%=request.getContextPath()%>/image/insertlogo2.png">
		</a>
	</div>

	<form method="post" action="/Kkuni/Member/InsertPro.do" name="userinput" onsubmit="return checkIt()">
	

		<h2 align="center">
			<strong>회원가입</strong>
		</h2>
		<br> <br> <br>

		<fieldset class="f">
		<legend>기본 정보</legend>
		<ul>
			<li>
				아이디<b>*</b>&emsp;&emsp;&emsp;&emsp;&nbsp;
				<input type="text" name="userid" id="userid" style="width:63%" required> 
				<input type="button" name="confirmid" id="confirmid" value="중복 체크" onclick="openConfirmid(this.form)">
				<input type="hidden" name="idCheck" value="idUncheck">
			</li> <br>
			
			<li>
				패스워드<b>*</b>&emsp;&emsp;&emsp;&nbsp; 
				<input type="password" name="userpwd" style="width:63%" required>
			</li> <br> 
			<li>
				패스워드 확인<b>*</b>&emsp; 
				<input type="password" name="userpwd2" style="width: 63%" required>
			</li> <br> 
			<li>
				이름<b>*</b>&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<input type="text" name="username" style="width:63%" required>
			</li><br>
			<li>
				생년월일<b>*</b>&emsp;&emsp;&emsp;&nbsp; 

			<select name="birthyear">
				<c:forEach begin="0" end="${2018-1950 }" var="x">
					<c:set var="a" value="${2018-x }"/>
					<option value="${a}">${a}년</option>
				</c:forEach>
			</select>&emsp;
			
			<select name="birthmonth">
				<c:forEach begin="1" end="12" step="1" var="x">
					<option value="${x}">${x}월</option>
				</c:forEach>
			</select> &emsp; 
			
			<select name="birthday">
				<c:forEach begin="1" end="31" step="1" var="x">
					<option value="${x}">${x}일</option>
				</c:forEach>
			</select></li> <br> 
			
			<li>
				성별<b>*</b>&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<label><input type="radio" name="sex" value="male" checked="checked">남자</label>&emsp;&emsp; 
				<label><input type="radio" name="sex" value="female">여자</label>
			</li>
			
		</ul>
		</fieldset>

		<fieldset class="f">
			<legend>추가 정보</legend>
			<ul>
			<li>
				휴대폰 번호<b>*</b>&emsp;&emsp; 
				<input type="tel" name="tel" style="width: 63%" placeholder="숫자만 입력해주세요.">
			</li>
			<li>
				이메일<b>*</b>&emsp;&emsp;&emsp;&emsp;&nbsp; 
				<input type="email" name="email" style="width:50%" placeholder="ex)abcd@domain.com" required>
				<input type="button" name="emailcheck" id="emailcheck" value="중복체크" onclick="mailCheck()" style="display:inline">
				<input type="button" name="emailconfirm" id="emailconfirm" value="이메일인증" onclick="mailConfirm()" style="display:none"> 
				<b id="confirmOk" style="display:none">인증완료</b>
				<input type="hidden" name="mailChecked" value="Uncheck">
				<input type="hidden" name="codeCheck" value="codeUncheck"> 
			</li>
			<li>
				기본 주소<b>*</b>&emsp;&emsp;&nbsp;&nbsp; 우편번호&nbsp;&nbsp;
				<input type="text" name="zipcode" size="7" readonly required> 
				<input type="button" value="검색" onclick="zipCheck()"> 
			</li><br>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="text" name="address" style="width: 60%" required>
			</li><br>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="text" name="addressdt" style="width: 60%" placeholder="상세주소를 입력해주세요."> 
			</li><br> 
			<li>
			환불계좌
			<select name="bankName">
				<option>은행선택</option>
				<option value="ibk">기업은행</option>
				<option value="woori">우리은행</option>
				<option value="nh">농협은행</option>
				<option value="kb">국민은행</option>
				<option value="sh">신한은행</option>
			</select>
			&nbsp;
			계좌번호&nbsp;<input type="text" name="accountNo" style="width:35%" placeholder="숫자만 입력해주세요.">&nbsp;
			예금주&nbsp;<input type="text" name="depositor" style="width:13%">
			</li>
			<li>&emsp;</li>
			<li>&emsp;</li>
			<li>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<input type="submit" name="submit" value="가입" class="btn_p">
				<input type="button" name="cancel" id="cancel" value="취소" class="btn_b" onclick="javascript:history.go(-1)">
			</li>
		</ul>
		</fieldset>
		
		<!-- <p id="submit">
		<input type="submit" name="submit" value="가입" class="btn_p">
		<input type="button" name="cancel" value="취소" class="btn_b" onclick="javascript:history.go(-1)">
		</p> -->
	</form>

</body>
</html>