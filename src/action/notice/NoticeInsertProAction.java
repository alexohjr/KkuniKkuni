package action.notice;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.notice.NoticeDBBean;
import bean.notice.NoticeDataBean;

public class NoticeInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		MultipartRequest multi = null;	

		int sizeLimit = 100 * 1024 * 1024 ; // 100메가입니다.

		String realpath = request.getRealPath("/files");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

		try{

		multi=new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

		 }catch (Exception e) {

			e.printStackTrace();

		 } 
		String nFile = multi.getFilesystemName("nFile");
		String nRealpath = "\\files\\" + nFile;
		System.out.println(nFile);
		System.out.println(nRealpath);
		
	    String nSort = "";
	    if (multi.getParameter("selecter").equals("1")) {
	    	nSort = "e";
	    } else {
	    	nSort = "m";
	    }
		NoticeDataBean article= new NoticeDataBean();		
		article.setnSort(nSort);
		article.setnTitle(multi.getParameter("nTitle"));
		article.setnDate(new Timestamp(System.currentTimeMillis()));
		article.setViewCount(0);
		article.setnFile(nFile);
		article.setnContents(multi.getParameter("nContents"));
		article.setnRealpath(nRealpath);
		
		
		/*article.setnSort(nSort);
		article.setnTitle(request.getParameter("nTitle"));
		article.setnDate(new Timestamp(System.currentTimeMillis()));
		article.setViewCount(0);
		//article.setnFile(request.getParameter("nFile"));
		article.setnContents(request.getParameter("nContents"));
		article.setnRealpath(request.getParameter(nRealpath));*/
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.insertArticle(article);
		
		return "/Notice/noticeInsertPro.jsp";
	}

}
