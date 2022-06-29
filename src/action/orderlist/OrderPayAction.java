package action.orderlist;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDBBean;
import bean.orderlist.OrderlistDataBean;

public class OrderPayAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		OrderlistDataBean orderlist = new OrderlistDataBean();
		
		String mId = request.getParameter("memId");
		/*String mId = (String)session.getAttribute("memId");*/
		String eId = request.getParameter("eId");
		
		orderlist.setOrderDate(new Timestamp(System.currentTimeMillis()));
		orderlist.setOrderAmount(Integer.parseInt(request.getParameter("orderAmount")));
		orderlist.setRentalD1(java.sql.Date.valueOf(request.getParameter("rentalD1")));
		orderlist.setRentalD2(java.sql.Date.valueOf(request.getParameter("rentalD2")));
		orderlist.setPayTotal(Integer.parseInt(request.getParameter("payTotal")));
		orderlist.setPayMethod(request.getParameter("payMethod"));
		orderlist.setOrderMeg(request.getParameter("orderMeg"));
		orderlist.setOrderState("complete");
		orderlist.setmId(mId);
		orderlist.seteId(eId);                                                                                                                        
		orderlist.setItemNo(request.getParameter("itemNo"));
		
		MemberDBBean memPro = MemberDBBean.getInstance();
		MemberDataBean memberlist = memPro.getMember(mId);
		
		String address = memberlist.getmAddress();
		
		int idx = address.indexOf("/");
	    String address1 = address.substring(0, idx);
	    String address2 = address.substring(idx+1);
		
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("address", address1+" "+address2);
		
		
		return "/Orderlist/orderPay.jsp";
	}
}
