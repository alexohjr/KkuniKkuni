<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>1:1 문의글 작성</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript"
	src="../editor/demo/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/button.css">
<script>
	var oEditors = [];
	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "content", //textarea에서 지정한 id와 일치해야 합니다. 
			//SmartEditor2Skin.html 파일이 존재하는 경로
			sSkinURI : "../editor/demo/SmartEditor2Skin.html",
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
				oEditors.getById["content"].exec("PASTE_HTML",
						[ "내용을 입력 해주세요" ]);
			},
			fCreator : "createSEditor2"
		});

		//저장버튼 클릭시 form 전송
		$("#save").click(function() {
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#frm").submit();
		});
	});
	function insertSave() {
		if (document.insertform.qTitle.value == "") {
			alert("제목을 입력하십시오.");
			document.insertform.qTitle.focus();
			return false;
		}
	}
</script>
<style>
.total {
	width: 1410px;
	align: center;
	margin: auto;
	padding: 0;
	overflow: hidden;
	margin-top: -18px;
}

.wrapper {
	width: 1200px;
	margin: auto;
	padding: 0;
	align: center;
	float: right;
	margin-bottom: 30px;
}

.tdqu {
	border: 1px solid #e4e4e4;
}

.trqu {
	border: 1px solid #e4e4e4;
}
</style>

</head>
<body>
	<tiles:insertDefinition name="header" />
	<div class="total">
		<tiles:insertDefinition name="nav" />
		<div class="wrapper">
			<form method="post" name="insertform" action="/Kkuni/Question/InsertPro.do" onsubmit="return insertSave()" style="width: 1200px; margin-left: 10px;">
				<!-- "/Kkuni/Question/questionInsertForm.do" -->
				<div>
					<h3>1:1 문의글 등록</h3>

				</div>
				<hr width="100%" style="border: 0.8px solid #e4e4e4;">
				<table width="1150" style="border: 1px solid #e4e4e4; margin-top: 20px;">
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center" width="100px">분류</td>
						<td class="tdqu" colspan="5" width="200px"><select id="sort" name="qSort">
							<option value="1">상품</option>
							<option value="2">배송</option>
							<option value="3">결제</option>
							<option value="4">계정</option>
						</select>
						<td class="tdqu" colspan="2" align="center" width="200px">작성자</td>
						<td class="tdqu" colspan="3" align="center" width="200px">${mId }</td>
						<input type="hidden" name="mId" value="${mId }">
					</tr>
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center">제목</td>
						<td class="tdqu" colspan="10"><input type="text" id="qTitle" name="qTitle" maxlength="50" style="width: 99%" /></td>
					</tr>
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center">내용</td>
						<td class="tdqu" colspan="10">
						<textarea rows="10" cols="30" id="content" name="qContents" style="width: 100%; height: 350px;">
                  		</textarea>
                  		</td>
					</tr>
				</table>
					<div align="right" colspan="12" style="width: 1150px">
					<input class="btn_p" type="submit" id="save" value="등록" />
					<input type="button" class="btn_b" value="목록" onclick="window.location='/Kkuni/Question/List.do?mId=${mId}'">
					</div>
			</form>
		</div>
	</div>
	
	<tiles:insertDefinition name="footer" />
</body>
</html>