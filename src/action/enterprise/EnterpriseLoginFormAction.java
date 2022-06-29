package action.enterprise;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class EnterpriseLoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.getSession().removeAttribute("mId");
		
		return "/Enterprise/enterpriseLoginForm.jsp";
	}

}
