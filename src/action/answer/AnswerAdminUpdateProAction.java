package action.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

public class AnswerAdminUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String aContents = request.getParameter("aContents");
		String pageNum = request.getParameter("pageNum");

		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		dbPro.updateAdminArticle(aContents, questionNo);
		
		QuestionDataBean articleAnswer = new QuestionDataBean();
		articleAnswer = dbPro.getAdminAnswer(questionNo);
		
		int check = dbPro.updateAdminArticle(aContents, questionNo);

		
		request.setAttribute("articleAnswer", articleAnswer);
		request.setAttribute("questionNo", questionNo);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		
		return "/Answer/answerAdminUpdatePro.jsp";
	}

}
