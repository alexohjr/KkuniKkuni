package action.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.cart.CartDBBean;

public class CartInsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String mId = request.getParameter("mId");
		
		CartDBBean dbpro = CartDBBean.getInstance();
		dbpro.insertCartlist(mId, itemNo);
		
		request.setAttribute("itemNo", itemNo);
		 
		return "/cart/Insert.jsp";
	}

}
