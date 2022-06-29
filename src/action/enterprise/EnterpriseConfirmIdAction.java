package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		String eId = request.getParameter("eId"); // 사용자 입력 아이디
		
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DB처리
		int check = dbPro.confirmId(eId);
		
		if(eId.equals("semipro")) {
			check = 1;
		}
		// 해당 뷰에서 사용할 속성
		request.setAttribute("eId", eId);
		request.setAttribute("check", check);
		
		return "/Enterprise/enterpriseConfirmId.jsp";
	}

}
