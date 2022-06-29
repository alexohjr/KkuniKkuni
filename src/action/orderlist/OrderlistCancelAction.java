package action.orderlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.orderlist.OrderlistDBBean;

public class OrderlistCancelAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String memId = request.getParameter("memId");
		System.out.println(memId);
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		dbPro.updateCanceling(num);
		
		request.setAttribute("num", new Integer(num));
		
		return "/orderlist/Orderlist.do?memId="+memId;
	}

}
