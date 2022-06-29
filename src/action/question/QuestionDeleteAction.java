package action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;

public class QuestionDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		String pageNum = request.getParameter("pageNum");
		String mId = request.getParameter("mId");
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		int check = dbPro.deleteMemberArticle(questionNo, mId);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/Question/questionDelete.jsp";
	}
	
}
