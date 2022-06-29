<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title></title>
<script>

   function reviewDetail(reviewNo) {
    
       url = "/Kkuni/Review/Detail.do?reviewNo="+reviewNo;
    
      window.open(url, "post", "toolbar=no, left=430%, top=200%, width=1070, height=650, directories=no, status=yes, scrollbars=no, menubar=no");
    }
   
   function insertFormUp(itemNo) {
        
      url = "/Kkuni/Review/InsertForm.do?itemNo="+itemNo;
      
      window.open(url, "post", "width=1070, height=650,left=430%, top=200%, directories=no, status=yes, scrollbars=no, menubar=no")
      
   }
   
   

</script>

<style>
/* 목록의 글머리에 아무것도 표시하지 않음 */
ul {
   list-style-type: none;
   /* 전체 목록과 텍스트 가운데 정렬*/
}

#item {
   text-align: center;

}

ul {
   display: table;
   margin: auto;
   padding: 0;
}

#id_nav {
   float: left;
   margin: 20px 0px 0px 20px;
}

#main_lnb>ul {
   overflow: hidden;
}

#main_lnb>ul>li {
   float: left;
}

#main_lnb>ul>li>a {
   display: block;
   padding: 10px 20px;
   border: 1px solid #E4E4E4;
   text-decoration: none;
   color: #3E4149;
}

input[type=button], input[type=submit]{
	width: 100px;
	height: 40px;  
	border-radius: 5px;
	border: 0px;
	margin-top: 10px;
}

.btn_p {
	background-color: #DF7785;
	color: white;
}

.btn_b {
	background-color: #444F59;
	color: white;
}

</style>
<!-- 사이드 -->
<style>
/* 첫 번째 탭 */
#first:nth-of-type(1) {
   display: none;
}

#first:nth-of-type(1) ~ div:nth-of-type(1) {
   display: none;
}

#first:nth-of-type(1):checked ~ div:nth-of-type(1) {
   display: block;
}

/* 두 번째 탭 */
#second:nth-of-type(2) {
   display: none;
}

#second:nth-of-type(2) ~ div:nth-of-type(2) {
   display: none;
}

#second:nth-of-type(2):checked ~ div:nth-of-type(2) {
   display: block;
}

/* 탭 모양 구성 */
section.buttons {
   overflow: hidden;
}

section.buttons>label {
   /* 수평 정렬 */
   display: block;
   float: left;
   /* 크기 및 글자 위치 지정*/
   width: 100px;
   height: 30px;
   border-radius: 5px 5px 5px 5px;
   line-height: 30px;
   text-align: center;
   /* 테두리 지정 */
   box-sizing: border-box;
   /* 색상 지정*/
   background: #E4E4E4;
   color: black;
}

input:nth-of-type(1):checked ~ section.buttons>label:nth-of-type(1) {
   background: #FD999A;
   color: white;
}

input:nth-of-type(2):checked ~ section.buttons>label:nth-of-type(2) {
   background: #FD999A;
   color: white;
}
</style>
<!-- 목록 -->
<style>
.item {
   overflow: hidden;
   padding: 10px;
   border: 2px solid #E4E4E4;
   text-align: center;
   width: 1000px;
}

.itemd2 {
   overflow: auto;
   max-height: 500px;
   padding: 10px;
   border: 2px solid #E4E4E4;
   text-align: center;
   width: 1000px;
}

.detailimg {
   text-align: center;
   width: 100%;
}

#main_aside {
   width: 1000px;
}

/* #tdrv {
   border: 1px solid #E4E4E4;
} */
.detailrv {
   width: 980px;
}

#detail1 {
   width: 95px;
   height: 95px;
   float: left;
   margin: 5px;
   margin-left: 20px;
}

#rvtb {
   margin: 5px;
   width: 750px;
   height: 85px;
   float: left;
   margin-left: 20px;
}

#cont {
   height: 8 0px;
}
</style>


</head>
<body>


   <aside id="main_aside">
      <input id="first" type="radio" name="tab" checked="checked" /> <input
         id="second" type="radio" name="tab" />
      <section class="buttons">
         <label for="first">상세설명</label> <label for="second">후기</label>
      </section>
      <div class="tab_item">
         <ul>
            <li class="item"><a href="#">
                  <div class="detailimg">
                     <img src="${pageContext.request.contextPath}${itemList.detail1}"><br>
                     <c:if test="${empty itemList.detail2 }">
                        <br>
                     </c:if>

                     <c:if test="${!empty itemList.detail2 }">
                        <img src="${pageContext.request.contextPath}${itemList.detail2}">
                        <br>
                     </c:if>
                     <c:if test="${!empty itemList.detail3 }">
                        <img src="${pageContext.request.contextPath}${itemList.detail3}">
                     </c:if>
                  </div>
            </a></li>

         </ul>

      </div>

      <div class="tab_item">
         <ul>
            <li class="itemd2">
               <div class="detailimg">
                  <c:if test="${rCount == 0 }">
                  작성된 후기가 없습니다.
                  </c:if>
                  <c:if test="${rCount != 0 }">
                  후기가 총 ${rCount }개 작성되었습니다.
                  <c:forEach var="review" items="${reviewList }">
                        <form name="reviewform">
                           <div class="detailrv">

                              <div id="detail1">
                                 <img src="${pageContext.request.contextPath}${review.rFile}"
                                    width=90px; height=90px;>
                              </div>


                              <table id="rvtb">
                                 <tr>
                                    <td
                                       style="text-align: left; font-weight: bold; color: #4C4C4C; font-size: 17px;">
                                       ${itemList.itemId }<input type="hidden" name="itemId"
                                       value="${itemList.itemId }">

                                    </td>
                                 </tr>

                                 <tr>
                                    <td style="text-align: left; font-size: 16px; margin: 0;">
                                       <a href="javascript:reviewDetail(${review.reviewNo });"
                                       style="text-decoration: none; color: #4C4C4C; margin: 0;">
                                          <c:if test="${empty review.rTitle }">   
                     ${review.rContents }
                     </c:if> <c:if test="${!empty review.rTitle }">
                     ${review.rTitle }
                     </c:if>
                                    </a>
                                    </td>
                                 </tr>
                                 <tr>
                                    <td
                                       style="text-align: left; font-size: 14px; color: #747474;">${review.mId }
                                       <input type="hidden" name="reviewNo"
                                       value="${review.reviewNo }"> | ${review.rDate }
                                    </td>
                                 </tr>
                              </table>
                              <hr style="width: 98%; border: 1px solid #E7E7E7;">
                           </div>
                        </form>
                     </c:forEach>
                  </c:if>
               </div>

            </li>
         </ul>
         <div width=1024 align=right>
            <c:if test="${!empty mId }">
               <form method="post"
                  onsubmit="return insertFormUp(${itemList.itemNo })">
                  <input type="submit" class="btn_p" value="후기등록">
               </form>
            </c:if>
         </div>
      </div>
   </aside>



</body>
</html>