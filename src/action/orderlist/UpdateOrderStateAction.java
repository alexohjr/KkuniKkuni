package action.orderlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.orderlist.OrderlistDBBean;
import bean.orderlist.OrderlistDataBean;

public class UpdateOrderStateAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String eId = request.getParameter("eId");
		String state = request.getParameter("OrderState");
		
		if(state.equals("배송중")) {
			OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
			dbPro.updateDeliveried(orderNo);
		}
		
		else if(state.equals("취소대기")) {
			OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
			dbPro.deleteOrderlist(orderNo);
		}
		
		
		
		return "/orderlist/OrderEnterpriseList.do?eId="+eId+"&pageNum="+pageNum;
	}

}
