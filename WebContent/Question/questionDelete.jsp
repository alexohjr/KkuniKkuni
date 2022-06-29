<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:if test="${check == 1}">
<script>
alert("삭제 완료하였습니다.");
</script>
<meta http-equiv="Refresh" content="0;url=/Kkuni/Question/List.do?pageNum=${pageNum}&mId=${mId}">
</c:if>

<c:if test="${check == 0}">
	<script>
	alert("삭제 권한이 없습니다.");
	history.go(-1);
	</script>
</c:if>