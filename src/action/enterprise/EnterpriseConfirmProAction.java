package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseConfirmProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String eId = request.getParameter("eId");
		String pageNum = request.getParameter("pageNum");
		
		EnterpriseDBBean dbpro = EnterpriseDBBean.getInstance(); // DBÃ³¸®
		
		dbpro.confirmEnterprise(eId);
		request.setAttribute("pageNum", pageNum);
		
		return "/Enterprise/enterpriseConfirmPro.jsp";
	}
}
