<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="editor/demo/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script language="JavaScript" src="noticeFunction.js"></script>
<script>
	var oEditors = [];
	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
			//SmartEditor2Skin.html 파일이 존재하는 경로
			sSkinURI : "editor/demo/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : false,
				fOnBeforeUnload : function() {

				}
			},
			fOnAppLoad : function() {
				//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
				oEditors.getById["ir1"].exec("PASTE_HTML",
						[ "내용을 입력 해주세요" ]);
				
				
			},
			fCreator : "createSEditor2"
		});

		//저장버튼 클릭시 form 전송
		$("#save").click(function() {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#frm").submit();
		});
	});
	function insertSave() {
		if (document.insertform.nTitle.value == "") {
			alert("제목을 입력하십시오.");
			document.insertform.nTitle.focus();
			return false;
		}
				
	}
</script>
<link href="<%=request.getContextPath()%>/css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
</head>
<body>
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />
<div class="body_size">
	<div style="width:1000px; position: absolute; left: 300px; " >
			<form method="post" name="updateform"  enctype="multipart/form-data"  action="/Kkuni/Notice/UpdatePro.do?noticeNo=${article.noticeNo}&pageNum=${pageNum}" onsubmit="return insertSave()">
			<table  width="1000" align="center">
				<tr>
					<td colspan="8"><h2>공지사항</h2>
					<td colspan="4" align="right">게시판관리 > 공지사항 > 글등록</td>
				</tr>
			</table>
			<hr width="1000">		
			<table width="1000" border=1 cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2" align="center">분류</td>
					<td colspan="5" >
					<c:if test="${article.nSort eq 'e'}">
					<select id="selecter" name="selecter">
            		<option value="1" selected >관리공지</option>
					<option value="2">일반공지</option>
         			</select>
         			</c:if>
					<c:if test="${article.nSort eq 'm'}">
					<select id="selecter" name="selecter">
            		<option value="1" >관리공지</option>
					<option value="2" selected>일반공지</option>
         			</select>
         			</c:if>
					<td style="background-color:#e4e4e4;" colspan="2" align="center">작성자</td>
					<td colspan="3" align="center">관리자</td>
				</tr>
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2" align="center" >제목</td>
					<td colspan="10">
					<input type="text" id="nTitle" name="nTitle" value="${article.nTitle}"	maxlength="50" style="width: 99.5%" /></td>
				</tr>
				<tr>
					<td style="background-color:#e4e4e4;" colspan="2"align="center">첨부파일</td>
					<td colspan="10">
					<input type=file name=nFile />${article.nFile}</td>
				</tr>
				<tr>
					<td colspan="10">
						<textarea rows="10" cols="30" id="ir1" name="nContents" style="width: 100%; height: 350px;">
						</textarea>
					</td>
				</tr>
				</table>
					<div align="right">
						<input class="btn_p" type="submit" id="save" value="수정" />
						<input class="btn_b" type="button" value="목록" onclick="window.location='/Kkuni/Notice/NoticeAdminList.do'">
					</div>
		</form>


</div>
</body>
</html>