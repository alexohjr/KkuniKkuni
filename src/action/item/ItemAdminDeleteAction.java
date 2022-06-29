package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;

public class ItemAdminDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int ItemNo = Integer.parseInt(request.getParameter("itemNo"));
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
		ItemDBBean dbPro = ItemDBBean.getInstance();
	    dbPro.adminItemDelete(ItemNo);
		
		return "/Item/itemAdminDelete.jsp";
	}

}
