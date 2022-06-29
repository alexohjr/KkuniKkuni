package action.member;

import bean.member.MemberDataBean;

import bean.member.MemberDBBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MemberLoginFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String id = request.getParameter("userid");
		String passwd = request.getParameter("userpwd");
		
		
		return "/Member/memberLoginForm.jsp"; // ÇØ´ç ºä
	}
}
