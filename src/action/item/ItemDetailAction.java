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
      
      // 후기 목록 속성
      int pageSize = 50; // 한페이지의 글의 개수
      int currentPage = Integer.parseInt(reviewNum);
      int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
      int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
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