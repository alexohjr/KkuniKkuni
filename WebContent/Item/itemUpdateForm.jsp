<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String langType = request.getParameter("siteLanguage");
%>
<html>
<head>
<title>상품수정</title>
<link rel="stylesheet" href="../css/button.css">
<script>
   function zipCheck() {
      url = "/Kkuni/Item/ZipCheck.do";
      window
            .open(
                  url,
                  "post",
                  "toolbar=no, left=770%, top=400%, width=500, height=300 directories=no, status=yes, scrollbars=yes, menubar=no");
   }

   var InputImage = (function loadImageFile() {
      if (window.FileReader) {
         var ImagePre;
         var ImgReader = new window.FileReader();
         var fileType = /^(?:image\/bmp|image\/gif|image\/jpeg|image\/png|image\/x\-xwindowdump|image\/x\-portable\-bitmap)$/i;

         ImgReader.onload = function(Event) {
            if (!ImagePre) {
               var newPreview = document.getElementById("imagePreview");
               ImagePre = new Image();
               ImagePre.style.width = "150px";
               ImagePre.style.height = "150px";
               newPreview.appendChild(ImagePre);
            }
            ImagePre.src = Event.target.result;

         };

         return function() {

            var img = document.getElementById("image").files;

            if (!fileType.test(img[0].type)) {
               alert("이미지 파일을 업로드 하세요");
               return;
            }

            ImgReader.readAsDataURL(img[0]);
         }

      }

      document.getElementById("imagePreview").src = document
            .getElementById("image").value;

   })();
</script>

<style>
.pageSizes {
   width: 1440px;
   margin: 70px;
   margin-top: 120px;
   font-family: 'Nanum Gothic', sans-serif;
}

ul, li {
   list-style-type: none;
}

#item_title {
   width: 700px;
   height: 25px;
   margin: 10px 0 0 20px;
   border-bottom: solid 2px #5D5D5D;
}

#item_form {
   width: 700px;
   margin-left: 20px;
   margin: 0 0 0 0px;
   margin-left: -38px;
}

#item_form li.row {
   height: 37px;
   margin-top: 15px;
   border-bottom: solid 2px #e5e5e5;
}

.item input {
   width: 150px;
}

.item select {
   width: 80px;
   height: 20px;
   font-size: 12px;
}

.col1 {
   width: 120px;
   text-align: right;
   display: inline-block;
   font-weight: bold;
   color: #4C4C4C;
   font-size: 15px;
}

.col2 {
   margin-left: 30px;
   display: inline-block;
   color: #888888;
   font-size: 13px;
   vertical-align: middle;
}

.col2 li {
   display: inline-block;
   vertical-align: middle;
}

#btsm {
   text-align: center;
   width: 700px;
   margin-top: 10px;
}
</style>
<body>
   <tiles:insertDefinition name="enterprise_header" />
   <tiles:insertDefinition name="enterprise_menu" />
   <div
      style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
      <div class="pageSizes">

         <div style="align: left; width: 700px; text-align: center;">
            <h2>상품수정</h2>
            <br>
            <hr style="width: 700px; align: center;">
         </div>
         <form method="post" name="iteminsert" enctype="multipart/form-data"
            action="/Kkuni/Item/UpdatePro.do?pageNum=${pageNum }&itemNo=${item.itemNo}"
            onsubmit="return checkIt()">
            <ul id="item_form">
               <li class="row" id="e_Id">
                  <ul class="item">
                     <li class="col1">업체명</li>
                     <li class="col2"><input type="text" name="eId"
                        value="${item.eId}" required readonly></li>
                  </ul>
               </li>
               <li class="row">
                  <ul class="category">
                     <li class="col1">카테고리</li>
                     <li class="col2"><c:if test="${item.categoryNo == 1 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1" selected>유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 2 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2" selected>유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 3 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3" selected>유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 4 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4" selected>스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 5 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5" selected>스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 6 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6" selected>스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 7 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7" selected>스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 8 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8" selected>스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 9 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9" selected>음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 10 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10" selected>음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 11 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11" selected>음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 12 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 13 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13" selected>디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">>공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 14 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14" selected>디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">>공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 15 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15" selected>공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 16 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16" selected>공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 17 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17" selected>공간대여 > 공연/강연</option>
                              <option value="18">공간대여 > 스튜디오</option>
                           </select>
                        </c:if> <c:if test="${item.categoryNo == 18 }">
                           <select name="categoryNo" id='category1'
                              onChange="SelectValue(this.value)">
                              <option value="0">------선택------</option>
                              <option value="1">유아용품 > 장난감</option>
                              <option value="2">유아용품 > 책</option>
                              <option value="3">유아용품 > 탈것</option>
                              <option value="4">스포츠/레저 > 캠핑</option>
                              <option value="5">스포츠/레저 > 레저</option>
                              <option value="6">스포츠/레저 > 등산</option>
                              <option value="7">스포츠/레저 > 헬스</option>
                              <option value="8">스포츠/레저 > 탈것</option>
                              <option value="9">음향기기 > 마이크</option>
                              <option value="10">음향기기 > 스피커</option>
                              <option value="11">음향기기 > 우퍼</option>
                              <option value="12">디지털가전전 > 카메라</option>
                              <option value="13">디지털가전 > 노트북</option>
                              <option value="14">디지털가전 > 빔프로젝트</option>
                              <option value="15">공간대여 > 이벤트홀</option>
                              <option value="16">공간대여 > 파티룸</option>
                              <option value="17">공간대여 > 공연/강연</option>
                              <option value="18" selected>공간대여 > 스튜디오</option>
                           </select>
                        </c:if></li>
                  </ul>
               </li>


               <li class="row" id="itemNo">
                  <ul class="item">
                     <li class="col1">상품명</li>
                     <li class="col2"><input type="text" name="itemId"
                        value="${item.itemId}" required></li>


                  </ul>
               </li>
               <li class="row" id="price">
                  <ul class="item">
                     <li class="col1">가격</li>
                     <li class="col2"><input type="text" name="price"
                        value="${item.price}" required> 원</li>
                  </ul>
               </li>
               <li class="row" id="amount">
                  <ul class="item">
                     <li class="col1">수량</li>
                     <li class="col2"><input type="text" name="amount"
                        value="${item.amount}" style="width: 80px;" required> 개</li>
                  </ul>
               </li>
               <li class="row" id="item_state">
                  <ul class="item">
                     <li class="col1">상태</li>
                     <li class="col2"><c:if test="${item.itemstate eq 'yes' }">
                           <select name="itemstate" id="category2">
                              <option>-----선택------</option>
                              <option value="yes" selected>판매중</option>
                              <option value="no">품절</option>
                              <option value="out">대여중</option>
                           </select>
                        </c:if> <c:if test="${item.itemstate eq 'no' }">
                           <select name="itemstate" id="category2">
                              <option>-----선택------</option>
                              <option value="yes">판매중</option>
                              <option value="no" selected>품절</option>
                              <option value="out">대여중</option>
                           </select>
                        </c:if> <c:if test="${item.itemstate eq 'out' }">
                           <select name="itemstate" id="category2">
                              <option>-----선택------</option>
                              <option value="yes">판매중</option>
                              <option value="no">품절</option>
                              <option value="out" selected>대여중</option>
                           </select>
                        </c:if></li>
                  </ul>
               </li>
               <li class="row" id="rental_P">
                  <ul class="item">
                     <li class="col1">대여가능기간</li>
                     <li class="col2">최대 <input type="text" name="rentalP"
                        value="${item.rentalP}" style="width: 80px;" required> 일
                     </li>
                  </ul>
               </li>


               <li class="row" id="thumbnail" style="height: 200px">
                  <ul class="item">
                     <li class="col1">대표이미지</li>
                     <li class="col2"><img
                        src="${pageContext.request.contextPath }${item.tRealpath}"
                        style="width: 160px; height: 160px;"><br> <input
                        type="file" name="thumbnail" accept=".gif, .jpg, .png">
                        <input type="hidden" name="thumbnailo" value="${item.thumbnail}">
                        <input type="hidden" name="tRealpath" value="${item.tRealpath }">
                     </li>
                  </ul>
               </li>
               <li class="row" id="detail1" style="height: 200px">
                  <ul class="item">
                     <li class="col1">상세이미지1</li>
                     <li class="col2"><img
                        src="${pageContext.request.contextPath }${item.d1Realpath}"
                        style="width: 160px; height: 160px;"><br> <input
                        type="file" name="detail1" accept=".gif, .jpg, .png"> <input
                        type="hidden" name="detail1o" value="${item.detail1}"> <input
                        type="hidden" name="d1Realpath" value="${item.d1Realpath }">
                     </li>
                  </ul>
               </li>


               <li class="row" id="detail1" style="height: 200px">
                  <ul class="item">
                     <li class="col1">상세이미지2</li>
                     <li class="col2" id="imagePreview"><img
                        src="${pageContext.request.contextPath }${item.d2Realpath}"
                        style="width: 160px; height: 160px;"><br> <input
                        type="file" name="detail2" id="image" accept=".gif, .jpg, .png"
                        onchange="InputImage();"> <input type="hidden"
                        name="detail2o" value="${item.detail2}"> <input
                        type="hidden" name="d2Realpath" value="${item.d2Realpath }">

                     </li>
                  </ul>
               </li>
               <li class="row" id="detail1" style="height: 200px">
                  <ul class="item">
                     <li class="col1">상세이미지3</li>
                     <li class="col2"><img
                        src="${pageContext.request.contextPath }${item.d3Realpath}"
                        style="width: 160px; height: 160px;"><br> <input
                        type="file" name="detail3" accept=".gif, .jpg, .png"> <input
                        type="hidden" name="detail3o" value="${item.detail3}"> <input
                        type="hidden" name="d3Realpath" value="${item.d3Realpath }">
                     </li>
                  </ul>
               </li>
               <li id="r_address">
                  <ul class="item">
                     <li class="col1">반송지주소</li>
                     <li class="col2"><input type="text" name="rzipcode" size="7"
                        value="${item.rzipcode}" style="width: 120px" readonly required>
                        <input type="button" value="우편번호"
                        style="width: 80px; height: 21px;" onclick="zipCheck()">
                        <br> <input type="text" name="address" value="${address1 }"
                        style="width: 300px" required><br> <input
                        type="text" name="raddress" value="${address2 }"
                        style="width: 300px" placeholder="상세주소를 입력해주세요."></li>
                  </ul>
               </li>
            </ul>
            <li class="rows"
               style="width: 700px; margin-top: 10px; border-bottom: solid 4px #E4E4E4;"></li>


            <div id="btsm">
               <input type="hidden" name="itemNo" value="${item.itemNo}">
               <input type="submit" class="btn_p" value="상품수정"> <input
                  type="button" class="btn_b" value="취소"
                  onclick="javascript:history.go(-1)">
            </div>

         </form>
      </div>
   </div>
</body>
</html>