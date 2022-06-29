<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>1:1 문의</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/s_css/button.css">
<style type="text/css">


.total{
width:1410px;
align: center;
margin: auto;
padding: 0;
overflow: hidden;
}

.wrapper {
   width: 1200px;
   margin: auto;
   padding: 0;
   align: center;
   float: right;
}

table {
   border-collapse: collapse;
   vertical-align: middle;
   line-height: 150%;
}

.thsv {
   border-top: 2px solid #e4e4e4;
   border-bottom: 2px solid #e4e4e4;
   padding: 8px;
   text-align: center;
}  

.tdsv {
   border-top: 1.2px solid #e4e4e4;
   border-bottom: 1.2px solid #e4e4e4;
   padding: 8px;
}

.trsv {
   border-top: 1.2px solid #e4e4e4;
   border-bottom: 1.2px solid #e4e4e4;
   padding: 8px;
}

.lisv {
   list-style-type: none;
   text-align: right;
}

.ulsv {
   list-style-type: none;
   text-align: right;
}

body {
   font-family: 'Nanum Gothic', sans-serif;
}

#qlsearch {
   height:25px;
}


</style>
<script>
   function qlsubmit() {
      if(!document.submitform.mId.value) {
         alert("로그인해주시기 바랍니다.");
         return false;
      } 
   }
   
   function qCheck() {
         alert("로그인 후 이용해주세요.");
         return false;
      }
</script>


</head>
<body>
<tiles:insertDefinition name="header" />
<div class="total">
<tiles:insertDefinition name="navservice" />
   <div class="wrapper">
      <section>

         <div>
            <h3>1:1 문의</h3>
               
         </div>

         <hr width="1200" style="border: 0.8px solid #e4e4e4;">
         <div id="help">
            <div style="text-align: center;">
               <img src="<%=request.getContextPath()%>/images/help.png">
         </div>


                
            <form method="post" name="searchform" action="/Kkuni/Question/ServiceList.do" >
               <div align="right" style="margin-bottom: 15px;">
                   <input type="text" name="search" maxlength="50" class="body_search"/>
                    <input type="hidden" name="mId" value="${mId}">
                    <button class="btn btn_bodysearch"><i class="fa fa-search"></i></button>
                </div>
            </form>
         </div>
         <div margin="0">

               <div margin="0" align="center">
                  <c:if test="${count==0}">
                   <table width="1200" border="1" cellpadding="0" cellspacing="0" style="border-left: none; border-right: none;">
                       <tr class="trsv" >
                        <td class="tdsv"  align="center" style="border-left: none;    border-right: none;">
                           등록된 문의글이 없습니다.
                        </td>
                     </tr>
                  </table>
                  </c:if>
                  
                  <input type="hidden" name="mId" value="${mId}">
         
         <c:if test="${count > 0}">
            <table class="tablesv"  width="1200" text-align="center">
               <tr class="trsv"  height="30">
                  <th class="thsv"  width="50">순 번</th>
                  <th class="thsv"  width="50">분 류</th>
                  <th class="thsv"  width="250">제 목</th>
                  <th class="thsv"  width="100">작성자</th>
                  <th class="thsv"  width="100">작성일</th>
                  <th class="thsv"  width="50">상 태</th>
               </tr>

              <c:forEach var="article" items="${articleList}">
                 <tr class="trsv"  height="30">
                    <td class="tdsv"  align="center" width="50">
                       <c:out value="${number}"/>
                        <c:set var="number" value="${number-1}"/>
                        </td>
                        <c:if test="${article.qSort == 'item'}">
                        <td class="tdsv"  align="center" width="50">상품
                        </c:if>
                        <c:if test="${article.qSort == 'delivery'}">
                        <td class="tdsv"  align="center" width="50">배송
                        </c:if>
                        <c:if test="${article.qSort == 'pay'}">
                        <td class="tdsv"  align="center" width="50">결제
                        </c:if>
                        <c:if test="${article.qSort == 'member'}">
                        <td class="tdsv"  align="center" width="50">계정
                        </c:if>
                        </td>
                        <td class="tdsv"  width="250" align="center">
                        <a href="/Kkuni/Question/ListServiceDetail.do?questionNo=${article.questionNo}&pageNum=${currentPage}&mId=${mId}" style="text-decoration:none; color:black;">
                        ${article.qTitle}</a>
                        </td>
                        <td class="tdsv"  align="center" width="100">${article.mId}</td>
                        <td class="tdsv"  align="center" width="150">${article.qDate}</td>
                        <td class="tdsv"  align="center" width="50">
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
                <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
                <c:set var = "pageBlock" value ="${10}"/> <!-- 페이징 넘버 -->
                <fmt:parseNumber var = "result" value ="${currentPage / 10}" integerOnly = "true" />
                <c:set var = "startPage" value ="${result * 10 + 1}" />
                <c:set var = "endPage" value ="${startPage + pageBlock-1}" /> <!-- 보여질 페이징 넘버 -->
             <c:if test="${endPage > pageCount}">
                <c:set var = "endPage" value ="${pageCount}" />
            </c:if>
            </c:if>

         </div>
         
         <c:if test="${!empty mId }">
            <div style="width: 100%; margin-top: 10px;">
               <ul class="ulsv">
                  <li class="lisv">
                  <form method="post" name="submitform" action="/Kkuni/Question/InsertFormService.do" onsubmit="return qlsubmit()">
                  <input type="hidden" name="mId" value="${mId }">
                  <input type="submit" class="btn_b" id="qlsubmit" value="문의하기">
                  </form>
                  </li>
               </ul>
            </div>
            </c:if>
            
            <c:if test="${empty mId }">
            <div style="width: 100%; margin-top: 10px;">
               <ul class="ulsv">
                  <li class="lisv">
                  <input type="button" class="btn_b" id="qlsubmit" value="문의하기" onclick="qCheck()">
                  </li>
               </ul>
            </div>
            </c:if>
            
            <div style="text-align: center; text-decoration: none;">
               <c:if test="${count > 0 }">
                     <c:if test="${startPage > 10}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${startPage - 10}&mId=${mId}&search=${search}">[이전]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${startPage - 10}&mId=${mId}">[이전]</a>
                        </c:if>
                     </c:if>
                     <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${i}&mId=${mId}&search=${search}">[${i}]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${i}&mId=${mId}">[${i}]</a>
                        </c:if>
                     </c:forEach>
                     <c:if test="${endPage < pageCount}">
                        <c:if test="${!empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${startPage + 10}&mId=${mId}&search=${search}">[다음]</a>
                        </c:if>
                        <c:if test="${empty search}">
                           <a href="/Kkuni/Question/ServiceList.do?pageNum=${startPage + 10}&mId=${mId}">[다음]</a>
                        </c:if>
                     </c:if>
                  </c:if>
            </div>
         </div>
      </section>
   </div>
   </div>

<tiles:insertDefinition name="footer" />
</body>
</html>