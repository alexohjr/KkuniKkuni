<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>주문서</title>
<link href="../s_css/list.css" rel="stylesheet" type="text/css">
<link href="../s_css/button.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
   function frm_check() {
      if(document.orderfrm.check1.value=="" ||
            document.orderfrm.check2.value=="" ||
            document.orderfrm.check3.value==""){
              alert("동의해주세요");
              return false;
         }
      
      if(document.orderfrm.check1.value=="미동의"){
           alert("동의해주세요");
           return false;
      }
      
      if(document.orderfrm.check2.value=="미동의"){
           alert("개인정보 제공 동의해주세요");
           return false;
      }
      
      if(document.orderfrm.check3.value=="미동의"){
           alert("구매진행에 동의해주세요");
           return false;
      }
   } 
</script>
</head>
<body>
<tiles:insertDefinition name="header" />
<div class="pageSize">
<div align="left">
   <h2>대여주문서</h2>
   <hr>
   <h4>주문자 정보</h4>
</div>
<form name="orderfrm" action="/Kkuni/orderlist/OrderPay.do" method="post" onsubmit="return frm_check();">
<table class="table_size" style="height: 100px;">
   <tr>
      <td width="100">주문자</td>
      <td class="table_td">
         ${memberlist.mName}
         <%-- <input type="hidden" name="m_id" value="${sessionScope.memId}"> --%>
         <input type="hidden" name="memId" value="${memberlist.mId}">
      </td>
      
      <td width="150">휴대폰번호</td>
      <td class="table_td">${memberlist.mTel}
      </td>
      <td width="150">이메일</td>
      <td class="table_td">${memberlist.mMail}</td>
   </tr>
</table>

<div align="left">
   <hr>
   <h4>대여 상품</h4>
</div>

<table class="table_size">
   <tr>
      <td>이미지</td>
      <td>상품명</td>
      <td>단가</td>
      <td>수량</td>
      <td>기간</td>
      <td>총 금액</td>
      <td>배송비</td>
   </tr>
   
   <tr><td colspan="7"><hr class="table_line"></td></tr>
   
   <tr>
      <td><img src="${pageContext.request.contextPath}${itemList.tRealpath }" class="table_img"></td>
      <td>${itemList.itemId}
      <input type="hidden" name="itemNo" value="${itemList.itemNo}">
      <input type="hidden" name="eId" value="${eId}">
      </td>
      <td>${itemList.price}원</td>
      <td>${orderAmount}개<input type="hidden" name="orderAmount" value="${orderAmount}"></td>
      <td>
         ${rentalD1} ~ ${rentalD2}
         <input type="hidden" name="rentalD1" value="${rentalD1}">
         <input type="hidden" name="rentalD2" value="${rentalD2}">
      </td>
      <td>
         ${total}원
         <input type="hidden" name="payTotal" value="${itemList.price * orderAmount}">
      </td>
      <td>무료</td>
   </tr>
</table>

<div align="left">
   <hr>
   <h4>배송지 정보</h4>
   <table class="table_size" style="height: 300px;">
      <tr>
         <td width="200" align="center">수령인</td>
         <td>
            <input type="text" readonly value="${memberlist.mName}">
         </td>
         
      </tr>
      
      <tr>
         <td width="200" align="center">연락처</td>
         <td><input type="text" readonly value="${memberlist.mTel}"></td>
      </tr>
      
      <tr>
         <td width="200" align="center">주소</td>
         <td><input type="text" readonly value="${memberlist.mZipcode}"></td>
      </tr>
      
      <tr>
         <td width="200"></td>
         <td>
            <input type="text" style="width: 300px;" readonly value="${address}"> &nbsp;&nbsp;
            <input type="text" readonly value="${addressDetail}">
         </td>
      </tr>
   
      <tr>
         <td width="200" align="center">배송메세지</td>
         <td>
         <select name='orderMeg'>
             <option value='' selected>-- 선택 --</option>
             <option value='배송 전 연락주세요'>배송 전 연락주세요</option>
             <option value='경비실에 맡겨주세요'>경비실에 맡겨주세요</option>
             <option value='문 앞에 놓아주세요'>문 앞에 놓아주세요</option>
         </select>
         </td>
      </tr>
</table>
<hr>
   <h4>결제수단</h4>
   <label><input type="radio" name="payMethod" value="credit" checked> 카드</label>
<!--    <label><input type="radio" name="payMethod" value="read_time" > 실시간 계좌이체</label>
   <label><input type="radio" name="payMethod" value="no_bank" > 무통장 입금</label> -->
   
   <hr>
   <h4>※주문자동의</h4>
   <h5>대여기간 만료일로 부터 7일이내에 미반납시 법적 책임을 물을 수 있습니다.</h5>
   <label><input type="radio" name="check1" value="동의">동의</label>
   <label><input type="radio" name="check1" value="미동의" > 동의 안함</label>
   <h5>개인정보 제3자 제공동의 - 배송등 거래를 위해 판매자에게 개인정보가 제공됩니다.</h5>
   <label><input type="radio" name="check2" value="동의"> 동의</label>
   <label><input type="radio" name="check2" value="미동의" > 동의 안함</label>
   <h5>위 상품 정보 및 거래 조건을 확인하였으며, 구매 진행에 동의합니다.</h5>
   <label><input type="radio" name="check3" value="동의"> 동의</label>
   <label><input type="radio" name="check3" value="미동의" > 동의 안함</label>
   <hr>
</div>
<h2>총 결제 금액 &nbsp;&nbsp; ${itemList.price * orderAmount}원</h2>
<input type="submit" class="btn btn_p" value="결제">
<input type="button" value="취소" class="btn" onclick="document.location.href='/Kkuni/orderlist/Orderlist.do?memId=${memberlist.mId}'">
</form>
</div>
<tiles:insertDefinition name="footer" />
</body>
</html>