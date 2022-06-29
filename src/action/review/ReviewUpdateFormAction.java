package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.review.ReviewDBBean;
import bean.review.ReviewDataBean;

public class ReviewUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String mId = request.getParameter("mId");
		String tRealpath = request.getParameter("tRealpath");
		String itemId = request.getParameter("itemId");
		
		/*String pageNum = request.getParameter("pageNum");*/
		
		ReviewDBBean dbPro = ReviewDBBean.getInstance();
		ReviewDataBean article = dbPro.updateGetArticle(reviewNo);
		
		/*request.setAttribute("pageNum", new Integer(pageNum));*/
		
		request.setAttribute("article", article);
		request.setAttribute("tRealpath", tRealpath);
		request.setAttribute("itemId", itemId);
		
		return "/Review/reviewUpdateForm.jsp";
	}

}
