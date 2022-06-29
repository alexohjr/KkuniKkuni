package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MemberSearchPwFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/Member/memberSearchPwForm.jsp"; // ÇØ´ç ºä
	}

}