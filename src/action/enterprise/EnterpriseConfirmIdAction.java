package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		String eId = request.getParameter("eId"); // ����� �Է� ���̵�
		
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DBó��
		int check = dbPro.confirmId(eId);
		
		if(eId.equals("semipro")) {
			check = 1;
		}
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("eId", eId);
		request.setAttribute("check", check);
		
		return "/Enterprise/enterpriseConfirmId.jsp";
	}

}
