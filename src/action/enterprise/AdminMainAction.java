package action.enterprise;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.notice.NoticeDBBean;
import bean.question.QuestionDBBean;

public class AdminMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

request.getParameter("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5; // ���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
		int endRow = currentPage * pageSize; // �� �������� ������ �۹�ȣ
		int count = 0;
		int entnumber = 5;
		int notinumber = 5;
		int questnumber = 5;
		String search = "";
		
		// ��ü ���� �Խ���
		List entList = null;
		EnterpriseDBBean entdbPro = EnterpriseDBBean.getInstance(); // DB����
		count = entdbPro.getEnterpriseListCount(); // ��ü ���� ��
		if (count > 0) {
			entList = entdbPro.getEnterpriseBoardList(startRow, endRow, search); // ���� �������� �ش��ϴ� ��
		} else {
			entList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("entnumber", new Integer(entnumber));
		request.setAttribute("entList", entList);
		
		// �������� �Խ���
		List noticeList = null;
		NoticeDBBean noticePro = NoticeDBBean.getInstance(); // DB����
		count = noticePro.getArttcleCount(); // ��ü ���� ��
		if (count > 0) {
			noticeList = noticePro.getBoardArcticle(startRow, endRow); // ���� �������� �ش��ϴ� ��
		} else {
			noticeList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("notinumber", new Integer(notinumber));
		request.setAttribute("noticeList", noticeList);
		
		//���� �Խ���
		List questionList = null;
		QuestionDBBean questionPro = QuestionDBBean.getInstance();
		count = questionPro.getAdminArticleCount();
		
		if(count > 0) {
			questionList = questionPro.getAdminBoardArticles(startRow, endRow, search);
		} else {
			questionList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("questnumber", new Integer(questnumber));
		request.setAttribute("questionList", questionList);
		
		return "/Enterprise/adminMain.jsp";
	}

}
