package action.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import action.CommandAction;
import bean.chat.ChatDBBean;
import bean.chat.ChatDataBean;

public class ChatMemberProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String receiver = request.getParameter("receiver");
		String sender = request.getParameter("sender");
		String contents = request.getParameter("contents");
		String mId = request.getParameter("mId");
		String eId = request.getParameter("eId");
		
		ChatDataBean chat = new ChatDataBean();
		chat.setReceiver(receiver);
		chat.setSender(sender);
		chat.setContents(contents);
		chat.setmId(mId);
		chat.seteId(eId);
		
		
		String result = ChatDBBean.getInstance().submitMember(chat)+"";
		if(contents == null || contents.equals("")) {
			response.getWriter().write("0");
			
		}else {
			response.getWriter().write(result+"");
		}
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		request.setAttribute("result", result);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		//result.append("{\"result\":"+insert+"}");
		//System.out.println(result);
		/*return jsonObject.toString();*/
		return "/Chat/resultMember.jsp";
	}

}
