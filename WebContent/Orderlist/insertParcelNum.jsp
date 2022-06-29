<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>운송장번호</title>
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
<script>
	function NumCheck() {
		if (document.parcelNumForm.parcelNum.value == "") {
			alert("운송장 번호를 입력해주세요");
			document.parcelNumForm.parcelNum.focus();
			return;
		}
		
		var blank_pattern = /[\s]/g;
		if( blank_pattern.test(document.parcelNumForm.parcelNum.value) == true){
			alert("운송장 번호를 입력해주세요");
			document.parcelNumForm.parcelNum.focus();
			return;
		}

		document.parcelNumForm.submit();
	}
	
	function Frmclose() {
		opener.parent.location.reload();
		window.close();
	}
	
</script>
</head>
<body>
<div align="center">
	<div align="left">
			<h2>운송장번호</h2>
			<hr>
		</div>
	<table>
		<form name="parcelNumForm" method="post" action="/Kkuni/orderlist/InsertParcelNumPro.do">
		<input type="hidden" name="orderNo" value="${orderlist.orderNo}">
		<c:choose>
			<c:when test="${check eq 'y'}">
				<tr>
					<td><br>
						완료되었습니다.
					</td>
				</tr>
				
				<tr>
					<td align="center"><br><input type="button" class="btn" value="닫기" onclick="Frmclose();"></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:choose>
						    <c:when  test="${empty orderlist.parcelNum}">
								<tr>
									<td><br>
										번호입력 : <input name="parcelNum" type="text">
									</td>
								</tr>
								<tr>
									<td align="center"><br>
										<input type="button" class="btn btn_p" value="등록" onclick="NumCheck();">
										<input type="button" class="btn" value="닫기" onclick="Frmclose();">
									</td>
								</tr>	    
							</c:when>
						
						    <c:otherwise>
						      	<tr>
									<td><br>
										번호수정 : <input name="parcelNum" type="text" value="${orderlist.parcelNum}">
									</td>
								</tr>
									<tr>
									<td align="center"><br>
										<input type="button" class="btn btn_p" name="parcelState" value="수정" onclick="NumCheck();">
										<input type="button" class="btn" value="닫기" onclick="Frmclose();">
									</td>
								</tr>
						    </c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
			<input type="hidden" name=orderNo value="${orderNo}">
		</form>
	</table>
	
</div>
</body>
</html>