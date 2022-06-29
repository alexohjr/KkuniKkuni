package action.enterprise;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.enterprise.EnterpriseDataBean;

public class EnterpriseModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String id = request.getParameter("eId");

		
		EnterpriseDBBean dbpro = EnterpriseDBBean.getInstance();
		EnterpriseDataBean ent = dbpro.getMember(id);
		
		String address = ent.geteAddress();
		String mail = ent.geteMail();
		
		int idx = address.indexOf("/");
		String address1 = address.substring(0, idx);
		String address2 = address.substring(idx+1);
		request.setAttribute("address1", address1);
		request.setAttribute("address2", address2);
		
		System.out.println("모디파이폼액션 이메일:::" + mail);
		System.out.println("모디파이폼액션 어드레스::" + address);
		
		/*StringTokenizer st = new StringTokenizer(address, "/");
		while(st.hasMoreTokens()) {
			String address1 = st.nextToken();
			String address2 = st.nextToken();
			request.setAttribute("address1", address1);
			request.setAttribute("address2", address2);
			System.out.println("어드레스2::" + address2);
		}*/
		
		request.setAttribute("ent", ent);
		
		return "/Enterprise/enterpriseModifyForm.jsp";
	}

}
