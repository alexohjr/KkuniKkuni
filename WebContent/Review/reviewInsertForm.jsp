<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>후기 작성</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<style>

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
	width: 400px;
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

<script type="text/javascript" src="../editor/demo/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script language="JavaScript" src="noticeFunction.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>  
	
	function insertSave() {
	   if (!document.reviewInsert.rContents.value) {
		   alert("후기 내용을 입력해주세요.");
		   document.reviewInsert.rContents.focus();
		   return false;
	   }
	   
	   if(!document.reviewInsert.rFile.value) {
	         alert("후기 이미지파일을 첨부해주세요.");
	         return false;
	      }
   }
	
	function LoadImg(value) {
		if(value.files && value.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#LoadImg').attr('src', e.target.result);
			}
			reader.readAsDataURL(value.files[0]);
		}
	}	

</script>

</head>
<body>
     <div class="wrap">
		<section>
			<div id="list1">
				<img id="LoadImg" width=600px; height=600px;>
			</div>
			<div id="list2">
			<form method="post" name="reviewInsert" enctype="multipart/form-data" action="/Kkuni/Review/InsertPro.do" onsubmit="return insertSave()">
				<table width=430>
					<tr>
						<td width="100px">작성자</td>
						<td id ="writer">${mId}</td>
						<input type="hidden" name="mId" value="${mId }">
					</tr>
					<tr rowspan=6>
						<td colspan=3><hr>
							<textarea cols=55 rows=24 name="rContents" style="resize:none;" placeholder="후기 내용을 입력해주세요."></textarea>
						</td> 
					</tr>
					
					<tr>
						<td colspan=3 style="text-align:left;"><hr>
							<input type="file" id="imgAttach" name="rFile" onchange="LoadImg(this);">
						</td>
					</tr>
					
					<tr align="right">
						<td colspan=3>
						<input type="hidden" name="itemNo" value="${itemNo }">
						<input type="hidden" name="mId" value="${mId }">
						<input type="submit" value="등록" id="modify" class="btn_p">
						<input type="button" value="취소" id="dlt" class="btn_b" name="dlt" onclick="self.close();">
						</td>
					</tr>
				</table>
			</form>
			</div>
		</section>
		</div>
</body>
</html>