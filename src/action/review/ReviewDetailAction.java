package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.review.ReviewDBBean;
import bean.review.ReviewDataBean;

public class ReviewDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		/*String itemId = request.getParameter("itemId");*/
		/*String pageNum = request.getParameter("pageNum");*/
		
		ReviewDBBean dbPro = ReviewDBBean.getInstance();
		ReviewDataBean article = dbPro.getReview(reviewNo);
		
		ReviewDataBean EnterpriseAnswer = null;
		
		int reviewCount = dbPro.reviewCount(reviewNo);
		if(reviewCount > 0) {
			EnterpriseAnswer = dbPro.getEnterpriseAnswer(reviewNo);
			request.setAttribute("EnterpriseAnswer", EnterpriseAnswer);
		}
		
		request.setAttribute("reviewNo", new Integer(reviewNo));
		/*request.setAttribute("pageNum", new Integer(pageNum));*/
		request.setAttribute("article", article);
		/*request.setAttribute("itemId", itemId);*/
		
		request.setAttribute("reviewCount", reviewCount);
		
		return "/Review/reviewDetail.jsp";
	}

}
