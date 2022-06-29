<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   $(document).ready(
         function() {
            jQuery(".list-item").hide();
            $(".list").click(
                  function() {
                     $(".list-item")
                           .not(
                                 $(this).next(".list-item")
                                       .slideToggle(500))
                           .slideUp();
                  });
         });
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:700');
</style>
<style>
body {
   font-family: 'Nanum Gothic', sans-serif;
}

a {
   text-decoration: none;
   color: black;
}

.menu {
   border: 1px solid #e4e4e4;
   background-color: white;
   width: 200px;
   display: inline;
   float: left;
}

.member_menu {
   text-align: center;
   color: black;
   vertical-align: middle;
}

.list {
   text-align: center;
   vertical-align: center;
   font-size: 12pt;
   font-weight: bold;
   color: #353535;
}

.list-item {
   text-align: center;
   padding: 8px;
   color: #8C8C8C;   
   font-size: 11pt;
   line-height: 290%;
}

.list a:focus {
   color: #FC9799;
   /* background-color: #FC9799; */
}

.list-item a:focus {
   color: #353535;
}

.tablecl {
   line-height: 290%;
   text-align: center;
}
</style>
</head>
<body>
   <div class="menu">

      <div class="member_menu">
         <h3>
            <p>마이페이지</p>
         </h3>
         <hr style="broder-color:black;">
      </div>
      <div>
         <div class="list">
            <table class="tablecl" width=100%;>
               <tr>
                  <td><a href="#">쇼핑정보</td>
                  <td><img src="<%=request.getContextPath()%>/images/arrow.png"></a></td>
               </tr>
            </table>
         </div>
         <div class="list-item">
            <a href="/Kkuni/orderlist/Orderlist.do">주문관리</a><br>
         </div>
         <div class="list">
            <table class="tablecl" width=100%;>
               <tr>
                  <td><a href="#">활동정보</td>
                  <td><img src="<%=request.getContextPath()%>/images/arrow.png"></a></td>
               </tr>
            </table>
         </div>
         <div class="list-item">
            <a href="/Kkuni/Cart/List.do?mId=${mId }">관심상품</a><br> 
            <a href="/Kkuni/Question/List.do?mId=${mId }">1:1문의</a><br> 
            <a href="/Kkuni/Chat/ChatMemberList.do?mId=${mId}">똑똑친구</a><br>
         </div>
         <div class="list">
            <table class="tablecl" width=100%;>
               <tr>
                  <td><a href="#">개인정보</td>
                  <td><img src="<%=request.getContextPath()%>/images/arrow.png"></a></td>
               </tr>
            </table>
         </div>
         <div class="list-item">
            <a href="/Kkuni/Member/ModifyForm.do?mId=${mId }">정보수정</a><br>
            <a href="/Kkuni/Member/DeleteForm.do">회원탈퇴</a><br>
         </div>
      </div>
   </div>
</body>
</html>