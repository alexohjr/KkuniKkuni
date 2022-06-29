<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>1:1 문의</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">
<style type="text/css">
table {
	border-collapse: collapse;
	vertical-align: middle;
	line-height: 230%;
}

.thque {
	border-top: 2px solid #e4e4e4;
	border-bottom: 2px solid #e4e4e4;
	padding: 8px;
	text-align: center;
}

.tdque {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
}

.trque {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
}

.lique {
	list-style-type: none;
	text-align: right;
}

.ulque {
	list-style-type: none;
	text-align: right;
}

body {
	font-family: 'Nanum Gothic', sans-serif;
}

#qlsearch {
	height: 25px;
}
</style>
</head>
<body style="background-color: #dbdbdb;">
	<tiles:insertDefinition name="enterprise_header" />
	<tiles:insertDefinition name="enterprise_menu" />
	<div style="background-color: white; width: 1680x; min-height: 900px; margin: 10px; margin: 80px 0px 0px 200px;">
		<div class="pageSize size">

			<br>
		<div align="left">
			<h2>1:1문의</h2>
			<hr>
		</div>  
			<div id="help">
				<div style="text-align: center; width: 1400px;">
					<img src="<%=request.getContextPath()%>/images/help.png">
				</div>

				<div align="right" style="margin-bottom: 15px;">
				<form method="post" action="/Kkuni/Question/EnterpriseList.do" style="width: 1400px;">
					<ul class="ulque" class="ulque">
						<li class="lique" class="lique"><input type="text" name="search" size="15"  class="body_search" maxlength="50" />
							<button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
							 <input type="hidden" name="eId" value="${eId}"></li>
					</ul>
				</form>
				</div>
			</div>
			<div margin="0">
				<div margin="0" align="center">
					<c:if test="${count==0}">
						<table width="1400" border="1" cellpadding="0" cellspacing="0" style="border-left: none; border-right: none;">
							<tr class="trque">
								<td class="tdque" align="center" style="border-left: none; border-right: none;">게시판에 저장된 글이 없습니다.</td>
							</tr>
						</table>
					</c:if>

					<input type="hidden" name="eId" value="${eId}">

					<c:if test="${count > 0}">
						<table width="1400" text-align="center">
							<tr class="trque" height="30">
								<th class="thque" class="thque" width="50">순 번</th>
								<th class="thque" width="50">분 류</th>
								<th class="thque" width="250">제 목</th>
								<th class="thque" width="100">작성자</th>
								<th class="thque" width="100">작성일</th>
								<th class="thque" width="50">상 태</th>
							</tr>

							<c:forEach var="article" items="${articleList}">
								<tr class="trque" height="30">
									<td class="tdque" align="center" width="50">
									<c:out value="${number}" /> 
									<c:set var="number" value="${number-1}" />
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

									<td class="tdque" width="250" align="center">
									<a href="/Kkuni/Question/EnterpriseDetail.do?questionNo=${article.questionNo}&pageNum=${currentPage}&eId=${eId}" style="text-decoration: none; color: black;">
									${article.qTitle}</a></td>
									<td class="tdque" align="center" width="100">
									${article.eId}
									</td>
									<td class="tdque" align="center" width="150">
									${article.qDate}
									</td>
									<td class="tdque" align="center" width="50">
									<c:if test="${article.qState ==  'F' }">
										<font color="#F15F5F">미답변</font>
			                        </c:if>
			                        <c:if test="${article.qState == 'T' }">
			                             <font color="#4374D9">답변완료</font> 
			                        </c:if>
			                     	</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<c:if test="${count > 0}">
						<c:set var="pageCount"
							value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
						<c:set var="pageBlock" value="${10}" />
						<!-- 페이징 넘버 -->
						<fmt:parseNumber var="result" value="${currentPage / 10}"
							integerOnly="true" />
						<c:set var="startPage" value="${result * 10 + 1}" />
						<c:set var="endPage" value="${startPage + pageBlock-1}" />
						<!-- 보여질 페이징 넘버 -->
						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" />
						</c:if>
					</c:if>

				</div>

				<div style="width: 1400px; margin-top: 10px;">
					<ul class="ulque">
						<li class="lique">
						<input type="button" class="btn_b" id="qlsubmit" value="문의하기" onclick="window.location='/Kkuni/Question/EnterpriseInsertForm.do?eId=${eId}'">
						</li>
					</ul>
				</div>
				<div style="text-align: center; text-decoration: none;">
					<c:if test="${count > 0 }">
                     <c:if test="${startPage > 10}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${startPage - 10}&eId=${eId}&search=${search}">[이전]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${startPage - 10}&eId=${eId}">[이전]</a>
                        </c:if>
                     </c:if>
                     <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${i}&eId=${eId}&search=${search}" style="color:black">[${i}]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${i}&eId=${eId}" style="color:black">[${i}]</a>
                        </c:if>
                     </c:forEach>
                     <c:if test="${endPage < pageCount}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${startPage + 10}&eId=${eId}&search=${search}">[다음]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/EnterpriseList.do?pageNum=${startPage + 10}&eId=${eId}">[다음]</a>
                        </c:if>
                     </c:if>
                  </c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>