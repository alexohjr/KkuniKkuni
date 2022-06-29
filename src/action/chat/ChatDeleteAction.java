package action.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.chat.ChatDBBean;

public class ChatDeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mId = request.getParameter("mId");
		String eId = request.getParameter("eId");
		
		
		
		ChatDBBean chatPro = ChatDBBean.getInstance();
		chatPro.chatDelete(mId, eId);
		
		return "/Chat/chatDelete.jsp";
	}

}
