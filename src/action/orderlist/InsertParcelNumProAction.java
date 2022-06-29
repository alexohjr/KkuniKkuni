package action.orderlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.orderlist.OrderlistDBBean;

public class InsertParcelNumProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String parcelNum = request.getParameter("parcelNum");
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		String check = "y";
		
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		dbPro.updateParcelNum(parcelNum, orderNo);
		
		request.setAttribute("check", check);
		
		return "/Orderlist/insertParcelNum.jsp";
	}

}
