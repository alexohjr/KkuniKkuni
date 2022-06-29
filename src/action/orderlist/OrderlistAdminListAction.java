package action.orderlist;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDBBean;

public class OrderlistAdminListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		
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
			count = dbPro.getOrderlistCount();
			
			if(count > 0) {
				orderlists = dbPro.getAdminOrderlists(startRow,endRow);
			} else {
				orderlists = Collections.EMPTY_LIST;
			}
		}
		
		else {
			count = dbPro.getSearchAdminOrderlistCount(search);
			
			if(count > 0) {
				orderlists = dbPro.getSearchAdminOrderlists(startRow,endRow,search);
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
		request.setAttribute("search", search);
		
		
		return "/Orderlist/orderlistAdminList.jsp";
	}

}
