<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
<title>업체관리</title>
<link rel="stylesheet" href="../s_css/button.css">
<link rel="stylesheet" href="../s_css/list.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
	.size{
		width: 1500px;
	}
	
	.abc{
		color: black;
	}
</style>
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />


  <div style="background-color: white; width: 1680px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">

	<div class="pageSize size">
	<br>
		<div align="left">
			<h2>업체관리</h2>
			<hr>
		</div>
		
		<div align="right" style="margin-bottom: 15px;">
               <form method="post" action="/Kkuni/Enterprise/AdminList.do">
              	<input  type="text" size="20" name="search" class="body_search" placeholder="업체검색" />
               <button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
               </form>
   		</div>
         
         <c:if test="${count == 0}">
         <table class="table_size size tablelist">
            <tr>
            	<td align="center">
                	등록한 업체가 없습니다 ㅠ.ㅠ
                </td>
         	</tr>
         </table>
         </c:if>
         
         <c:if test="${count > 0 }">
         
         <table class="table_size size tablelist" style="text-align: center;">
         	<tr>
         	<th width="110">순번</th>
         	<th width="400">아이디</th>
         	<th width="400">업체명</th>
         	<th width="200">카테고리</th>
         	<th width="500">메일</th>
         	<th width="500">사업자번호</th>
         	<th width="1200">소재지</th>
         	<th width="200">인증</th>
         	</tr>
         	<c:forEach var="article" items="${articleList }">
         	<tr>
         		<td>
         		<c:out value="${number }"/>
         		<c:set var="number" value="${number-1 }"/>	
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		${article.eId }
         		</a>
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		${article.eName }
         		</a>
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		
         		<c:if test="${article.eType eq 'item' }">
         			물품
         		</c:if>
         		<c:if test="${article.eType eq 'space' }">
         			공간
         		</c:if>
         		</a>
         		
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		${article.eMail }
         		</a>
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		${article.registerNo }
         		</a>
         		</td>
         		<td>
         		<a href="/Kkuni/Enterprise/ConfirmForm.do?eId=${article.eId }&pageNum=${currentPage}" class="abc">
         		${article.eAddress }
         		</a>
         		</td>
         		<c:if test="${article.eConfirm eq 'wait' }">
         		<td>
         		<p style="color: #F15F5F;">인증대기</p>
         		</td>
         		</c:if>
         		<c:if test="${article.eConfirm eq 'confirm' }">
         		<td>
         		<p style="color: #4374D9;">인증완료</p>
         		</td>
         		</c:if>
         	</tr> 
         	</c:forEach>
         </table>
         </c:if>
         </div>
		<br>         
         <div align="center">
         <c:if test="${count > 0 }">
         <c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1 )}"/>
         <c:set var="pageBlock" value="${10 }"/> <!-- 페이징 넘버  -->
         <fmt:parseNumber var = "result" value ="${currentPage / 10}" integerOnly = "true" />
		 <c:set var = "startPage" value ="${result * 10 + 1}" />
		 <c:set var = "endPage" value ="${startPage + pageBlock-1}" /> <!-- 보여질 페이징 넘버 -->
		 <c:if test="${endPage > pageCount}">
			<c:set var = "endPage" value ="${pageCount}" />
		 </c:if>
		
		 
		<c:if test="${startPage > 10}">
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${startPage - 10}&search=${search}" class="abc">[이전]</a>
			</c:if>
			<c:if test="${empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${startPage - 10}" class="abc">[이전]</a>
			</c:if>
		 </c:if>
		
		 <c:forEach var="i" begin="${startPage}" end= "${endPage}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${i}" class="abc">[${i}]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${i}&search=${search}" class="abc">[${i}]</a>
			</c:if>
		 </c:forEach>
		
		 <c:if test="${endPage < pageCount}">
			<c:if test="${empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${startPage + 10}" class="abc">[다음]</a>
			</c:if>
			<c:if test="${!empty search }">
			<a href = "/Kkuni/Enterprise/AdminList.do?pageNum=${startPage + 10}&search=${search}" class="abc">[다음]</a>
			</c:if>
		 </c:if>
		</c:if>
		 </div>
   </div>
</body>
</html>