<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html >
<html>
<head>
<title>1:1 채팅</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <meta http-equiv="refresh" content="300"> -->
	<meta name="viewport" content="width=device=width, initial-scale=1">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chat.css">
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript">
		function submitMemberFunction(){
			var receiver = $('#receiver').val();
			var sender = $('#sender').val();
			var contents = $('#contents').val();
			var mId = $('#mId').val();
			var eId = $('#eId').val();
			
			$.ajax({
				type: "POST",
				url: "/Kkuni/Chat/ChatMemberPro.do",
				data:{
					receiver: receiver,
					sender: sender,
					contents: contents,
					eId: eId,
					mId: mId
				},
				 dataType : "xml",
				success:function(xml){
					var result = $(xml).find("result").text();
					if(result == 1){
						alert('전송에 성공했습니다.');
						location.reload(true);
					}else if(result==0){
						alert('내용을 정확히 입력하세요.');
					}else{
						alert('내용을 입력하세요');
					}
				},
				error : function(xhr, textStatus, errorThrown) {
		               console.log(textStatus+xhr.status+errorThrown);
		               alert('에러')

				}
			});
			$('#contents').val('');
		}
		
	
	

	</script>
</head>
<body>
	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-12">
					<div class="portlet portlet-dafault">
						<div class="portlet-heading">
							<div class="portlet-title">
								<h4><i class="fa fa-circle text-green"></i>실시간 채팅방</h4>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chatlist" class="panel-collepase collepse in">
							<div class="portlet-body chat-widget" style="overflow-y:outo; height:outo; ">
								<div class="row">
									<div class="col-lg-12">
										<p class="text-center text-muted samll">
										<c:set var="now" value="<%=new java.util.Date()%>" />
										<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy/MM/dd" /></c:set> 
										<c:out value="${sysYear}" /></p>
									</div>
								</div>
								<div id="load_tweets" style="overflow-y:scroll; height:400px;">
								<c:forEach var="chat" items="${chatList}">
								<div class="row">
									<div class="col-lg-12">
										<div class="media">
											<img class="media-object img-circle" src="<%=request.getContextPath() %>/images/icon.png">
										<div class="media-body">
											<c:if test="${chat.mId != chat.receiver }">
											<h4 class="media-heading">보내는분[${chat.mId}]-->받는분[${chat.eName}]
											<span class="small pull-right">${chat.cTime}</span>
											</h4>
											</c:if>
											<c:if test="${chat.mId == chat.receiver }">
											<h4 class="media-heading">보내는분[${chat.eName}]-->받는분[${chat.mId}]
											<span class="small pull-right">${chat.cTime}</span>
											</h4>
											</c:if>
										</div>
										<p>${chat.contents}</p>
										</div>
									</div>
								</div>
								<hr>
								</c:forEach>
								</div>
					
							</div>
							<div class="portlet-footer">
								<div class="row" style="height:90px;">
									<div class="form-group col-xs-10">
										<textarea style="height:80px;width:800px; resize:none;" id="contents" class="form-contral" placeholder="메시지를 입력하세요."></textarea>
										<input type="hidden" id="receiver" value="${eId}">
										<input type="hidden" id="eId" value="${eId}">
										<input type="hidden" id="mId" value="${mId}">
										<input type="hidden" id="sender" value="${mId}">
									</div>
									<div class="form-group col-xs-2">
										<button type="button" class="brn brn-defoult pull-right" onclick="submitMemberFunction()">전송</button>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>