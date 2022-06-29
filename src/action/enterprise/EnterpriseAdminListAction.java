package action.enterprise;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;

public class EnterpriseAdminListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		String search = request.getParameter("search"); // 검색 내용
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		
		List articleList = null;
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance(); // DB연동
		if(search == null) {
			count = dbPro.getEnterpriseListCount(); // 전체 글의 수
		} else {
			count = dbPro.getEnterpriseListSearchCount(search);
		} 
		
		if (count > 0) {
			articleList = dbPro.getEnterpriseList(startRow, endRow, search); // 현재 페이지에 해당하는 글
		} else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize; // 글목록에 표시할 글 번호
		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("search", search);
		
		return "/Enterprise/enterpriseAdminList.jsp"; // 해당 뷰
	}

}
