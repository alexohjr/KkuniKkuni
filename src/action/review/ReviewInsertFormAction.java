package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ReviewInsertFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		
		System.out.println("�����μ�Ʈ���׼� �����۳���Ʈ::" + itemNo);
		/*try {
		
			if(request.getParameter("reviewNo")!=null) {
				reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
				request.setAttribute("reviewNo", new Integer("reviewNo"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
		String mId = request.getParameter("mId");
		
		System.out.println("�����μ�Ʈ���׼�::" + mId);
		
		request.setAttribute("mId", mId);
		request.setAttribute("itemNo", itemNo);
		
		return "/Review/reviewInsertForm.jsp";
	}

}
