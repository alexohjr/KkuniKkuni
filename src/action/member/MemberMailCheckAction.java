package action.member;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;

public class MemberMailCheckAction implements CommandAction {
	
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mail = request.getParameter("email"); // ����� �Է� �̸���
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DBó��
		int check = dbPro.confirmMail(mail);
		
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("mail", mail);
		request.setAttribute("check", check);
		
		return "/Member/memberMailCheck.jsp"; // �ش� ��
	}
}
