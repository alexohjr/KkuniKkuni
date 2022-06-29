package action.member;

import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import bean.member.MemberDataBean;
import bean.member.MemberDBBean;
import action.CommandAction;


public class MemberInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8"); // �ѱ� ���ڵ�
		
		MemberDataBean member = new MemberDataBean(); // ������ó�� ��
		
		member.setmId(request.getParameter("userid"));
		member.setmPw(request.getParameter("userpwd"));
		member.setmName(request.getParameter("username"));
		
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthday = request.getParameter("birthday");
		
		member.setBirthday(birthyear+"-"+birthmonth+"-"+birthday);
		
		if(request.getParameter("sex").equals("female")) {
			member.setSex("F");
		} else {
			member.setSex("M");
		}
		member.setmTel(request.getParameter("tel"));
		member.setmMail(request.getParameter("email"));
		member.setmConfirm("member");
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
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DBó��
		dbPro.memberInsert(member);
		
		return "/Member/memberInsertPro.jsp"; // �ش� ��
	}
}
