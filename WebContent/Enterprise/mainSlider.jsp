<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
<title>상품등록</title>
<link rel="stylesheet" href="../css/button.css">

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
   width: 770px;
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
<tiles:insertDefinition name="admin_header" />
<tiles:insertDefinition name="admin_menu" />
<div style="background-color: white; width: 1705px; min-height: 850px; margin: 10px; margin: 80px 0px 0px 200px;">
	<div class="pageSizes">
	<br>
		<div style="align: left; width: 770px;">
		<h2>메인 슬라이더 수정</h2><br>
		<hr style="width: 770px;">
        </div>
   <form method="post" name="image" enctype="multipart/form-data" action="/Kkuni/Enterprise/MainSliderPro.do" style="text-align: left;">
		<ul id="item_form">
            <li class="row" id="thumbnail">
               	<ul class="item">
                  <li class="col1">첫번째 페이지</li>
                  <li class="col2"><input type="file" name="slider1" accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
            <li class="row" id="thumbnail">
               	<ul class="item">
                  <li class="col1">두번째 페이지</li>
                  <li class="col2"><input type="file" name="slider2" accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
            <li class="row" id="thumbnail">
               	<ul class="item">
                  <li class="col1">세번째 페이지</li>
                  <li class="col2"><input type="file" name="slider3" accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
            <li class="row" id="thumbnail">
               	<ul class="item">
                  <li class="col1">네번째 페이지</li>
                  <li class="col2"><input type="file" name="slider4" accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
            <li class="row" id="thumbnail">
               	<ul class="item">
                  <li class="col1">다섯번째 페이지</li>
                  <li class="col2"><input type="file" name="slider5" accept="image/gif, image/jpeg, image/png" required></li>
               </ul>
            </li>
       </ul>
       <div align="center" style="margin-top: 10px; margin-left: -95px;">
       <input type="hidden" name="intro1og" value="${slider.intro1 }">
       <input type="hidden" name="intro2og" value="${slider.intro2 }">
       <input type="hidden" name="intro3og" value="${slider.intro3 }">
       <input type="hidden" name="intro4og" value="${slider.intro4 }">
       <input type="hidden" name="intro5og" value="${slider.intro5 }">
       <input type="submit" class="btn_p" value="수정" >    
       <input type="button" class="btn_b" value="취소" onclick="javascript:history.go(-1)">
       </div>
	</form>
	</div>
</div>
</body>
</html>