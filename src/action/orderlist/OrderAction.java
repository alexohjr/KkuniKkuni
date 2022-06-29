package action.orderlist;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.orderlist.OrderlistDataBean;

public class OrderAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      request.setCharacterEncoding("UTF-8");
      
      OrderlistDataBean orderlist = new OrderlistDataBean();
      String mId = (String) request.getSession().getAttribute("mId");
      orderlist.setmId(mId);
      String eId = request.getParameter("eId");
      
      int itemNo = Integer.parseInt(request.getParameter("itemNo"));
      int orderAmount = Integer.parseInt(request.getParameter("order_amount"));
      String rentalD1 = request.getParameter("rentalD1");
      String rentalD2 = request.getParameter("rentalD2");
      
        /* try{ 
              SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
              Date FirstDate = format.parse(rentalD1);
              Date SecondDate = format.parse(rentalD2);
              
              long calDate = FirstDate.getTime() - SecondDate.getTime(); 
              
              long calDateDays = calDate / ( 24*60*60*1000); 
       
              calDateDays = Math.abs(calDateDays);
              
              System.out.println("두 날짜의 날짜 차이: "+calDateDays);
              request.setAttribute("calDate", calDateDays);
              }
              catch(ParseException e)
              {
                  e.printStackTrace();
              }*/
      
      ItemDBBean itemPro = ItemDBBean.getInstance();
      ItemDataBean itemList = itemPro.getItem(itemNo);
      
      int Total = itemList.getPrice() * orderAmount;
      
      DecimalFormat df = new DecimalFormat("###,###");
      String total = df.format(Total);
      
      MemberDBBean memPro = MemberDBBean.getInstance();
      MemberDataBean memberlist = memPro.getMember(mId);
      
      String address = memberlist.getmAddress();
      
      int idx = address.indexOf("/");
       String address1 = address.substring(0, idx);
       String address2 = address.substring(idx+1);
      
      request.setAttribute("memberlist", memberlist);
      request.setAttribute("memId", mId);
      request.setAttribute("address", address1);
      request.setAttribute("addressDetail", address2);
      request.setAttribute("itemList", itemList);
      request.setAttribute("orderAmount", orderAmount);
      request.setAttribute("rentalD1", rentalD1);
      request.setAttribute("rentalD2", rentalD2);
      request.setAttribute("total", total);
      request.setAttribute("eId", eId);
      return "/Orderlist/order.jsp";
   }

}