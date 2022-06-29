<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>1:1 문의 글 상세</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/button.css">
<script type="text/javascript"
	src="../editor/demo/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script language="JavaScript" src="noticeFunction.js"></script>
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
	
	function delete_check() {
		if(confirm("정말 삭제하시겠습니까?") == true){
			return true;
		}
		else {
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

			<form method="post" action="/Kkuni/Question/Delete.do?questionNo=${article.questionNo}&pageNum=${pageNum}" style="width: 1200px; margin-left: 10px;" onsubmit="return delete_check()">
				<!-- "/Kkuni/Question/questionDetail.do" -->
				<div>
					<h3>1:1 문의 글 상세</h3>

				</div>
				<hr width="100%" style="border: 0.8px solid #e4e4e4;">
				<table width="1150"	style="border: 1px solid #e4e4e4; margin-top: 20px;">
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center" width="100px">분류</td>
						<td class="tdqu" colspan="5" width="200px">
						<c:if test="${article.qSort == 'item'}">상품</c:if>
						<c:if test="${article.qSort == 'delivery'}">배송</c:if>
						<c:if test="${article.qSort == 'pay'}">결제</c:if>
						<c:if test="${article.qSort == 'member'}">계정</c:if>
						</td>
						<td class="tdqu" colspan="2" align="center" width="200px">작성자</td>
						<td class="tdqu" colspan="3" align="center" width="200px">${article.mId}</td>
						<input type="hidden" name="mId" value="${mId }">
					</tr>
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center">제목</td>
						<td class="tdqu" colspan="10">${article.qTitle}</td>
					</tr>
					<tr class="trqu">
						<td class="tdqu" colspan="2" align="center">내용</td>
						<td class="tdqu" colspan="10">
							<div rows="10" cols="30" style="width: 100%; height: 350px;">
							${article.qContents}</div>
						</td>
					</tr>
				</table>
				<div align="right" colspan="12" style="width: 1150px">
					<input type="submit" id="save" class="btn_b" value="삭제" />
					<input type="button" class="btn_p" value="목록" onclick="javascript:history.go(-1)">
				</div>

				<br>
				<c:if test="${answerCount > 0 }">
					<div width="1000">
						<table width="1000" align="left">
							<tr>
								<td>답변</td>
							</tr>
						</table>
						<table style="border: 1px solid #e4e4e4; margin-left: 0px;"	width="1150" align="center">
							<form method="post">
								<input type="hidden" name="questionNo" value="${questionNo}">
								<tr class="trqu">
									<td class="tdqu">관리자<br>${articleAnswer.aDate}</td>
									<td class="tdqu" colspan=4><div cols=130 name="aContents" rows=5 style="resize: none; border: none;" readonly="readonly">
									${articleAnswer.aContents}</div></td>
								</tr>
						</table>
				</c:if>
			</form>
		</div>
</body>
</html>
