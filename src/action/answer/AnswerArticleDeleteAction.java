package action.answer;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;


public class AnswerArticleDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));

		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		dbPro.AnswerArticleDelete(questionNo);
		
		
		
		return "/Answer/answerArticleDelete.jsp";
	}

}
