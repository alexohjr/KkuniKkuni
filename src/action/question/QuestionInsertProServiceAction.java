package action.question;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

public class QuestionInsertProServiceAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		QuestionDataBean article = new QuestionDataBean();
		
		String qSort ="";
		if(request.getParameter("qSort").equals("1")) {
			article.setqSort("item");
		} else if(request.getParameter("qSort").equals("2")) {
			article.setqSort("delivery");
		} else if(request.getParameter("qSort").equals("3")) {
			article.setqSort("pay");
		} else if(request.getParameter("qSort").equals("4")) {
			article.setqSort("member");
		}
		article.setqTitle(request.getParameter("qTitle"));
		article.setqContents(request.getParameter("qContents"));
		article.setqDate(new Timestamp(System.currentTimeMillis()));
		article.setqState("F");
		article.setmId(request.getParameter("mId"));
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		dbPro.insertMemberArticle(article);
		
		return "/Question/questionInsertProService.jsp";
	}

}
