package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.enterprise.EnterpriseDataBean;

public class EnterpriseConfirmFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
		String eId = request.getParameter("eId");

		EnterpriseDBBean dbpro = EnterpriseDBBean.getInstance();
		EnterpriseDataBean ent = dbpro.getMember(eId);
		
		String address = ent.geteAddress();
		
		int idx = address.indexOf("/");
		String address1 = address.substring(0, idx);
		String address2 = address.substring(idx+1);
		
		request.setAttribute("ent", ent);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("address1", address1);
		request.setAttribute("address2", address2);
		
		return "/Enterprise/enterpriseConfirmForm.jsp";
	}

}
