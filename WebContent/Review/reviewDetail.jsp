<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>후기 상세페이지</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>
/* 목록의 글머리에 아무것도 표시하지 않음 */

.wrap {
	width: 1050px;
	margin: auto;
	padding: 0;
	overflow: hidden;
	padding: 0;
}

#list1 {
	top: 10px;
	width: 600px;
	height: 600px;
	display: inline-block;
	margin: 0 auto;
	border: 1px solid #E4E4E4;
	float: left;
}

#list2 {
	top: 10px;
	width: 420px;
	height: 500px;
	display: inline-block;
	margin-left: 10px;
	float: left;
	text-align: center;
} 

#dlt {
	height:25px;
	width:80px;
}

#modify {
	height:25px;
	width:80px;
}


</style>

<script>

	function checkR() {
		if(document.reviewform.reviewmId.value != document.reviewform.mId.value) {
			alert("작성자만 수정할 수 있습니다.");
			return false
		}
	}
	
	function checkR2() {
		if(document.deleteform.reviewmId.value != document.updateform.mId.value) {
			alert("작성자만 수정할 수 있습니다.");
			return false;
		}
	}
	
	function reviewDelete() {
		
		if(document.reviewform.reviewmId.value != document.reviewform.mId.value) {
			alert("작성자만 삭제할 수 있습니다.");
			return false;
		}
		
		if(confirm("정말 삭제하시겠습니까?") == true) {
			var reviewNo = document.reviewform.reviewNo.value;
			location.href="/Kkuni/Review/Delete.do?reviewNo="+reviewNo;
		} else {
			return;
		}
	}

</script>
</head>
<body>
	<div class="wrap">
		<section>
			<div id="list1">
				<img src="${pageContext.request.contextPath}${article.rFile}"
					width=600px; height=600px;>
			</div>
			<div id="list2">
				<table width="430">
					<tr>
						<td rowspan=2 width=100 height=100><img src="${pageContext.request.contextPath}${article.tRealpath}" width=100px; height=100px; /></td>
						<td width="100px">작성자</td>
						<td text-align="center">${article.mId}</td>
					</tr>
					<tr>
						<td width="100px">상품명</td>
						<td id = "itemId">${article.itemId}</td>
					</tr>
					<tr rowspan=6>
						<td colspan=3><hr>
							<textarea cols=55 rows=24 name=rContents value="${article.rContents }"style="resize:none;" readonly="readonly">${article.rContents }</textarea>
						</td> 
					</tr>
					<tr align="right" vertical-align=middle>
						<td colspan=3 width=408><hr>
						<form method="post" name="reviewform" action="/Kkuni/Review/UpdateForm.do" onsubmit="return checkR()">
						<input type="hidden" name="reviewNo" value="${reviewNo }">
						<input type="hidden" name="reviewmId" value="${article.mId }">
						<input type="hidden" name="mId" value="${mId }">
						<input type="hidden" name="tRealpath" value="${article.tRealpath}">
						<input type="hidden" name="itemId" value="${article.itemId}">
						<input type="submit" value="수정" id="modify" class="btn_p">
						<input type="button" value="삭제" id="dlt" class="btn_b" name="dlt" onclick="reviewDelete()">
						</form>
						</td>
					</tr>
				</table>
			</div>
		</section>
		</div>
</body>
</html>