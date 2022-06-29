function insertSave() {
	
	if (document.insertform.nContens.value == "") {
		alert("내용를 입력하십시오.");
		document.insertform.nContens.focus();
		return false;
	}
	if (document.insertform.nTitle.value == "") {
		alert("제목을 입력하십시오.");
		document.insertform.nTitle.focus();
		return false;
	}
	
}
