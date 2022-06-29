package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;

public class ItemUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int ItemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		String pageNum = request.getParameter("pageNum");
		
		ItemDBBean dbPro = ItemDBBean.getInstance();
	    ItemDataBean item = dbPro.getItem(ItemNo);	    
	    
	    String address = item.getRaddress();
	    
	    int idx = address.indexOf("/");
	    String address1 = address.substring(0, idx);
	    String address2 = address.substring(idx+1);
	    
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("item", item);
	    request.setAttribute("address1",address1);
	    request.setAttribute("address2",address2);
		
	    return "/Item/itemUpdateForm.jsp";
	}

}
