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
		
		String id = request.getParameter("userid"); // ����� �Է� ���̵�
		
		MemberDBBean dbPro = MemberDBBean.getInstance(); // DBó��
		int check = dbPro.confirmId(id);
		
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		
		return "/Member/memberConfirmId.jsp"; // �ش� ��
	}

}
 