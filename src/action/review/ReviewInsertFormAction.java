package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ReviewInsertFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		
		System.out.println("리뷰인서트폼액션 아이템넘인트::" + itemNo);
		/*try {
		
			if(request.getParameter("reviewNo")!=null) {
				reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
				request.setAttribute("reviewNo", new Integer("reviewNo"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
		String mId = request.getParameter("mId");
		
		System.out.println("리뷰인서트폼액션::" + mId);
		
		request.setAttribute("mId", mId);
		request.setAttribute("itemNo", itemNo);
		
		return "/Review/reviewInsertForm.jsp";
	}

}
