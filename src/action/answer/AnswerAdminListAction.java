package action.answer;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.question.QuestionDBBean;


public class AnswerAdminListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		//pageNum이 parameter값으로 넘어오지 않았다면 페이지 번호는 1
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		QuestionDBBean dbPro = QuestionDBBean.getInstance();
		
		if(search == null) {
			count = dbPro.getAdminArticleCount();
		} else {
			count = dbPro.getAdminSearchCount(search);
		}
		
		if(count > 0) {
			articleList = dbPro.getAdminSearchArticles(startRow, endRow, search);
		} else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count-(currentPage -1)*pageSize;
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("search", search);
		
		return "/Answer/answerAdminList.jsp";
	}

}
