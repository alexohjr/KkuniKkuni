package action.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.chat.ChatDBBean;

public class ChatEnterpriseListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String eId = request.getParameter("eId");

		List ChatList = null;
		ChatDBBean dbPro = ChatDBBean.getInstance();
		
		ChatList = dbPro.enterpriseList(eId);
		request.setAttribute("ChatList", ChatList);
		
		
		
		
		return "/Chat/enterpriseChatList.jsp";
	}

}
