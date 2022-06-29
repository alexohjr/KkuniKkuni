package action.item;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;

public class ItemListAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

      
      request.setCharacterEncoding("UTF-8");
      
      List itemLists = null;
      
      ItemDBBean dbPro = ItemDBBean.getInstance();
      /*ItemDataBean itemLists = new ItemDataBean();*/
            
      String search = request.getParameter("search");

      String pageNum = request.getParameter("pageNum"); // 페이지 번호
      
      int cNo = 0;
        
      if(request.getParameter("cNo") != null) {
         cNo = Integer.parseInt(request.getParameter("cNo"));   
         request.setAttribute("cNo", cNo);
      } else {
         cNo = 0;
      }
      
      
      if (pageNum == null) {
         pageNum = "1";
      }
      int pageSize = 12; // 한페이지의 글의 개수
      int currentPage = Integer.parseInt(pageNum);
      int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
      int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
      int count = 0;
      int number = 0;
      int itemqt = 0;

      count = dbPro.getArticleCount(); // 전체 글의 수
      
      if (count > 0) {
         itemLists = dbPro.getItemList(start, end, cNo);
         count = dbPro.getCatCount(cNo);
      } else {
         itemLists = Collections.EMPTY_LIST;
      }

      number = count - (currentPage - 1) * pageSize; // 글목록에 표시할 글 번호

      request.setAttribute("currentPage", new Integer(currentPage));
      request.setAttribute("start", new Integer(start));
      request.setAttribute("end", new Integer(end));
      request.setAttribute("count", new Integer(count));
      request.setAttribute("pageSize", new Integer(pageSize));
      request.setAttribute("number", new Integer(number));
      request.setAttribute("itemqt", itemqt);
      request.setAttribute("itemLists", itemLists);
      
      
      
      return "/Item/itemList.jsp";
   }

}
