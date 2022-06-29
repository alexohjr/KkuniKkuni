package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;

public class NoticeDeleteAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.deleteArticle(noticeNo);
		
		return "/Notice/noticeDelete.jsp";
	}

}
