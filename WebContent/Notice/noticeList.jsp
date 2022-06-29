<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html >
<html>
<head>
<title>공지사항</title>
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/s_css/button.css">
<style>
.total {
   width: 1410px;
   align: center;
   margin: auto;
   padding: 0;
   overflow: hidden;
}

.wrapper {
   margin: auto;
   padding: 0;
   align: center;
   float: right;
   width: 1200px;
}

table {
   border-collapse: collapse;
   vertical-align: middle;
   line-height: 230%;
}

.thnc {
   border-top: 2px solid #e4e4e4;
   border-bottom: 2px solid #e4e4e4;
   padding: 8px;
   text-align: center;
}

.tdcltrnc {
   border-top: 1.2px solid #e4e4e4;
   border-bottom: 1.2px solid #e4e4e4;
   padding: 8px;
}

.trnc {
   border-top: 1.2px solid #e4e4e4;
   border-bottom: 1.2px solid #e4e4e4;
   padding: 8px;
}

.linc {
   list-style-type: none;
   text-align: right;
}

.ulnc {
   list-style-type: none;
   text-align: right;
}

body {
   font-family: 'Nanum Gothic', sans-serif;
}

</style>
</head>
<body>
   <tiles:insertDefinition name="header" />
   <div class="total">
      <tiles:insertDefinition name="navservice" />

      <div class="wrapper">
         <section style="margin-left: 10px;">
            <div>
               <h3>공지사항</h3>

            </div>
            <hr width="1200" style="border: 0.8px solid #e4e4e4;">

            <div id="tablent">
               <form method="post" action="/Kkuni/Notice/Search.do">
                  <div align="right" style="margin-bottom: 15px;">
             		<input type="text" name="search" maxlength="50" class="body_search"/>
                    <button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
                </div>
               </form>
               
               
            </div>

            <div margin="0">

               <div margin="0" align="center">
                  <c:if test="${count==0}">
                     <table width="1200" border="1" cellpadding="0" cellspacing="0"
                        style="border-left: none;">
                        <tr class="trnc">
                           <td class="tdcl" align="center">게시판에 저장된 글이 없습니다.</td>
                        </tr>
                     </table>
                  </c:if>


                  <c:if test="${count > 0}">
                     <table width="1200" text-align="center">
                        <tr class="trnc" height="30">
                           <th class="thnc" class="thnc" width="50">순 번</th>
                           <th class="thnc" width="250">제 목</th>
                           <th class="thnc" width="100">작성자</th>
                           <th class="thnc" width="150">작성일</th>
                           <th class="thnc" width="50">조회수</th>
                        </tr>

                        <c:forEach var="article" items="${articleList}">
                           <tr class="trnc" height="30">
                              <td class="tdcl" align="center" width="50"><c:out
                                    value="${number}" /> <c:set var="number"
                                    value="${number-1}" /></td>
                              <td class="tdcl" align="center" width="250"><a
                                 href="/Kkuni/Notice/NoticeDetail.do?noticeNo=${article.noticeNo}&pageNum=${currentPage}"
                                 style="text-decoration: none; color: black;">
                                    ${article.nTitle}</a></td>
                              <td class="tdcl" align="center" width="100">관리자</td>
                              <td class="tdcl" align="center" width="150">${article.nDate}
                              </td>
                              <td class="tdcl" align="center" width="50">${article.viewCount}
                              </td>

                           </tr>
                        </c:forEach>
                     </table>


                     <div align="center"
                        style="margin-top: 35px; margin-bottom: 20px;">
                        <c:set var="pageCount"
                           value="${count/pageSize+(count%pageSize==0 ? 0:1)}" />
                        <c:set var="pageBlock" value="${10}" />
                        <fmt:parseNumber var="result" value="${currentPage / 10}"
                           integerOnly="true" />
                        <c:set var="startPage" value="${result *10 +1}" />
                        <c:set var="endPage" value="${startPage + pageBlock-1}" />
                        <c:if test="${endPage > pageCount}">
                           <c:set var="endPage" value="${pageCount}" />
                        </c:if>
                        <c:if test="${search == null}">
                           <c:if test="${startPage>10}">
                              <a href="/Kkuni/Notice/NoticeList.do?pageNum=${startPage-10}"
                                 style="color: black;">[이전]</a>
                           </c:if>
                        </c:if>
                        <c:if test="${search != null}">
                           <c:if test="${startPage>10}">
                              <a
                                 href="/Kkuni/Notice/Search.do?${search}&pageNum=${startPage-10}"
                                 style="color: black;">[이전]</a>
                           </c:if>
                        </c:if>
                        <c:if test="${search == null}">
                           <c:forEach var="i" begin="${startPage}" end="${endPage}">
                              <a href="/Kkuni/Notice/NoticeList.do?pageNum=${i}"
                                 style="color: black;">[${i}]</a>
                           </c:forEach>
                        </c:if>
                        <c:if test="${search != null}">
                           <c:forEach var="i" begin="${startPage}" end="${endPage}">
                              <a
                                 href="/Kkuni/Notice/Search.do?search=${search}&pageNum=${i}"
                                 style="color: black;">[${i}]</a>
                           </c:forEach>
                        </c:if>
                        <c:if test="${search == null}">
                           <c:if test="${endpage<pageCount}">
                              <a href="Kkuni/Notice/NoticeList.do?pageNum=${startPage+10}"
                                 style="color: black;">[다음]</a>
                           </c:if>
                        </c:if>
                        <c:if test="${search != null}">
                           <c:if test="${endpage<pageCount}">
                              <a
                                 href="Kkuni/Notice/Search.do?${search}&pageNum=${startPage+10}"
                                 style="color: black;">[다음]</a>
                           </c:if>
                        </c:if>
                     </div>
                  </c:if>

               </div>


            </div>
      </div>
   </div>
   <tiles:insertDefinition name="footer" />
</body>
</html>