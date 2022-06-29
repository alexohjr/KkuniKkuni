package action.item;

import java.text.DecimalFormat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.cart.CartDBBean;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;
import bean.review.ReviewDBBean;

public class ItemDetailAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      
      List itemdetails = null;
      List reviewList = null;
      
      int itemNo = Integer.parseInt(request.getParameter("itemNo"));
      String pageNum = request.getParameter("pageNum");
      String reviewNum = request.getParameter("reviewNum");
      String mId = (String) request.getSession().getAttribute("mId");
      
      
      ItemDataBean itemdetail = new ItemDataBean();
      ItemDBBean dbPro = ItemDBBean.getInstance();
      
      int rCount = 0;
      ReviewDBBean dbPro2 = ReviewDBBean.getInstance();
      CartDBBean cbPro = CartDBBean.getInstance();
      int Cartcount = cbPro.getCartlist(mId, itemNo);
  
      rCount = dbPro2.detailReviewCount(itemNo);
      
      if (pageNum == null) {
         pageNum = "1";
      }
      if(reviewNum == null) {
         reviewNum = "1";
      }
      
      // �ı� ��� �Ӽ�
      int pageSize = 50; // ���������� ���� ����
      int currentPage = Integer.parseInt(reviewNum);
      int start = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
      int end = currentPage * pageSize; // �� �������� ������ �۹�ȣ
      int count = 0;
      int number = 0;
      
      
      if(rCount != 0) {  
         reviewList = dbPro2.getReviews(start, end, itemNo);
      }
      
      itemdetail = dbPro.getItemlist(itemNo);
      //itemdetails = dbPro.getArticles();

      DecimalFormat df = new DecimalFormat("###,###");
      /* String total = df.format(itemdetail.getPrice()); */
      request.setAttribute("itemList", itemdetail);
      request.setAttribute("reviewList", reviewList);
      request.setAttribute("rCount", rCount);
      request.setAttribute("memId", mId);
      request.setAttribute("Cartcount", Cartcount);
      /* request.setAttribute("itemTotal", total); */

      return "/Item/itemDetail.jsp";
   }

}