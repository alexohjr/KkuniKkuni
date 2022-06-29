<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>결제테스트</title>

<script type="text/javascript">

	function pay_check () {
	 var IMP = window.IMP; // 생략가능
	 IMP.init('imp79321636'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	 var mId = $('#memId').val();
	 var itemNo = $('#itemNo').val();
	 var orderAmount = $('#orderAmount').val();
	 var rentalD1 = $('#rentalD1').val();
	 var rentalD2 = $('#rentalD2').val();
	 var orderMeg = $('#orderMeg').val();
	 var payTotal = $('#payTotal').val();
	 
	 var mName = $('#mName').val();
	 var mTel = $('#mTel').val();
	 var mMail = $('#mMail').val();
	 var mZipcode = $('#mZipcode').val();
	 var mAddress = $('#mAddress').val();
	 
	 IMP.request_pay({
		    pg : 'kakao', //html5_inicis
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : 'KkuniKkuni',
		    amount : payTotal,
		    buyer_email : mMail,
		    buyer_name : mName,
		    buyer_tel : mTel,
		    buyer_addr : mAddress,
		    buyer_postcode : mZipcode
		}, function(rsp) {
		    if ( rsp.success ) {
		    	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
		    	jQuery.ajax({
		    		url: "/Kkuni/orderlist/Complete.do", //cross-domain error가 발생하지 않도록 주의해주세요
		    		type: 'POST',
		    		contentType : 'application/json;charset=UTF-8',
		    		dataType: 'json',
		    		data: {
			    		imp_uid : rsp.imp_uid
		    		}
		    	}).done(function(data) { //요청 성공시
		    		if ( everythings_fine ) {
		    			var msg = '결제가 완료되었습니다.';
		    			msg += '\n고유ID : ' + rsp.imp_uid;
		    			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
		    			msg += '\결제 금액 : ' + rsp.paid_amount;
		    			msg += '카드 승인번호 : ' + rsp.apply_num;
		    			
		    			alert(msg);
		    		} else {
		    			
		    		}
		    	});
		    	document.payInfo.submit();
		    	
		    } else {
		        alert(rsp.error_msg);
		        window.location.href = "/Kkuni/orderlist/Orderlist.do?memId="+mId;
		    }
		});
};
 
</script>


</head>
<body onload="pay_check();">
<form name="payInfo" action="/Kkuni/orderlist/Complete.do" method="post">
<input type="hidden" name="memId" id="memId" value="${orderlist.mId}">
<input type="hidden" name="itemNo" id="itemNo" value="${orderlist.itemNo}">
<input type="hidden" name="orderAmount" id="orderAmount"  value="${orderlist.orderAmount}">
<input type="hidden" name="rentalD1" id="rentalD1"  value="${orderlist.rentalD1}">
<input type="hidden" name="rentalD2" id="rentalD2"  value="${orderlist.rentalD2}">
<input type="hidden" name="payTotal" id="payTotal"  value="${orderlist.payTotal}">
<input type="hidden" name="orderMeg" id="orderMeg"  value="${orderlist.orderMeg}">
<input type="hidden" name="eId" id="eId"  value="${orderlist.eId}">
<input type="hidden" name="mName" id="mName"  value="${memberlist.mName}">
<input type="hidden" name="mTel" id="mTel"  value="${memberlist.mTel}">
<input type="hidden" name="mMail" id="mMail"  value="${memberlist.mMail}">
<input type="hidden" name="mZipcode" id="mZipcode"  value="${memberlist.mZipcode}">
<input type="hidden" name="mAddress" id="mAddress"  value="${address}">
</form>
</body>
</html>