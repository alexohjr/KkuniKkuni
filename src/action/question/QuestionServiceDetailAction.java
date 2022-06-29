package action.question;

import action.CommandAction;
import bean.question.QuestionDBBean;
import bean.question.QuestionDataBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionServiceDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("questionNo")); // �ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum"); // �ش� ������ ��ȣ
		
		QuestionDBBean dbPro = QuestionDBBean.getInstance(); // DBó��
		QuestionDataBean article = dbPro.getMemberArticle(num); // �ش� �۹�ȣ�� ���� �ش� ���ڵ�
		
		QuestionDataBean articleAnswer = null;
		
		int answerCount = dbPro.AnswerCount(num);
		if(answerCount >0) {
			articleAnswer = dbPro.getAdminAnswer(num);
			request.setAttribute("articleAnswer", articleAnswer);
		}
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		request.setAttribute("answerCount", answerCount);
		
		return "/Question/questionListServiceDetail.jsp";
	}
}
