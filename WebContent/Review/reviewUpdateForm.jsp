<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>후기 수정</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
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

<script language="JavaScript"></script>
<script>
	function updateSave() {
	   if (!document.updateform.aContents.value) {
		   alert("내용을 입력하십시오.");
		   document.updateform.aContents.focus();
		   return false;
	   }
   }
</script>

</head>
<body>
	<div class="wrap">
		<section>
			<div id="list1">
				<img src="${pageContext.request.contextPath}${article.rFile}" width=600px; height=600px;>
			</div>
			<div id="list2">
				<table width=430>    
					<tr>
						<td rowspan=2 width=100 height=100><img src="${pageContext.request.contextPath}${tRealpath}" width=100px; height=100px; /></td>
						<!-- <input type = "button" value="닫기" id="exit" onclick="self.close();"> -->
						<td width="100px">작성자</td>
						<td text-align="center">${article.mId}</td>
					</tr>
					<tr>  
						<td width="100px">상품명</td>
						<td id = "itemId">${itemId}</td>
					</tr>
						
						<form method="post" name="updateform" action="/Kkuni/Review/UpdatePro.do" enctype="multipart/form-data" onsubmit="return updateSave()">
					
					<tr rowspan=6>
						<td colspan=3><hr>
							<textarea cols=55 rows=24 name=rContents value="${article.rContents }"style="resize:none;">${article.rContents }</textarea>
						</td> 
					</tr>
					
					<tr>
						<td colspan=3 style="text-align:left;"><hr>
							<input type="file" name="rFile">
						</td>
					</tr>
					
					<tr align="right">
						<td colspan=3>
						<input type="hidden" name="reviewNo" value="${article.reviewNo }">
						<input type="hidden" name="reviewmId" value="${article.mId }">
						<input type="hidden" name="mId" value="${mId }">
						<input type="hidden" name="rFileog" value="${article.rFile}">
						<input type="submit" value="수정" id="modify" class="btn_p">
						<input type="button" value="취소" id="dlt" class="btn_b" onclick="javascript:history.go(-1)">
						</td>
					</tr>

						</form>
				
				</table>
			</div>
		</section>
		</div>
</body>
</html>