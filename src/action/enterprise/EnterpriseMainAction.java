package action.enterprise;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;
import bean.orderlist.OrderlistDBBean;
import bean.review.ReviewDBBean;

public class EnterpriseMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
  
request.getParameter("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		String eId = request.getParameter("eId");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int rentalCount = 0;
		int noticeCount=0;
		int reviewCount=0;
		int rentalnumber = 5;
		int notinumber = 5;
		int reviewnumber = 5;
		
		//대여 현황
		List rentalList = null;
		OrderlistDBBean orderPro = OrderlistDBBean.getInstance(); // DB연동
		rentalCount = orderPro.getOrderCountent(eId); // 전체 글의 수
		if (rentalCount > 0) {
			rentalList = orderPro.getOrderListent(eId); // 현재 페이지에 해당하는 글
		} else {
			rentalList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("rentalnumber", new Integer(rentalnumber));
		request.setAttribute("rentalList", rentalList);
		request.setAttribute("rentalCount", rentalCount);
		
		//공지사항
		List noticeList = null;
		NoticeDBBean noticePro = NoticeDBBean.getInstance(); // DB연동
		noticeCount = noticePro.getNoticeCount(); // 전체 글의 수
		if (noticeCount > 0) {
			noticeList = noticePro.getNotice(); // 현재 페이지에 해당하는 글
		} else {
			noticeList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("notinumber", new Integer(notinumber));
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("noticeCount", noticeCount);
		
		//후기
		List reviewList = null;
		ReviewDBBean reviewPro = ReviewDBBean.getInstance();
		reviewCount = reviewPro.getReviewCount(eId);
		if (reviewCount> 0) {
			reviewList = reviewPro.getReviews(eId);
		}else {
			reviewList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("reviewnumber", new Integer(reviewnumber));
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("reviewCount", reviewCount);
		
		return "/Enterprise/enterpriseMain.jsp";
	}

}
