package action.member;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.member.MemberDBBean;
import bean.member.MemberDataBean;

public class MemberModifyFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		 
		String mId = request.getParameter("mId");

		
		MemberDBBean dbpro = MemberDBBean.getInstance();
		MemberDataBean member = dbpro.getMember(mId);
		
		String address = member.getmAddress();

		int idx = address.indexOf("/");
		String address1 = address.substring(0, idx);
		String address2 = address.substring(idx+1);
		request.setAttribute("address1", address1);
		request.setAttribute("address2", address2);
		
		/*StringTokenizer st = new StringTokenizer(address, "/");
		while(st.hasMoreTokens()) {
			String address1 = st.nextToken();
			String address2 = st.nextToken();
			request.setAttribute("address1", address1);
			request.setAttribute("address2", address2);
		}*/
		
		String birthday = member.getBirthday();
		String birthRe = birthday.substring(0, 10);
		request.setAttribute("birthday", birthRe);
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("member", member);
		
		
		return "/Member/memberModifyForm.jsp"; // 해당 뷰
		
	}


}