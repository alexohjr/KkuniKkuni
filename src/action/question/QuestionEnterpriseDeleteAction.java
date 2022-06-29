package action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;

public class QuestionEnterpriseDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

				
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String pageNum = request.getParameter("pageNum");
		String eId = request.getParameter("eId");
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		int check = dbPro.deleteEnterpriseArticle(questionNo, eId);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/Question/questionEnterpriseDelete.jsp";
	}
	
}
