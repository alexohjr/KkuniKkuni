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
            <p>고객센터</p>
         </h3>
         <hr style="broder-color:black;">
      </div>
      <div>
         <div class="list">
            <table class="tablecl" width=100%;>
               <tr>
                  <td><a href="/Kkuni/Notice/NoticeList.do">공지사항</td>
               </tr>
            </table>
         </div>
         <div class="list">
            <table class="tablecl" width=100%;>
               <tr>
                  <td><a href="/Kkuni/Question/ServiceList.do?mId=${mId }">1:1 문의</td>
               </tr>
            </table>
         </div>
        
      </div>
   </div>
</body>
</html>