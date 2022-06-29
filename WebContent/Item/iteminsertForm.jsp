<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
<title>상품등록</title>
<link rel="stylesheet" href="../css/button.css">
<script>
   function checkIt() {
      var userinput = eval("document.iteminsert");

      if (!userinput.itemId.value) {
         alert("상품명을 입력하세요");
         return false;
      }

      if (!userinput.price.value) {
         alert("가격을 입력하세요");
         return false;
      }
      if (!userinput.amount.value) {
         alert("수량을 입력하세요");
         return false;
      }

      if (userinput.itemstate.value == '1') {
         alert("품절여부를 선택하세요");
         return false;
      }

      if (userinput.categoryNo.value == '0') {
         alert("카테고리를 선택하세요.");
         return false;
      }
      if (userinput.thumbnail.value == null) {
         alert("대표이미지를 첨부해주세요.");
         return false;
      }
      if (userinput.detail1.value == null) {
         alert("상세이미지를 첨부해주세요");
         return false;
      }
      if (!userinput.rzipcode.value) {
         alert("반송지주소를 입력해주세요.");
         return false;
      }
      if (!userinput.address.value) {
         alert("반송지 주소를 입력해주세요.");
         return false;
      }
      return true;
   }

   function zipCheck() {
      url = "/Kkuni/Item/ZipCheck.do";
      window
            .open(
                  url,
                  "post",
                  "toolbar=no, left=770%, top=400%, width=500, height=300 directories=no, status=yes, scrollbars=yes, menubar=no");
   }
</script>
<style>
.pageSizes {
   width: 1440px;
   margin: 70px;
   text-align: center;
   font-family: 'Nanum Gothic', sans-serif;
}

ul {
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

#item_form li.rows {
   margin-top: 10px;
   border-bottom: solid 4px #E4E4E4;
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
}

.col2 li {
   display: inline-block;
   vertical-align: middle;
}
</style>
<body>
   <tiles:insertDefinition name="enterprise_header" />
   <tiles:insertDefinition name="enterprise_menu" />
   <div
      style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
      <div class="pageSizes">
         <!--  align="center" -->
         <br>
         <div style="align: left; width: 700px;">
            <h2>상품등록</h2>
            <br>
            <hr style="width: 700px;">
         </div>

         <form method="post" name="iteminsert" enctype="multipart/form-data"
            action="/Kkuni/Item/InsertPro.do" onsubmit="return checkIt()"
            style="text-align: left;">
            <ul id="item_form">
               <li class="row" id="e_Id">
                  <ul class="item">
                     <li class="col1">업체명</li>
                     <li class="col2">${eId }<input type="hidden" name="eId"
                        value="${eId }">
                     </li>
                  </ul>
               </li>
               <li class="row">
                  <ul class="category">
                     <li class="col1">카테고리</li>
                     <li class="col2"><select name="categoryNo" id="category1-1">
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
                           <option value="12">디지털가전 > 카메라</option>
                           <option value="13">디지털가전 > 노트북</option>
                           <option value="14">디지털가전 > 빔프로젝트</option>
                           <option value="15">공간대여 > 이벤트홀</option>
                           <option value="16">공간대여 > 파티룸</option>
                           <option value="17">공간대여 > 공연/강연</option>
                           <option value="18">공간대여 > 스튜디오</option>
                     </select></li>

                  </ul>
               </li>
               <li class="row" id="itemNo">
                  <ul class="item">
                     <li class="col1">상품명</li>
                     <li class="col2"><input type="text" name="itemId" required></li>
               </li>

            </ul>
            </li>
            <li class="row" id="price">
               <ul class="item">
                  <li class="col1">가격</li>
                  <li class="col2"><input type="text" name="price" required>
                     원</li>
               </ul>
            </li>
            <li class="row" id="amount">
               <ul class="item">
                  <li class="col1">수량</li>
                  <li class="col2"><input type="text" name="amount"
                     style="width: 80px;" required> 개</li>
               </ul>
            </li>
            <li class="row" id="item_state">
               <ul class="item">
                  <li class="col1">상태</li>
                  <li class="col2"><select name="itemstate" id="category1-1">
                        <option value="1">-----선택------</option>
                        <option value="yes">판매중</option>
                        <option value="no">품절</option>
                        <option value="out">대여중</option>
                  </select></li>
               </ul>
            </li>
            <li class="row" id="rental_P">
               <ul class="item">
                  <li class="col1">대여가능기간</li>
                  <li class="col2">최대 <input type="text" name="rentalP"
                     style="width: 80px;" required> 일
                  </li>
               </ul>
            </li>

            <li class="row" id="thumbnail">
               <ul class="item">
                  <li class="col1">대표이미지</li>
                  <li class="col2"><input type="file" name="thumbnail"
                     accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
            <li class="row" id="detail1">
               <ul class="item">
                  <li class="col1">상세이미지1</li>
                  <li class="col2"><input type="file" name="detail1"
                     accept=".gif, .jpg, .png" required></li>
               </ul>
            </li>
            <li class="row" id="detail1">
               <ul class="item">
                  <li class="col1">상세이미지2</li>
                  <li class="col2"><input type="file" name="detail2"
                     accept=".gif, .jpg, .png"></li>
               </ul>
            </li>
            <li class="row" id="detail1">
               <ul class="item">

                  <li class="col1">상세이미지3</li>
                  <li class="col2"><input type="file" name="detail3"
                     accept=".gif, .jpg, .png"></li>
               </ul>
            </li>
            <li id="r_address">
               <ul class="item">
                  <li class="col1">반송지주소</li>
                  <li class="col2" style="vertical-align: middle;">
                  <input type="text" name="rzipcode" size="7" readonly="readonly" style="width: 120px" required>
                  <input type="button" value="우편번호" style="width: 80px; height: 21px;" x" onclick="zipCheck()"> <br>
                  <input type="text" name="address" style="width: 300px;" readonly="readonly" required> <br>
                  <input type="text" name="raddress" style="width: 300px;" placeholder="상세주소를 입력해주세요."></li>
               </ul>
            </li>
            <li class="rows"></li>


            <div align="center" style="margin-top: 10px;">
               <input type="submit" class="btn_p" value="상품등록">
               <!-- <input type="submit" class="btn_b" value="취소"> -->
            </div>
         </form>
      </div>
   </div>
</body>
</html>