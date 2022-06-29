package action.item;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;

public class ItemInsertFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String eId = request.getParameter("eId");
		
		request.setAttribute("eId", eId);
		
		return "/Item/iteminsertForm.jsp";

	}
}
