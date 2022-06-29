<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.util.List" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
<title>1:1 문의</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function delete_check() {
		if(confirm("정말 삭제하시겠습니까?") == true){
			return true;
		}
		else {
			return false;
		}
	} 
</script>

<style type="text/css">
.size{
		width: 1500px;
	}
	  
  #submit{
  	width:100px;
  	height:25px;
  	margin:0;
  }

  
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />

<div style="background-color: white; width: 1705px;
         min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px; overflow: auto;">

      <!--헤더시작-->
      <div class="pageSize size">
            <br>
			<div align="left">
				<h2>1:1문의글</h2>
				<hr>
			</div>
            
            <div align="right" style="margin-bottom: 15px;">
	            <form method="post" align= "right" action="/Kkuni/Answer/AdminList.do"> <!-- action을 적지 않으면 현재 페이지의 url을 요청한다. -->
					<input type="text" class="body_search" name="search" size="15" maxlength="50">
					<button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
				</form> 
			</div>
            <article>
               <div align="center">
                  <c:if test="${count==0}">
                  <table class="table_size size tablelist">
                     <tr>
                        <td align="center">
                        	게시판에 저장된 글이 없습니다.
                        </td>
                     </tr>
                  </table>
                  </c:if>
             
                  <c:if test="${count > 0}">
                  <table class="table_size size tablelist">
                     <tr height="30">
                        <th align="center" width="50">번 호</th>
                        <th align="center" width="50">분 류</th>
                        <th align="center" width="250">제 목</th>
                        <th align="center" width="100">작성자</th>
                        <th align="center" width="150">작성일</th>
                        <th align="center" width="50">상 태</th>
                        <th align="center" width="50">삭 제</th>
                     </tr>
                  <c:forEach var="article" items="${articleList}">
                     <tr height="30">
                        <td align="center" width="50">
                        <c:out value="${number}"/>
                        <c:set var="number" value="${number-1}"/>
                        </td>
                        <c:if test="${article.qSort == 'item'}">
                        <td align="center" width="50">상품
                        </c:if>
                        <c:if test="${article.qSort == 'delivery'}">
                        <td align="center" width="50">배송
                        </c:if>
                        <c:if test="${article.qSort == 'pay'}">
                        <td align="center" width="50">결제
                        </c:if>
                        <c:if test="${article.qSort == 'member'}">
                        <td align="center" width="50">계정
                        </c:if>
                        </td>
                        <td width="250" align="center">
                        <a href="/Kkuni/Answer/AdminDetail.do?questionNo=${article.questionNo}&pageNum=${currentPage}" style="text-decoration:none; color:black;">
                        ${article.qTitle}</a>
                        </td>
                        <td align="center" width="100">
                        <c:choose>
						<c:when test="${empty article.eId }">
						${article.mId }
						</c:when>
						<c:otherwise>
						${article.eId }
						</c:otherwise>
						</c:choose>
                     	</td>
                      	<td align="center" width="150">${article.qDate}
                     	</td>
                     	<td align="center" width="50">
                     	<c:if test="${article.qState ==  'F' }">
                     		<font style="color: #F15F5F">미답변</font>
                     	</c:if>
                     	<c:if test="${article.qState == 'T' }">
                     		<font style="color: #4374D9;">답변완료</font>
                     	</c:if>
                     	</td>
                     	<form method="post" name="questionNo" action="/Kkuni/Answer/AdminArticleDelete.do?questionNo=${article.questionNo}" onsubmit="return delete_check();">
	                     	<td style="width: 50px;" align="center">
	                     		<input type="submit" value="삭제" id="submit" class="btn_b">
	                     	</td>
                     	</form>
                 	</tr>
                 </c:forEach>
                 </table>
                 </c:if>
                 <br>
                 <c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
		<c:set var = "pageBlock" value ="${10}"/> <!-- 페이징 넘버 -->
		<fmt:parseNumber var = "result" value ="${currentPage / 10}" integerOnly = "true" />
		<c:set var = "startPage" value ="${result * 10 + 1}" />
		<c:set var = "endPage" value ="${startPage + pageBlock-1}" /> <!-- 보여질 페이징 넘버 -->
		<c:if test="${endPage > pageCount}">
			<c:set var = "endPage" value ="${pageCount}" />
		</c:if>
		</c:if>
		
		<c:if test="${count > 0 }">
		<c:if test="${startPage > 10}">
			<c:if test="${!empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${startPage - 10}&search=${search}" style="color:black">[이전]</a>
			</c:if>
			<c:if test="${empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${startPage - 10}" style="color:black">[이전]</a>
			</c:if>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end= "${endPage}">
			<c:if test="${!empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${i}&search=${search}" style="color:black">[${i}]</a>
			</c:if>
			<c:if test="${empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${i}" style="color:black">[${i}]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<c:if test="${!empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${startPage + 10}&search=${search}" style="color:black">[다음]</a>
			</c:if>
 			<c:if test="${empty search}">
			<a href = "/Kkuni/Answer/AdminList.do?pageNum=${startPage + 10}" style="color:black">[다음]</a>
			</c:if>
		</c:if>
		</c:if>
               </div>
            </article>
      	</div>
      <!--풋터-->
   </div>

</body>
</html>