<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check eq 1 }">
	 <meta http-equiv="Refresh" content="0;url=/Kkuni/Enterprise/Main.do?eId=${eId }"/>
</c:if>

<c:if test="${check eq 0 }">
	<script>
		alert("아이디 또는 비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>

<c:if test="${check eq -1 }">
	<script>
		alert("업체인증 대기중입니다. 문의 010-2944-2149");
		history.go(-1);
	</script>
</c:if>

<c:if test="${check eq 5 }">
	<script>
		alert("관리자입니다 뿌잉");
	</script>
	<meta http-equiv="Refresh" content="0;url=/Kkuni/Admin/Main.do"/>
</c:if>

<%-- <c:set var="entId" value="${eId }" scope="session"/> --%>