<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check == 1}">
<meta http-equiv="Refresh" content= "0;url=/Kkuni/Answer/AdminDetail.do?questionNo=${questionNo}&pageNum=${pageNum}">
<script>
	alert("수정 완료하였습니다.");
</script>
</c:if>
<c:if test = "${check == 0}">
<script>
	alert("수정 실패하였습니다.");
	history.go(-1);
</script>
</c:if>