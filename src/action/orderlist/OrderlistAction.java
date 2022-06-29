package action.orderlist;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDBBean;

public class OrderlistAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		String mId = (String) request.getSession().getAttribute("mId");
		String search = request.getParameter("search");
		String eId = request.getParameter("eId");
		
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
			count = dbPro.getMemOrderlistCount(mId);
			
			if(count > 0) {
				orderlists = dbPro.getOrderlists(mId, startRow, endRow);
			} else {
				orderlists = Collections.EMPTY_LIST;
			}
		}
		
		else {
			count = dbPro.getSearchMemOrderlistCount(mId, search);
			
			if(count > 0) {
				orderlists = dbPro.getSearchMemOrderlists(mId, startRow, endRow, search);
			} else {
				orderlists = Collections.EMPTY_LIST;
			}
		}
				
		request.setAttribute("count", new Integer(count));
		request.setAttribute("orderlists", orderlists);
		request.setAttribute("memId", mId);
		request.setAttribute("search", search);
		
		MemberDataBean memberlist = new MemberDataBean();
		MemberDBBean memPro = MemberDBBean.getInstance();
		memPro.getMember(mId);
		
		request.setAttribute("memberlist", memberlist);
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("eId", eId);
		
		/*DecimalFormat df = new DecimalFormat("###,###");
		String total = df.format(orderlist.getPayTotal());
		
		request.setAttribute("orderTotal", total);*/
		
		return "/Orderlist/orderlist.jsp";
		
	}

}
