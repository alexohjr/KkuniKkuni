<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<title>MemberChat</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/button.css">
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

body {
   font-family: 'Nanum Gothic', sans-serif;
}
table_ho {
	
    border-collapse: collapse;
  }
  .td_logo {
    border-left: 1px solid #e4e4e4;
    padding: 10px;
  }
  .tr_list{
   border-bottom: 1px solid #e4e4e4;
   padding: 10px;
  }
.myPage_category{
	width: 80px;
	margin: auto;
}

</style>

</head>
<body>
<tiles:insertDefinition name="header" />
<div class="total">
<tiles:insertDefinition name="nav" />
   <div class="wrapper">
      <section style="margin-left: 10px;">

         <div>
            <h3>똑똑친구</h3>
              
         </div>

         <hr width="1200" style="border: 0.8px solid #e4e4e4;">
         
         <div style="verflow:scroll; height:500px">
		<c:forEach var="List" items="${ChatList}">
		<table class="table_ho" width="1190"  style=" border:0.8px solid #e4e4e4">
			<tr>
				<td rowspan="2" width="100" height="100" >
				<a href="#" onclick="window.open('/Kkuni/Chat/ChatMember.do?mId=${List.mId}&eId=${List.eId}','1:1채팅','width=1000,height=700,fullscreen=no')" style="text-decoration:none; color: black;">
				<img class="myPage_category" src="<%=request.getContextPath()%>/images/rantal.png"></a></td>
				<td>${List.mId}--${List.eName }</td>
				<td width="100" height="100" rowspan="2" ><input type="button" class="btn_p" value="나가기" onclick="window.location='/Kkuni/Chat/ChatDelete.do?mId=${List.mId}&eId=${List.eId}'"></td>
			</tr>
			<tr>
				<td>${List.contents}</td>
			</tr>
		</table>
		</c:forEach>
	</div>
         
      </section>
   </div>
   </div>

<tiles:insertDefinition name="footer" />
</body>
</html>