package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseLoginProAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String eId = request.getParameter("eId");
		String ePw = request.getParameter("ePw");

		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DB처리
		int check = dbPro.userCheck(eId, ePw);
		request.setAttribute("check", new Integer(check));
		request.setAttribute("eId", eId);
		
		request.getSession().setAttribute("eId", eId);
		
		
		
		if(eId.equals("semipro") && ePw.equals("kkuni")) {
			check = 5;
			request.setAttribute("check", new Integer(check));
		}
		// 해당 뷰에서 사용할 속성
		
		return "/Enterprise/enterpriseLoginPro.jsp";
	}

}
