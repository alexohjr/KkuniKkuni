package action.member;

import bean.member.MemberDataBean;

import bean.member.MemberDBBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MemberModifyProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		
		MemberDataBean member = new MemberDataBean();
		member.setmId(mId);
		member.setmPw(request.getParameter("userpwd"));
		member.setmTel(request.getParameter("tel"));
		member.setmZipcode(request.getParameter("zipcode"));
		member.setmAddress(request.getParameter("address")+"/"+request.getParameter("addressdt"));
		member.setmAccountNo(request.getParameter("accountNo"));
		if(request.getParameter("bankName") == null) {
			member.setmBankName(null);
		} else if(request.getParameter("bankName").equals("ibk")) {
			member.setmBankName("���");
		} else if(request.getParameter("bankName").equals("woori")) {
			member.setmBankName("�츮");
		} else if(request.getParameter("bankName").equals("nh")) {
			member.setmBankName("����");
		} else if(request.getParameter("bankName").equals("kb")) {
			member.setmBankName("����");
		} else if(request.getParameter("bankName").equals("sh")) {
			member.setmBankName("����");
		}
		member.setmDepositor(request.getParameter("depositor"));
		
		MemberDBBean dbPro = MemberDBBean.getInstance();
		dbPro.updateMember(member);
		
		
		
		return "/Member/memberModifyPro.jsp"; // �ش� ��
	}


}