package action.member;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;

public class MemberDeleteProAction implements CommandAction {
	
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DBÃ³¸®
		
		int check = dbPro.deleteMember(id, pwd);
		request.setAttribute("check", check);
		
		request.getSession().removeAttribute("mId");
		
		return "/Member/memberDeletePro.jsp"; // ÇØ´ç ºä
	}
}
