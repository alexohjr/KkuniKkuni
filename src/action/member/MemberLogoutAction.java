package action.member;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MemberLogoutAction  implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.getSession().removeAttribute("mId");
		
		return "/Member/memberLogout.jsp";
	}

}
