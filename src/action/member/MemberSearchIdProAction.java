package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;


public class MemberSearchIdProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8"); 
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");  
		int check = 0;
		
		MemberDBBean manager = MemberDBBean.getInstance();
		MemberDataBean c = manager.searchId(email, name); 
		if(c != null) {
			String id = c.getmId();
			String cutid = id.substring(0, id.length()-3);
			String result = cutid.concat("***");
			request.setAttribute("id", result);
			check = 1;
		} else {
			check = -1;
		}

		request.setAttribute("name", name);
		request.setAttribute("c", c);
		request.setAttribute("check", check);
		
		return "/Member/memberSearchIdPro.jsp"; // ÇØ´ç ºä
	}
	
}
