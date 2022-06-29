function submitCheck() {
	
	var userinput = eval("document.userinput");
		
	if(userinput.idCheck.value != "idCheck") {
		alert("ID 중복체크를 해주세요.");
		return false;
	}
	
	if(userinput.ePw.value != userinput.ePw2.value) {
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	} 
		
	if(userinput.mailChecked.value != "Checked") {
		alert("메일 중복체크를 해주세요.");
		return false;
	}
		
	if(userinput.codeCheck.value != "codeChecked") {
		alert("이메일인증을 해주세요.");
		return false;
	}
		
	if(!userinput.eFile.value) {
		alert("사업자등록증 파일을 첨부해주세요.");
		return false;
	}
		
	if(!userinput.eId.value) {
		alert("아이디를 입력하세요");
		return false;
	}  
	
	if(userinput.idCheck.value != "idCheck") {
		alert("ID 중복체크를 해주세요.")
		return false;
	}
	
	if(!userinput.ePw.value) {
		alert("비밀번호를 입력하세요");
		return false;
	}
	
	if(!userinput.ePw2.value) {
		alert("비밀번호를 입력하세요");
		return false;
	}
	
	if(userinput.ePw.value != userinput.ePw2.value) {
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	} 
	
	if(!userinput.eName.value) {
		alert("업체명을 입력하세요");
		return false;
	}
	
	if(userinput.mailChecked.value != "Checked") {
		alert("메일 중복체크를 해주세요.");
		return false;
	}
	
	if(userinput.codeCheck.value != "codeChecked") {
		alert("이메일인증을 해주세요.");
		return false;
	}
	
	if(!userinput.registerNo.value) {
		alert("사업자번호를 입력하세요");
		return false;
	}
	
	if(!userinput.address.value) {
		alert("주소를 입력하세요");
		return false;
	}
	
	if(!userinput.eMail.value) {
		alert("이메일을 입력하세요");
		return false;
	}
	
	if(!userinput.tmkno.value) {
		alert("통신판매번호를 입력하세요.");
		return false;
	}
	
	if(!userinput.eAccountNo.value) {
		alert("정산 계좌번호를 입력해주세요.");
		return false;
	}

	if(!userinput.eDepositor.value) {
		alert("정산 예금주를 입력해주세요.");
		return false;
	}
	
	if(!userinput.eFile.value) {
		alert("사업자등록증 파일을 첨부해주세요.");
		return false;
	}
} 

function openConfirmid(userinput) {
	// 아이디를 입력했는지 검사
	var id = userinput.eId.value;
	if(userinput.eId.value == "") {
		alert("아이디를 입력하세요");
		return false;
	}
	if(userinput.eId.value.length <= 5) {
		alert("아이디는 6글자이상 사용가능합니다.");
		return false;
	}
	
	var blank_pattern = /^[A-Za-z0-9]{4,10}$/;
 
	if( blank_pattern.test(id) != true){
          alert("한글 및 특수문자는 사용불가능 합니다.");
          return false;
    }
	
	url = "/Kkuni/Enterprise/ConfirmId.do?eId="+userinput.eId.value;
	open(url, "confirm", "toolbar=no, left=770%, top=400%, location=no,status=no,menubar=no,scrollbars=no,resizeable=no,width=435,height=200");
}

function zipCheck() {
	url ="/Kkuni/Zipcode/ZipCheck.do"; 
	window.open(url,"post","toolbar=no, left=770%, top=400%, width=500, height=300 directories=no, status=yes, scrollbars=yes, menubar=no");
}

function mailCheck() {
	var userinput = eval("document.userinput");
	if(userinput.eMail.value == "") {
		alert("이메일을 입력하세요.");
		return false;
	}
	url = "/Kkuni/Enterprise/MailCheck.do?eMail="+userinput.eMail.value;
	window.open(url,"post","toolbar=no, left=770%, top=400%, width=435px, height=200px directories=no, location=no, status=no, scrollbars=yes, menubar=no");
}

function mailConfirm() {
	var userinput = eval("document.userinput");
	url = "/Kkuni/Enterprise/MailConfirm.do?eMail="+userinput.eMail.value;
	window.open(url,"post","toolbar=no, left=770%, top=400%, width=390, height=200 directories=no, location=no, status=no, scrollbars=yes, menubar=no");
}