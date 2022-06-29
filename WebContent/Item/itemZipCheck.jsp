<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>우편번호검색</title>
<!-- <link href="style.css" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
<script>
	function dongCheck() {
		if (document.zipForm.area4.value == "") {
			alert("주소를 입력하세요.");
			document.zipForm.area4.focus();
			return;
		}
		document.zipForm.submit();
	}

	function sendAddress(zipcode, area1, area2, area3, area4) {
		var address = area1 + " " + area2 + " " + area3 + " " + area4;
		opener.document.iteminsert.rzipcode.value = zipcode;
		opener.document.iteminsert.address.value = address;
		self.close();
		
	}
</script>
</head>
<body>
	<div margin="auto" align="center">
		<b>우편번호 찾기</b>
		<table>
			<form name="zipForm" method="post" action="/Kkuni/Item/ZipCheck.do">
				<tr>
					<td><br> 도로명 주소 입력 : <input name="area4" type="text">
						<input type="button" value="검색" class="btn_p" style="height:25px" onclick="dongCheck();"></td>
				</tr>
				<input type="hidden" name="check" value="n">
			</form>
			<c:if test="${check == 'n' }">
			<c:if test="${empty zipcodeList }">
			<tr>
				<td align="center"><br>검색된 결과가 없습니다.</td>
			</tr>
			</c:if> 
			
			<tr>
				<td align="center"><br> *검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다.</td>
			</tr>
			
			<c:forEach var="i" items="${zipcodeList}" end="${totalList }">
			
			<tr>
				<td><a href="javascript:sendAddress(
						'${i.zipcode }', '${i.area1 }', '${i.area2 }', '${i.area3 }', '${i.area4 }')">
						${i.zipcode }&nbsp;${i.area1 }&nbsp;${i.area2 }&nbsp;${i.area3 }&nbsp;${i.area4 }</a><br>
			</td>
			</tr>
			
			</c:forEach>
			</c:if>
			<tr>
				<td align="center"><br>
				<input type="button" value="닫기" class="btn_b" onclick="self.close()"></td>
			</tr>
		</table>
		
	</div> 

</body>
</html>