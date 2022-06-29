<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>

<c:when test="${check eq 1 }">
	<meta http-equiv="Refresh" content="0;url=/Kkuni/Member/MemberMain.do?mId=${mId }"/>
</c:when>

<c:when test="${check eq 0 }">
	<script>
		alert("아이디 또는 비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:when>

<c:when test="${check eq -1 }">
	<script>
		alert("탈퇴 회원입니다.");
		history.go(-1);
	</script>
</c:when>
</c:choose>
 

