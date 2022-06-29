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
	
	int questionNo = Integer.parseInt(request.getParameter("questionNo")); // �ش� �۹�ȣ
	String pageNum = request.getParameter("pageNum"); // �ش� ������ ��ȣ
	
	QuestionDBBean dbPro = QuestionDBBean.getInstance(); // DBó��
	QuestionDataBean article = dbPro.getAdminArticle(questionNo); // �ش� �۹�ȣ�� ���� �ش� ���ڵ�
	
	
	QuestionDataBean articleAnswer = null;
		
	int answerCount = dbPro.AnswerCount(questionNo);
	if(answerCount > 0) {
		articleAnswer = dbPro.getAdminAnswer(questionNo);
		request.setAttribute("articleAnswer", articleAnswer);
	}
	
	//�ش� �信�� ����� �Ӽ�
	request.setAttribute("questionNo", questionNo);
	request.setAttribute("pageNum", new Integer(pageNum));
	request.setAttribute("article", article);
	
	
	request.setAttribute("answerCount", answerCount);
	
	return "/Answer/answerAdminDetail.jsp";
	}
}
