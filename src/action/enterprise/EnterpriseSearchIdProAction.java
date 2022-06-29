package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.enterprise.EnterpriseDataBean;
import bean.member.MemberDataBean;

public class EnterpriseSearchIdProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8"); 
		
		String email = request.getParameter("eMail");
		String name = request.getParameter("eName");  
		int check = 0;
		
		EnterpriseDBBean manager = EnterpriseDBBean.getInstance();
		EnterpriseDataBean c = manager.searchId(email, name); 
		if(c != null) { 
			String id = c.geteId();
			String cutid = id.substring(0, id.length()-3);
			String result = cutid.concat("***");
			request.setAttribute("id", result);
			check = 1; // 찾기 성공
		} else {
			check = -1; // 찾기 실패
		}

		request.setAttribute("name", name);
		request.setAttribute("c", c);
		request.setAttribute("check", check);
		
		return "/Enterprise/enterpriseSearchIdPro.jsp"; // 해당 뷰
	}

}
