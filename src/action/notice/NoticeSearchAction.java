package action.notice;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;

public class NoticeSearchAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search"); // 검색 내용
		String pageNum = request.getParameter("pageNum"); // 페이지 번호

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List articleList = null;
		NoticeDBBean dbPro = NoticeDBBean.getInstance(); // DB연동
		count = dbPro.getArttcleCount(); // 전체 글의 수

		if (count > 0) {
			articleList = dbPro.getArcticle(start,end,search); // 현재 페이지에 해당하는 글
		} else {
			articleList = Collections.EMPTY_LIST;
		}

		number = count - (currentPage - 1) * pageSize; // 글목록에 표시할 글 번호
		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(start));
		request.setAttribute("endRow", new Integer(end));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "/Notice/noticeList.jsp"; // 해당 뷰

	}

}
