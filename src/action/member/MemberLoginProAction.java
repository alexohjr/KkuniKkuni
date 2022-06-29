package action.member;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;

public class MemberLoginProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("userid");
		String passwd = request.getParameter("userpwd");
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DBó��
		int check = dbPro.userCheck(mId, passwd);
		
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("check", new Integer(check));
		request.setAttribute("id", mId);
		
		request.getSession().setAttribute("mId", mId);
		
		
		return "/Member/memberLoginPro.jsp"; // �ش� ��
	}

}
