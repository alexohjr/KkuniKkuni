package action.chat;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.chat.ChatDBBean;

public class ChatMemberListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String mId = request.getParameter("mId");

		List ChatList = null;
		ChatDBBean dbPro = ChatDBBean.getInstance();
		
		ChatList = dbPro.memberList(mId);
		request.setAttribute("ChatList", ChatList);
		
		
		
		
		return "/Chat/memberChatList.jsp";
	}

}
