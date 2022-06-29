package action.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;

public class AnswerAdminDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String pageNum = request.getParameter("pageNum");
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		dbPro.deleteAdminArticle(questionNo);
		
		request.setAttribute("questionNo", questionNo);
		request.setAttribute("pageNum", pageNum);
		
		return "/Answer/answerAdminDelete.jsp";
	}

}
