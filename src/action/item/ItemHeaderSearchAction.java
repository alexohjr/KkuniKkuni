package action.item;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;

public class ItemHeaderSearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		List itemLists = null;
		
		String search = request.getParameter("search");
		String pageNum = request.getParameter("pageNum");
		ItemDBBean dbPro = ItemDBBean.getInstance();
		
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 12; // ���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
		int end = currentPage * pageSize; // �� �������� ������ �۹�ȣ
		int count = 0;
		int number = 0;
		
		count = dbPro.getHeaderCount(search); // �˻��� ��ǰ�� ���� ������
		
		if(count > 0) { 
			itemLists = dbPro.getHeaderItemList(start, end, search);
		} else {
			count = 0; // ��ǰ����
			itemLists = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("start", new Integer(start));
		request.setAttribute("end", new Integer(end));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("itemLists", itemLists);
		request.setAttribute("search", search);
		
		return "/Item/itemHeaderSearch.jsp";
	}

}
