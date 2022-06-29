<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>관리자 메인</title>

<style type="text/css">
.wrapper {
	width: 1650px;
	height: 880px;
	margin: 10px;

}

#adminmain1 {
	width: 1000px;
	height: 410px;
	float: left;
margin: 10px 10px 10px 20px;
}

#adminmain2 {
	width: 600px;
	height: 410px;
	float: right;
	margin: 10px;
}

#adminmain3 {
	width: 800px;
	height: 410px;
	float: left;
margin: 10px 10px 10px 20px;
}

#adminmain4 {
	width: 800px;
	height: 410px;
	float: right;
	margin: 10px;
	
}

table {
	border-collapse: collapse;
	vertical-align: middle;
	width: 750px;
}

.thmd {
	border-top: 2px solid #e4e4e4;
	border-bottom: 2px solid #e4e4e4;
	padding: 8px;
	text-align: center;
}

.tdcltrmd {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
}

.trmd {
	border-top: 1.2px solid #e4e4e4;
	border-bottom: 1.2px solid #e4e4e4;
	padding: 8px;
	height: 35px;
}

#textcolor {
	float: right;
	color: black;
}

#plustext {
	align: right;
	margin-bottom: 10px;
}
</style>
</head>
<body style="background-color: #dbdbdb;">
	<tiles:insertDefinition name="admin_header" />
	<tiles:insertDefinition name="admin_menu" /> 
	<div
		style="background-color: white; width: 1680x; min-height: 900px; margin: 10px; margin: 80px 0px 0px 200px;">

		<div class="wrapper">

			<!-- 인증 게시판 -->
			<div id="adminmain1">
				<div id="div1"style="line-height: 220%;">
					<h3 style="text-align: left; margin-bottom: -20px;">업체 인증</h3>
					<table id="plustext">
						<tr>
							<td><a id="textcolor" href="/Kkuni/Enterprise/AdminList.do">더보기</a></td>
						</tr>
					</table>
					<table>
						<tr class="trmd">
		                     <th class="thmd" width="50">번 호</th>
		                     <th class="thmd" width="100">업체명</th>
		                     <th class="thmd" width="150">카테고리</th>
		                     <th class="thmd" width="150">이메일</th>
		                     <th class="thmd" width="100">인증 상태</th>
                 		 </tr>
						<c:forEach var="ent" items="${entList}" end="5">
                     	<tr>
                        	<td align="center" width="50">
                        	<c:out value="${entnumber}" />
                        	<c:set var="entnumber" value="${entnumber-1}" />
                        	</td>
                        	<td align="center" width="100">
                        	${ent.eName}
                        	</td>
                        	<td align="center" width="150">
                        	<c:if test="${ent.eType eq 'item'}">
                        	물품
                        	</c:if>
                        	<c:if test="${ent.eType eq 'space'}">
                        	공간
                        	</c:if>
                        	</td>
                        	<td align="center" width="150">
                        	${ent.eMail}
                        	</td>
                        	<td align="center" width="100">
                        	<c:if test="${ent.eConfirm eq 'wait'}">
                        	<font style="color: #F15F5F">인증대기</font>
                        	</c:if>
                        	<c:if test="${ent.eConfirm eq 'confirm' }">
                        	<font style="color: #4374D9">인증완료</font>
                        	</c:if>
                        	</td>
                     	</tr>
                  			</c:forEach>
					</table>
				</div>
			</div>
			<!--------------------- 달력 -------------------------------->
			<div id="adminmain2">
				<iframe src="../calender/calendar.html" scrolling="no" width="320"
					height="260" frameborder="0"></iframe>
			</div>
			<!-- --------------------------선 --------------------------------->
			<hr style="width:98%; border:1px solid #dbdbdb;">

			<!---------------------- 공지사항 -------------------------------------->
			<div id="adminmain3">
				<h3 style="text-align: left; margin-bottom: -20px;">공지사항</h3>
				<table id="plustext">
					<tr>
						<td><a id="textcolor" href="/Kkuni/Notice/NoticeAdminList.do">더보기</a></td>
					</tr>
				</table>
				<table>
               		<tr class="trmd">
                  		<th class="thmd" width="50">번 호</th>
                  		<th class="thmd" width="250">제목</th>
                  		<th class="thmd" width="100">작성자</th>
                  		<th class="thmd" width="100">작성일</th>
               		</tr>
					 <c:forEach var="notice" items="${noticeList}" end="5">
                  	<tr>
                     	<td align="center" width="50">
                     	<c:out value="${notinumber}" />
                     	<c:set var="notinumber" value="${notinumber-1}" />
                     	</td>
                     	<td align="center" width="250">
                     	${notice.nTitle}
                     	</td>
                     	<td align="center" width="100">
                     	관리자
                     	</td>
                     	<td align="center" width="100">
                     	${notice.nDate}
                     	</td>
                  	</tr>
               		</c:forEach>
				</table>
			</div>

			<!---------------------------- 문의 ------------------------------->
			<div id="adminmain4">
				<h3 style="text-align: left; margin-bottom: -20px;">새로운문의</h3>
				<table id="plustext">
					<tr>
						<td><a id="textcolor" href="/Kkuni/Answer/AdminList.do">더보기</a></td>
					</tr>
				</table>
				<table>
               		<tr class="trmd">
                  		<th class="thmd" width="50">번 호</th>
                  		<th class="thmd" width="250">제목</th>
                  		<th class="thmd" width="100">작성자</th>
                  		<th class="thmd" width="100">작성일</th>
               		</tr>
					<c:forEach var="question" items="${questionList}" end="5">
                  	<tr>
                    	<td align="center" width="50">
                     	<c:out value="${questnumber}" />
                     	<c:set var="questnumber" value="${questnumber-1}" />
                     	</td>
                     	<td align="center" width="250">
                     	${question.qTitle}
                     	<td align="center" width="100">
                     	<c:if test="${!empty question.mId}">
                     	${question.mId}
                     	</c:if>
                     	<c:if test="${!empty question.eId}">
                     	${question.eId}
                     	</c:if>
                     	<td align="center" width="100">
                     	${question.qDate}
                  	</tr>
               		</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>