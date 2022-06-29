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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum");//�ش� ��������ȣ
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean article = dbPro.getArcticle(noticeNo);//�д� �۹�ȣ�� ���� �ش� ���ڵ�
		String nSort = null;
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("noticeNo", new Integer(noticeNo));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article",article);
		if(article.getnSort().equals("e")) {
			nSort = "��������";
		}else {
			nSort = "�Ϲݰ���";
		}
		request.setAttribute("nSort", nSort);
		
		
		
		return "/Notice/noticeAdminDetail.jsp";
	}

}
