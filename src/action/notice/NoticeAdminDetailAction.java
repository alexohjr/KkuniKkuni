package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;
import bean.notice.NoticeDataBean;

public class NoticeAdminDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//해당 글번호
		String pageNum = request.getParameter("pageNum");//해당 페이지번호
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean article = dbPro.getArcticle(noticeNo);//패당 글번호에 대한 해당 레코드
		String nSort = null;
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("noticeNo", new Integer(noticeNo));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article",article);
		if(article.getnSort().equals("e")) {
			nSort = "관리공지";
		}else {
			nSort = "일반공지";
		}
		request.setAttribute("nSort", nSort);
		
		
		
		return "/Notice/noticeAdminDetail.jsp";
	}

}
