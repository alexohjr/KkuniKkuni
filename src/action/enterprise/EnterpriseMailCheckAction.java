package action.enterprise;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseMailCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String mail = request.getParameter("eMail"); // 사용자 입력 이메일
		
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DB처리
		int check = dbPro.confirmMail(mail);
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("eMail", mail);
		request.setAttribute("check", check);
		
		
		return "/Enterprise/enterpriseMailCheck.jsp";
	}

}
