package action.orderlist;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDBBean;

public class OrderlistEnterpriseListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		String eId = request.getParameter("eId");
		/*String eId = (String)session.getAttribute("eId");*/
		String search = request.getParameter("search");
		String searchn = request.getParameter("searchn");
		

		if(pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List orderlists = null;
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		
		if(search == null) {
			count = dbPro.getOrderEnterpriselistCount(eId);
			
			if(count > 0) {
				orderlists = dbPro.getEnterpriseOrderlists(eId,startRow,endRow);
			} else {
				orderlists = Collections.EMPTY_LIST;
			}
		}
		
		else {
			int searchNum = Integer.parseInt(searchn);
			count = dbPro.getSearchEnterpriseOrderlistCount(eId, search, searchNum);
			
			if(count > 0) {
				orderlists = dbPro.getSearchEnterpriseOrderlists(eId,startRow,endRow, search, searchNum);
			} else {
				orderlists = Collections.EMPTY_LIST;
			}
		}
		
		
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("orderlists", orderlists);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("eId", eId);
		request.setAttribute("search", search);
		request.setAttribute("searchn", searchn);
		
		return "/Orderlist/orderlistEnterpriseList.jsp";
	}

}
