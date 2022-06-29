package action.orderlist;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.orderlist.OrderlistDBBean;
import bean.orderlist.OrderlistDataBean;

public class InsertParcelNumFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		OrderlistDataBean orderlist = dbPro.getOrderDetail(orderNo);
		
		request.setAttribute("orderlist", orderlist);
		
		return "/Orderlist/insertParcelNum.jsp";
	}

}
