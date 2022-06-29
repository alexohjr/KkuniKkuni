package action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;
import bean.orderlist.OrderlistDBBean;
import bean.orderlist.OrderlistDataBean;
import bean.slider.SliderDBBean;
import bean.slider.SliderDataBean;

public class MemberMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.getSession().removeAttribute("eId");
		
		ItemDBBean itemPro = ItemDBBean.getInstance();
		List itemList = null;
		List itemBest = null; 
		
		
		itemList = itemPro.getArticles();
		itemBest = itemPro.getArticlesBest();
		request.setAttribute("itemList", itemList);
		request.setAttribute("itemBest", itemBest);
		
		SliderDataBean slider = new SliderDataBean();
		SliderDBBean sliderPro = SliderDBBean.getInstance();
		slider = sliderPro.sliderSelect();
		
		request.setAttribute("slider", slider);
		
		
		
		String mId = (String) request.getSession().getAttribute("mId");
		if(mId != null) {
			request.setAttribute("mId", mId);
		}
		
		return "/Member/memberMain.jsp";
	}

}
