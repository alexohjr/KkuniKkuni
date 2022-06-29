package action.question;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;

public class QuestionSearchAction implements CommandAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*String id = (String) request.getSession().getAttribute("mId");*/
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		String search = request.getParameter("search"); // 검색 조건
		String mId = request.getParameter("mId");

		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		count = dbPro.getMemberSearchCount(search);
		
        if (count > 0) {
            articleList = dbPro.getMemberSearchArticles(startRow, endRow, mId, search); //현재 페이지에 해당하는 글 목록
        } else {
            articleList = Collections.EMPTY_LIST;
        }
        
        number = count-(currentPage-1) * pageSize; //글 목록에 표시할 글 번호
        
        
        request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
        request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
		
        return "/Question/questionList.jsp";
	}
	
}
