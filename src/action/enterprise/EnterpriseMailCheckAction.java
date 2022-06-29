package action.enterprise;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseMailCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String mail = request.getParameter("eMail"); // ����� �Է� �̸���
		
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DBó��
		int check = dbPro.confirmMail(mail);
		
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("eMail", mail);
		request.setAttribute("check", check);
		
		
		return "/Enterprise/enterpriseMailCheck.jsp";
	}

}
