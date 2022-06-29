package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import bean.member.MemberDataBean;
import bean.member.MemberDBBean;


public class MemberSearchIdFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/Member/memberSearchIdForm.jsp"; // ÇØ´ç ºä
	}

}
