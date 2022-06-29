<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>1:1 문의글 상세보기</title>
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="../editor/demo/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script>
 
   function insertSave() {
	      if (document.insertform.aContents.value == "") {
	         alert("내용을 입력하십시오.");
	         document.insertform.aContents.focus();
	         return false;
	      }
	      
	   }
   function updateSave() {
	   if (document.updateform.aContents.value == "") {
		   alert("내용을 입력하십시오.");
		   document.updateform.aContents.focus();
		   return false;
	   }
   }
   
   function delete_check() {
		if(confirm("정말 삭제하시겠습니까?") == true){
			return true;
		}
		else {
			return false;
		}
	}
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
</head>
<body>
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />
<div style="background-color: white; width: 1705px;
			min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
	<br>
		<div align="left">
			<h2>1:1 문의</h2>
			<hr>
		</div>
         <table width="1500px" align="center" style="border: 1px solid #E4E4E4;">
            <tr>
               <td colspan="2" align="center" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">분류</td>
               <td colspan="5" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">
               <c:if test="${article.qSort == 'item'}">상품</c:if>                        
               <c:if test="${article.qSort == 'delivery'}">배송</c:if>
               <c:if test="${article.qSort == 'pay'}">결제</c:if>
               <c:if test="${article.qSort == 'member'}">계정</c:if>
               </td>
               <td colspan="2" align="center" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">작성자</td>
               <td colspan="3" align="center" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">
               <c:if test="${empty article.mId}">${article.eId}</c:if>
               <c:if test="${empty article.eId}">${article.mId}</c:if>
               </td>
            </tr>
            <tr>
               <td colspan="2" align="center" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">제목</td>
               <td colspan="10" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">
               ${article.qTitle}
               </td>
            </tr>
            <tr>
               <td colspan="2" align="center" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">내용</td>
               <td colspan="10" style="border-right: 1px solid #E4E4E4; border-bottom: 1px solid #E4E4E4;">
                  <div rows="10" cols="30" style="width: 100%; height: 350px;">
                  ${article.qContents}
                  </div>
               </td>
            </tr>
            <tr>
               <td align="right" colspan="12">
                  <input type="button" value="목록" style="height: 25px; width: 50px;" onclick="document.location.href='/Kkuni/Answer/AdminList.do?pageNum=${pageNum}'">                  
               </td>
            </tr>
         </table>
         <br>
         <div width="1500px">
         <table width="1500px" align="center">
         <tr>
         	<td>답변</td>
         </tr>
         </table>
         <c:if test="${answerCount == 0 }">
         <form method="post" name="insertform" action="/Kkuni/Answer/AdminInsertPro.do" onsubmit="return insertSave()">
         <table style="border: 1px solid #E4E4E4; width: 1500px;" align="center">
	         	<tr>
	         		<td style="text-align: center; width: 120px; border-right: 1px solid #E4E4E4;">
	         		 	<input type="hidden" name="questionNo" value="${questionNo}">
	        			<input type="hidden" name="pageNum" value="${pageNum}">
	      				  관리자
	         		</td>
	         		<td>
	         			<textarea cols=187 name="aContents" rows=5 style="resize:none; height: 100px; border: 0px;" placeholder="내용을 입력해주세요"></textarea></td>
	         		<td style="width: 100px;">
	         			<input type="submit" class="btn_p" id="save" value="등록" style="width:100px; height: 100px; margin: auto;">
	         		</td>
	         	</tr>
         </table>
         </form>
         </c:if>
         <c:if test="${answerCount > 0 }">
         
         <table style="border: 1px solid #E4E4E4; width: 1500px;" align="center">
         	<input type="hidden" name="questionNo" value="${questionNo}">
         	<input type="hidden" name="pageNum" value="${pageNum}">
         	<tr>
         		<form method="post" name="updateform" action="/Kkuni/Answer/AdminUpdatePro.do" onsubmit="return updateSave()">
         		<td style="border-right: 1px solid #E4E4E4; text-align: center;">관리자<br>${articleAnswer.aDate}</td>
         		<td>
         			<input type="hidden" name="questionNo" value="${questionNo}">
	         		<input type="hidden" name="pageNum" value="${pageNum}">
         			<textarea cols=187 name="aContents" rows=5 style="resize:none; border: 0px;">${articleAnswer.aContents}</textarea></td>
         			<td>
         			<input type="submit" id="save" value="수정" class="btn_p" style="height: 30px; width: 100px;">
				</form>
				<form method ="post" name="delete" action="/Kkuni/Answer/AdminDelete.do?questionNo=${article.questionNo}&pageNum=${pageNum}" onsubmit="return delete_check()">
	         			<input type="submit" value="삭제" class="btn_b" style="height: 30px; width: 100px; margin-bottom: 10px;">
	         			<input type="hidden" name="questionNo" value="${questionNo}">
	         			<input type="hidden" name="pageNum" value="${pageNum}">
	         	</form>
         			</td>
         		
         	</tr>
         </table>
         </c:if>
         </div>
   </div>
</body>
</html>
