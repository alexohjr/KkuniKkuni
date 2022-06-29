package action.notice;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.notice.NoticeDBBean;
import bean.notice.NoticeDataBean;

public class NoticeUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		MultipartRequest multi = null;	

		int sizeLimit = 10 * 1024 * 1024 ; // 10메가입니다.

		String nRealpath = request.getRealPath("/files");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

		try{

		multi=new MultipartRequest(request, nRealpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

		 }catch (Exception e) {

			e.printStackTrace();

		 } 
		String nFile = multi.getFilesystemName("nFile");
		
		if(nFile == null) {
			nFile = multi.getParameter("nFileog");
			nRealpath = multi.getParameter("nRealpathog");
		} else if(nFile != null) {
			nRealpath = "\\files\\"+nFile;
		}
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//해당 글번호
		String nSort = "";    
		System.out.println(multi.getParameter("selecter"));
	    if (multi.getParameter("selecter").equals("1")) {
	    	nSort = "e";
	    } else {
	    	nSort = "m";
	    }
	    System.out.println(nSort);
		NoticeDataBean article = new NoticeDataBean();
		article.setNoticeNo(noticeNo);
		article.setnSort(nSort);
		article.setnTitle(multi.getParameter("nTitle"));
		article.setnDate(new Timestamp(System.currentTimeMillis()));
		article.setViewCount(0);
		article.setnRealpath(nRealpath);
		article.setnFile(nFile);
		article.setnContents(multi.getParameter("nContents"));
		
		System.out.println("Pro");
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.updateArticle(article);
		
		return "/Notice/noticeUpdatePro.jsp";
	}

}
