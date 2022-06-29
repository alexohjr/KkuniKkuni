package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class EnterpriseInsertFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		return "/Enterprise/enterpriseInsertForm.jsp";
	}

}
