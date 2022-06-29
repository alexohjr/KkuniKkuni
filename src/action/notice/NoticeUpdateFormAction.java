package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.notice.NoticeDBBean;
import bean.notice.NoticeDataBean;

public class NoticeUpdateFormAction implements CommandAction{ //글수정 폼

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//해당 글번호
		String pageNum = request.getParameter("pageNum");//해당 페이지번호
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean article = dbPro.getArcticle(noticeNo);//패당 글번호에 대한 해당 레코드
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("noticeNo", new Integer(noticeNo));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article",article);
		System.out.println("Form");
		
		return "/Notice/noticeUpdateForm.jsp"; //해당뷰
	}

}
