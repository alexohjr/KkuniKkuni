package action.enterprise;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseAdminListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ
		String search = request.getParameter("search"); // �˻� ����
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10; // ���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
		int endRow = currentPage * pageSize; // �� �������� ������ �۹�ȣ
		int count = 0;
		int number = 0;
		
		List articleList = null;
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DB����
		if(search == null) {
			count = dbPro.getEnterpriseListCount(); // ��ü ���� ��
		} else {
			count = dbPro.getEnterpriseListSearchCount(search);
		} 
		
		if (count > 0) {
			articleList = dbPro.getEnterpriseList(startRow, endRow, search); // ���� �������� �ش��ϴ� ��
		} else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize; // �۸�Ͽ� ǥ���� �� ��ȣ
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("search", search);
		
		return "/Enterprise/enterpriseAdminList.jsp"; // �ش� ��
	}

}
