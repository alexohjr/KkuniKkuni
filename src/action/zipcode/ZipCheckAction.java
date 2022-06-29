package action.zipcode;

import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDBBean;
import bean.member.ZipcodeBean;
import action.CommandAction;

public class ZipCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		String check = request.getParameter("check"); //y
		String area4 = request.getParameter("area4"); //µø¿Ã∏ß
		MemberDBBean manager = MemberDBBean.getInstance();
		Vector zipcodeList = manager.zipcodeRead(area4);
		int totalList = zipcodeList.size();
		
		
		request.setAttribute("check", check);
		request.setAttribute("zipcodeList", zipcodeList);
		request.setAttribute("totalList", totalList);
		
		return "/ZipCheck/ZipCheck.jsp";
	}
}
