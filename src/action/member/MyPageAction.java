package action.member;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.cart.CartDBBean;
import bean.chat.ChatDBBean;
import bean.orderlist.OrderlistDBBean;
import bean.question.QuestionDBBean;
import bean.review.ReviewDBBean;

public class MyPageAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      request.setCharacterEncoding("UTF-8");

      String pageNum = request.getParameter("pageNum"); // 페이지 번호

      if (pageNum == null) {
         pageNum = "1";
      }
      int pageSize = 10; // 한페이지의 글의 개수
      int currentPage = Integer.parseInt(pageNum);
      int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
      int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
      int number = 0;
      int orderCount = 0;

      String mId = request.getParameter("mId");
      
      List articleList = null;
      OrderlistDBBean orderlist = OrderlistDBBean.getInstance();
      orderCount = orderlist.getOrderlistStateCount(mId);
      // 새로 추가
      int deliveryCount = orderlist.getOrderlistDeliveringCount(mId);
      request.setAttribute("deliveryCount", new Integer(deliveryCount));
      QuestionDBBean qbpro = QuestionDBBean.getInstance();
      int questionCount = qbpro.getMemberArticleCount(mId);
      request.setAttribute("questionCount", new Integer(questionCount));
      CartDBBean cbpro = CartDBBean.getInstance();
      int cartCount = cbpro.getCartCount(mId);
      request.setAttribute("cartCount", new Integer(cartCount));
      ReviewDBBean rbpro = ReviewDBBean.getInstance();
      int reviewCount = rbpro.memberReviewCount(mId);
      request.setAttribute("reviewCount", new Integer(reviewCount));
      ChatDBBean chPro = ChatDBBean.getInstance();
      int ChatCount = chPro.getMyChatCount(mId);
      request.setAttribute("ChatCount", new Integer(ChatCount));
      
      if(orderCount > 0) {
         articleList = orderlist.getOrderlistsState(mId);
      }else {
         articleList = Collections.EMPTY_LIST;
      }
      
      number = orderCount - (currentPage - 1) * pageSize;
      
      request.setAttribute("currentPage", new Integer(currentPage));
      request.setAttribute("orderCount", new Integer(orderCount));
      request.setAttribute("pageSize", new Integer(pageSize));
      request.setAttribute("number", 5);
      request.setAttribute("articleList", articleList);
      
      
      
      return "/Member/myPage.jsp";
   }

}