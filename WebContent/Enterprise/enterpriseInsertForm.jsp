<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>업체 회원 가입</title>
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/custom.css">
<style>

body {
   line-height: 1.3em;
   width: 100%;
   height:1080px;
}

img {
   magin-left:auto;
   margin-right:auto;
   width:40%;
}

.f {
   margin-left:auto;
   margin-right:auto;
   width:32%;
}

ul {
   list-style:none;
}

br {
   display: block;
   margin: 10px 0;
   line-height: 22px;
   content: " ";
}

select {
   width:20%;
   height:25px;
}


input[type=button] {
   width:80px;
   height:25px;
}

input[type=submit] {
   width:100px;
   height:40px;
}

#cancel {
   width:100px;
   height:40px;
}
#confirmOk {
   color:#0000ff;
}

b {
   color:#FF0000;
}


</style>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script language="javascript" src="entInsertForm.js">

</script>

</head>
<body>

<div align="center">
<img src="<%=request.getContextPath()%>/image/insertlogo2.png">
</div>

<form method="post" action="/Kkuni/Enterprise/InsertPro.do" name="userinput" enctype="multipart/form-data" onsubmit="return submitCheck()">

   <h2 align="center">
      <strong>업체 회원 가입</strong>
   </h2>
   <br><br><br>
   <fieldset id="f1" class="f">
   <legend>기본정보</legend>
   <ul>
      <li>아이디<b>*</b>&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
         <input type="text" name="eId" id="eId" style="width:63%" required>
         <input type="button" name="confirmid" id="confirmid" value="중복체크" onclick="openConfirmid(this.form)">
         <input type="hidden" name="idCheck" value="idUncheck">
      </li><br>
      <li>패스워드<b>*</b>&emsp;&emsp;&emsp;<input type="password" name="ePw" id="ePw" style="width:63%" required></li><br>
      <li>패스워드확인<b>*</b>&emsp;<input type="password" name="ePw2" id="ePw2" style="width:63%" required></li><br>
   </ul>
   </fieldset>
   <p>&emsp;</p>

   <fieldset id="f2" class="f">
   <legend>업체정보</legend>
   <ul>
      <li>업체명<b>*</b>&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="eName" id="eName" style="width:63%" required>
      </li><br>
      <li>사업자 번호<b>*</b>&emsp;&nbsp;&nbsp;
      <input type="text" name="registerNo" id="registerNo" style="width:63%" required>
      <li><br>
      <li>소재지<b>*</b>&emsp;&emsp;&emsp;&emsp;우편번호&emsp;&emsp;&nbsp;
      <input type="text" name="zipcode" size="7" readonly>
      <input type="button" value="검색" id="zipcheck" onclick="zipCheck()"><br></li>
      <li>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
      <input type="text" name="address" style="width:58%" required></li><br>
      <li>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
      <input type="text" name="addressdt" style="width:58%" placeholder="상세주소를 입력해주세요."></li><br>
      <li>이메일<b>*</b>&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="eMail" id="eMail" style="width:63%" required>
      <input type="button" name="emailcheck" id="emailcheck" value="중복체크" onclick="mailCheck()" style="display:inline">
      <input type="button" name="emailconfirm" id="emailconfirm" value="이메일인증" onclick="mailConfirm()" style="display:none"> 
      <b id="confirmOk" style="display:none">인증완료</b>
      <input type="hidden" name="mailChecked" value="Uncheck">
      <input type="hidden" name="codeCheck" value="codeUncheck"></li><br>
      <li>업종<b>*</b>&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
      <select name="eType" id="select1">
         <option value="1" selected>물품대여</option>
         <option value="2">공간대여</option>
      </select></li><br>
      <li>통신판매번호<b>*</b>&nbsp;&nbsp;&nbsp;
      <input type="text" name="tmkno" id="tmkno" style="width:63%" required>
   </ul>
   </fieldset>
   <p>&emsp;</p>
   
   <fieldset id="f3" class="f">
   <legend>담당자 정보</legend>
   <ul>
      <li>이름<b>*</b>&emsp;&emsp;&emsp;&emsp;&emsp;
      <input type="text" name="tptbName" id="tptbName" style="width:63%"></li><br>
      <li>연락처<b>*</b>&emsp;&emsp;&emsp;&emsp;
      <input type="text" name="tptbTel" id="tptbTel" style="width:63%"></li><br>
   </ul>
   </fieldset>
   <p>&emsp;</p>
   
   <fieldset id="f4" class="f">
   <legend>정산 정보</legend>
   <ul>
      <li>은행<b>*</b>&emsp;&emsp;&emsp;&emsp;&emsp;
      <select name="eBankName" id="select2">
            <option value="ibk">기업은행</option>
            <option value="woori">우리은행</option>
            <option value="nh">농협은행</option>
            <option value="kb">국민은행</option>
            <option value="sh">신한은행</option>
         </select>
      </li><br>
      <li>계좌번호<b>*</b>&emsp;&emsp;&emsp;
      <input type="text" name="eAccountNo" id="eAccountNo" style="width:63%" required></li><br>
      <li>예금주<b>*</b>&emsp;&emsp;&emsp;&emsp;
      <input type="text" name="eDepositor" id="eDepositor" style="width:63%" required></li><br>
   </ul>
   </fieldset>
   <p>&emsp;</p>
   
   <fieldset id="f5" class="f">
   <legend>첨부파일</legend>
   <ul>
      <li>사업자등록증<b>*</b><input type="file" name="eFile" id="eFile" accept=".gif, .jpg, .png"></li>
      <li>업체로고&emsp;&emsp;&emsp;&emsp;
      <input type="file" name="eLogoFile" id="eLogoFile" accept=".gif, .jpg, .png"></li>
      <li>&emsp;</li>
      <li>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <input type="submit" name="submit" value="가입" class="btn_p"> 
      <input type="button" name="cancel" id="cancel" value="취소" class="btn_b" onclick="javascript:history.go(-1)"></li>
   </ul>
   
   </fieldset><br>

</form>

</body>
</html>