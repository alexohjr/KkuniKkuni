package action.orderlist;

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

public class OrderlistDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		/*String pageNum = request.getParameter("pageNum");*/
		
		OrderlistDBBean dbPro = OrderlistDBBean.getInstance();
		OrderlistDataBean orderlist = dbPro.getOrderDetail(num);
		
		String memId = orderlist.getmId();
		int itemNo = Integer.parseInt(orderlist.getItemNo());
		
		System.out.println(orderlist.getItemNo());
		
		ItemDBBean itemPro = ItemDBBean.getInstance();
		ItemDataBean itemList = itemPro.getItem(itemNo);
		
		MemberDBBean memPro = MemberDBBean.getInstance();
		MemberDataBean memberlist = memPro.getMember(memId);
		
		DecimalFormat df = new DecimalFormat("###,###");
		String total = df.format(orderlist.getPayTotal());
		
		
		df = new DecimalFormat("###,###");
		String itemPrice= df.format(itemList.getPrice());
		
		String address = memberlist.getmAddress();
		
		int idx = address.indexOf("/");
	    String address1 = address.substring(0, idx);
	    String address2 = address.substring(idx+1);


		request.setAttribute("num", new Integer(num));
/*		request.setAttribute("pageNum", new Integer(pageNum));*/
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("itemList", itemList);
		
		request.setAttribute("orderTotal", total);
		request.setAttribute("itemTotal", itemPrice);
		request.setAttribute("address", address1);
		request.setAttribute("addressDetail", address2);
		
		
		return "/Orderlist/orderlistDetail.jsp";
	}

}
