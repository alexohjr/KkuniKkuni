package action.chat;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.chat.ChatDBBean;
import bean.chat.ChatDataBean;

public class ChatMemberAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String mId= request.getParameter("mId");
		String eId= request.getParameter("eId");
		
		
		
		ChatDBBean chat = ChatDBBean.getInstance();
		ArrayList<ChatDataBean> chatList = chat.getChatList(mId,eId);
		
		request.setAttribute("eId", eId);
		request.setAttribute("chatList", chatList);
		return "/Chat/chatMember.jsp";
		
	}

}		
