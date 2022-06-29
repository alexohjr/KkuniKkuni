package action.cart;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.cart.CartDBBean;
import bean.item.ItemDBBean;

public class CartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		List cartList = null;
		int cartcount = 0;
		
		String mId = request.getParameter("mId");
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		
		/*int itemNo = Integer.parseInt(request.getParameter("itemNo"));*/

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 8; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		CartDBBean dbPro = CartDBBean.getInstance(); // DB연동
		count = dbPro.getCartCount(mId); // 관심상품 개수
		
		if (count > 0) {
			// 현재 페이지에 해당하는 글
			cartList = dbPro.getCartlist(start, end, mId); 
		} else {
			cartList = Collections.EMPTY_LIST;
		}

		number = count - (currentPage - 1) * pageSize; // 글목록에 표시할 글 번호
		// 해당 뷰에서 사용할 속성
		request.setAttribute("cartcount", cartcount);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("start", new Integer(start));
		request.setAttribute("end", new Integer(end));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("cartList", cartList);

		return "/cart/cartList.jsp";
	}

}
