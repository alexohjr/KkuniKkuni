package action.member;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MemberDeleteFormAction implements CommandAction {
	
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/Member/memberDeleteForm.jsp"; // ÇØ´ç ºä
	}

}
