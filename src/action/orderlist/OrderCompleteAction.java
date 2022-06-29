package action.orderlist;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDBBean;
import bean.orderlist.OrderlistDataBean;

public class OrderCompleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		OrderlistDataBean orderlist = new OrderlistDataBean();
		
		
		String mId = request.getParameter("memId");
		//String mId = (String)session.getAttribute("memId");
		String eId = request.getParameter("eId");
		System.out.println(eId);
		
		orderlist.setOrderDate(new Timestamp(System.currentTimeMillis()));
		orderlist.setOrderAmount(Integer.parseInt(request.getParameter("orderAmount")));
		orderlist.setRentalD1(java.sql.Date.valueOf(request.getParameter("rentalD1")));
		orderlist.setRentalD2(java.sql.Date.valueOf(request.getParameter("rentalD2")));
		orderlist.setPayTotal(Integer.parseInt(request.getParameter("payTotal")));
		orderlist.setPayMethod("credit");
		orderlist.setOrderMeg(request.getParameter("orderMeg"));
		orderlist.setOrderState("complete"); 
		orderlist.setmId(mId); 
		orderlist.seteId(eId);
		orderlist.setItemNo(request.getParameter("itemNo"));
		
		DecimalFormat df = new DecimalFormat("###,###");
		String total = df.format(orderlist.getPayTotal());
		
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		dbPro.insertOrderlist(orderlist);
		
		MemberDBBean memPro = MemberDBBean.getInstance();
		MemberDataBean memberlist = memPro.getMember(mId);
		
		ItemDBBean itemPro = ItemDBBean.getInstance();
		ItemDataBean itemList = itemPro.getItem(Integer.parseInt(orderlist.getItemNo()));
		
		df = new DecimalFormat("###,###");
		String itemPrice= df.format(itemList.getPrice());
		
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("itemList", itemList);
		
		request.setAttribute("orderTotal", total);
		request.setAttribute("itemTotal", itemPrice);
		
		return "/Orderlist/orderComplete.jsp";
	}

}
