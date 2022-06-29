<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<style>
  table {
    width: 1500px;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #e4e4e4;
    padding: 8px;
  }
  #submit{
  	width:100px;
  	height:25px;
  	margin:0;
  }
   #search{
  	width:80px;
  	height:20px;
  	margin:0;
  }
</style>
<title>공지사항</title>
<link href="<%=request.getContextPath()%>/css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="enterprise_header" />
<tiles:insertDefinition name="enterprise_menu" />
   <div style="background-color: white; width: 1680x; min-height: 900px; margin: 10px; margin: 80px 0px 0px 200px;">
      
   
	<div style="width:1000px; position: absolute; left: 300px; " >
			<table >
				<tr>
					<td colspan="8"><h2>공지사항</h2>
				</tr>
			</table>
			<table>
				<tr>
					<td align="right">
					<form method="post" action="/Kkuni/Notice/EnterpriseSearch.do?eId=${eId}">
					<input  type="text" size="20" name="search" />
					<input class="btn_p" id="search" type="submit" value="검색" />
					</form>
					</td>
				</tr>
			</table>
			<c:if test="${count==0}">
			<table>
				<tr>
					<td align="center">
					게시판에 저장된 글이 없습니다.
					</td>
				</tr>
				</table>
			</c:if>
			<c:if test="${count >0}">
						<table>
							<tr height="50">
								<td align="center" width="50">번 호</td>
								<td align="center" width="250">제 목</td>
								<td align="center" width="100">작성자</td>
								<td align="center" width="150">작성일</td>
								<td align="center" width="50">조 회</td>
							</tr>
						<c:forEach var="article" items="${articleList}">
					   	<tr>
					   		<td align="center"  width="50" >
					   		<c:out value="${number}"/>
					   		<c:set var="number" value="${number-1}"/>
					   		</td>
					   		<td width="250">
					   		<a href="/Kkuni/Notice/EnterpriseDetail.do?noticeNo=${article.noticeNo}&pageNum=${currentPage}&eId=${eId}" style="text-decoration:none; color: black;">
					   			${article.nTitle}</a>
					   		</td>
					   		<td align="center"  width="100">
					       	관리자
							</td>
					    	<td align="center"  width="150">${article.nDate}
							</td>
					    	<td align="center"  width="50">${article.viewCount}</td>
					    	
					  </tr>
					  </c:forEach>
					  </table>
					  <c:choose>
					    <c:when test="${empty search}">
						<table>
						  	<tr>
						  		<td align="center" style="border: 0px" >
								<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
								<c:set var="pageBlock" value="${10}"/>
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result *10 +1}"/>
								<c:set var="endPage" value="${startPage + pageBlock-1}"/>
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}"/>
								</c:if>
								<c:if test="${startPage>10}">
									<a href="/Kkuni/Notice/NoticeEnterpriseList.do?pageNum=${startPage-10}&eId=${eId}" style="color: black;">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Kkuni/Notice/NoticeEnterpriseList.do?pageNum=${i}&eId=${eId}"style="color: black;">[${i}]</a>
								</c:forEach>
								<c:if test="${endpage<pageCount}">
									<a href="Kkuni/Notice/NoticeEnterpriseList.do?pageNum=${startPage+10}&eId=${eId}" style="color: black;">[다음]</a>
								</c:if>
							</td>
						</tr>
						</table>	    
						</c:when>
					
					    <c:otherwise>
					      	<table>
						  	<tr>
						  		<td align="center" style="border: 0px" >
								<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
								<c:set var="pageBlock" value="${10}"/>
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result *10 +1}"/>
								<c:set var="endPage" value="${startPage + pageBlock-1}"/>
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}"/>
								</c:if>
								<c:if test="${startPage>10}">
									<a href="/Kkuni/Notice/EnterpriseSearch.do?pageNum=${startPage-10}&search=${search}&eId=${eId}" style="color: black;">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Kkuni/Notice/EnterpriseSearch.do?pageNum=${i}&search=${search}&eId=${eId}"style="color: black;">[${i}]</a>
								</c:forEach>
								<c:if test="${endpage<pageCount}">
									<a href="/Kkuni/Notice/EnterpriseSearch.do?pageNum=${startPage+10}&search=${search}&eId=${eId}" style="color: black;">[다음]</a>
								</c:if>
							</td>
						</tr>
						</table>
					    </c:otherwise>
					</c:choose>
						</c:if>
						
					</div>
	</div>

</body>
</html>