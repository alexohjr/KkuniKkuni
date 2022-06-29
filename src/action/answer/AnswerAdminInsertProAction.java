package action.answer;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

public class AnswerAdminInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String pageNum = request.getParameter("pageNum");
		String aContents = request.getParameter("aContents");

		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		dbPro.insertAdminArticle(aContents, questionNo);
		
		QuestionDataBean articleAnswer = new QuestionDataBean();
		articleAnswer = dbPro.getAdminAnswer(questionNo);
		
		
		request.setAttribute("articleAnswer", articleAnswer);
		request.setAttribute("questionNo", questionNo);
		request.setAttribute("pageNum", pageNum);
		
		return "/Answer/answerAdminInsertPro.jsp";
	}

}
