package action.question;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionServiceDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String pageNum = request.getParameter("pageNum");
		String mId = request.getParameter("mId");
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		int check = dbPro.deleteMemberArticle(questionNo, mId);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/Question/questionDeleteService.jsp";
	}
}
