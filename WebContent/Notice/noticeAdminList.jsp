<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>

<style>
  #submit{
  	width:100px;
  	height:25px;
  	margin:0;
  }
</style>
<title>공지사항</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<%=request.getContextPath()%>/css/admin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/s_css/button.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/s_css/list.css">
</head>
<body style="background-color: #dbdbdb;">
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />
   <div style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
   
		<div class="pageSize size">
			<br>
			<div align="left">
				<h2>공지사항</h2>
				<hr>
			</div>
			<div align="right" style="margin-bottom: 15px;">
				<form method="post" action="/Kkuni/Notice/AdminSearch.do" >
					<input type="text" size="20" name="search" class="body_search"/>
					<button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
				</form>
			</div>
			
			<c:if test="${count==0}">
			<table class="table_size size tablelist">
				<tr>
					<td align="center">
					게시판에 저장된 글이 없습니다.
					</td>
				</tr>
				</table>
			</c:if>
			<c:if test="${count >0}">
						<table class="table_size size tablelist">
							<tr>
								<th align="center" width="50">번 호</th>
								<th align="center" width="250">제 목</th>
								<th align="center" width="100">작성자</th>
								<th align="center" width="150">작성일</th>
								<th align="center" width="50">조 회</th>
								<th align="center" width="50">삭 제</th>
							</tr>
						<c:forEach var="article" items="${articleList}">
					   	<tr>
					   		<td align="center"  width="50" >
					   		<c:out value="${number}"/>
					   		<c:set var="number" value="${number-1}"/>
					   		</td>
					   		<td width="250">
					   		<a href="/Kkuni/Notice/AdminDetail.do?noticeNo=${article.noticeNo}&pageNum=${currentPage}" style="text-decoration:none; color: black;">
					   			${article.nTitle}</a>
					   		</td>
					   		<td align="center"  width="100">
					       	관리자
							</td>
					    	<td align="center"  width="150">${article.nDate}
							</td>
					    	<td align="center"  width="50">${article.viewCount}</td>
					    	<td align="center">
					    	<input class="btn_b" type="button" id="submit" value="삭제" onclick="window.location='/Kkuni/Notice/Delete.do?noticeNo=${article.noticeNo}'">
					  </tr>
					  </c:forEach>
					  </table>
					  <br>
					  <c:choose>
					    <c:when test="${empty search}">
					     <div align="center" >
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
									<a href="/Kkuni/Notice/NoticeAdminList.do?pageNum=${startPage-10}" style="color: black;">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Kkuni/Notice/NoticeAdminList.do?pageNum=${i}"style="color: black;">[${i}]</a>
								</c:forEach>
								<c:if test="${endpage<pageCount}">
									<a href="Kkuni/Notice/NoticeAdminList.do?pageNum=${startPage+10}" style="color: black;">[다음]</a>
								</c:if>
							</td>
						</tr>
						</table>
						</div>	    
						</c:when>
					
					    <c:otherwise>
					    <div align="center" >
					      	<table>
						  	<tr>
						  		<td style="border: 0px" >
								<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
								<c:set var="pageBlock" value="${10}"/>
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result *10 +1}"/>
								<c:set var="endPage" value="${startPage + pageBlock-1}"/>
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}"/>
								</c:if>
								<c:if test="${startPage>10}">
									<a href="/Kkuni/Notice/AdminSearch.do?pageNum=${startPage-10}&search=${search}" style="color: black;">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Kkuni/Notice/AdminSearch.do?pageNum=${i}&search=${search}"style="color: black;">[${i}]</a>
								</c:forEach>
								<c:if test="${endpage<pageCount}">
									<a href="/Kkuni/Notice/AdminSearch.do?pageNum=${startPage+10}&search=${search}" style="color: black;">[다음]</a>
								</c:if>
								</td>
							</tr>
							</table>
						</div>
					    </c:otherwise>
					</c:choose>
					  </c:if>
						<div align="right">
							<input class="btn_p"  type="button" value="등록" onclick="window.location='/Kkuni/Notice/InsertForm.do'">
						</div>
					</div>
	</div>

</body>
</html>