package action.notice;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;

public class NoticeSearchAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search"); // �˻� ����
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10; // ���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
		int end = currentPage * pageSize; // �� �������� ������ �۹�ȣ
		int count = 0;
		int number = 0;

		List articleList = null;
		NoticeDBBean dbPro = NoticeDBBean.getInstance(); // DB����
		count = dbPro.getArttcleCount(); // ��ü ���� ��

		if (count > 0) {
			articleList = dbPro.getArcticle(start,end,search); // ���� �������� �ش��ϴ� ��
		} else {
			articleList = Collections.EMPTY_LIST;
		}

		number = count - (currentPage - 1) * pageSize; // �۸�Ͽ� ǥ���� �� ��ȣ
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(start));
		request.setAttribute("endRow", new Integer(end));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "/Notice/noticeList.jsp"; // �ش� ��

	}

}
