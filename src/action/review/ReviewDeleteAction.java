package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.review.ReviewDBBean;

public class ReviewDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		
		ReviewDBBean dbPro = ReviewDBBean.getInstance();
		dbPro.deleteReview(reviewNo);
		
		return "/Review/reviewDelete.jsp";
	}

}
