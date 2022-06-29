package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import bean.member.MemberDBBean;
//import javax.servlet.annotation.*;

//@WebServlet("/Kkuni/Member/ConfirmId.do")
public class MemberConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("userid"); // 사용자 입력 아이디
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DB처리
		int check = dbPro.confirmId(id);
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		
		return "/Member/memberConfirmId.jsp"; // 해당 뷰
	}

}
 