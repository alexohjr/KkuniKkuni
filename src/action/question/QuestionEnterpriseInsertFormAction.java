package action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class QuestionEnterpriseInsertFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int questionNo = 0;
		try {
			if(request.getParameter("questionNo")!=null) {
				questionNo = Integer.parseInt(request.getParameter("questionNo"));
				request.setAttribute("questionNo", new Integer("questionNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String id = request.getParameter("eId");
		
		return "/Question/questionEnterpriseInsertForm.jsp";
	}

}
