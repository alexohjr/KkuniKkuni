package action.enterprise;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.notice.NoticeDBBean;
import bean.question.QuestionDBBean;

public class AdminMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

request.getParameter("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		int entnumber = 5;
		int notinumber = 5;
		int questnumber = 5;
		String search = "";
		
		// 업체 인증 게시판
		List entList = null;
		EnterpriseDBBean entdbPro = EnterpriseDBBean.getInstance(); // DB연동
		count = entdbPro.getEnterpriseListCount(); // 전체 글의 수
		if (count > 0) {
			entList = entdbPro.getEnterpriseBoardList(startRow, endRow, search); // 현재 페이지에 해당하는 글
		} else {
			entList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("entnumber", new Integer(entnumber));
		request.setAttribute("entList", entList);
		
		// 공지사항 게시판
		List noticeList = null;
		NoticeDBBean noticePro = NoticeDBBean.getInstance(); // DB연동
		count = noticePro.getArttcleCount(); // 전체 글의 수
		if (count > 0) {
			noticeList = noticePro.getBoardArcticle(startRow, endRow); // 현재 페이지에 해당하는 글
		} else {
			noticeList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("notinumber", new Integer(notinumber));
		request.setAttribute("noticeList", noticeList);
		
		//문의 게시판
		List questionList = null;
		QuestionDBBean questionPro = QuestionDBBean.getInstance();
		count = questionPro.getAdminArticleCount();
		
		if(count > 0) {
			questionList = questionPro.getAdminBoardArticles(startRow, endRow, search);
		} else {
			questionList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("questnumber", new Integer(questnumber));
		request.setAttribute("questionList", questionList);
		
		return "/Enterprise/adminMain.jsp";
	}

}
