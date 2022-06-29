package action.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

public class AnswerAdminDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	request.setCharacterEncoding("UTF-8");
	
	int questionNo = Integer.parseInt(request.getParameter("questionNo")); // 해당 글번호
	String pageNum = request.getParameter("pageNum"); // 해당 페이지 번호
	
	QuestionDBBean dbPro = QuestionDBBean.getInstance(); // DB처리
	QuestionDataBean article = dbPro.getAdminArticle(questionNo); // 해당 글번호에 대한 해당 레코드
	
	
	QuestionDataBean articleAnswer = null;
		
	int answerCount = dbPro.AnswerCount(questionNo);
	if(answerCount > 0) {
		articleAnswer = dbPro.getAdminAnswer(questionNo);
		request.setAttribute("articleAnswer", articleAnswer);
	}
	
	//해당 뷰에서 사용할 속성
	request.setAttribute("questionNo", questionNo);
	request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("article", article);
	
	
	request.setAttribute("answerCount", answerCount);
	
	return "/Answer/answerAdminDetail.jsp";
	}
}
