package action.member;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;

public class MemberMailCheckAction implements CommandAction {
	
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mail = request.getParameter("email"); // 사용자 입력 이메일
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DB처리
		int check = dbPro.confirmMail(mail);
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("mail", mail);
		request.setAttribute("check", check);
		
		return "/Member/memberMailCheck.jsp"; // 해당 뷰
	}
}
