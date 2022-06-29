package action.review;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.review.ReviewDBBean;
import bean.review.ReviewDataBean;

public class ReviewUpdateProAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		/*String rContents = request.getParameter("rContents");*/
		/*String pageNum = request.getParameter("pageNum");*/
		
		MultipartRequest multi = null;
		int sizeLimit = 100 * 1024 * 1024 ; // 100�ް��Դϴ�.

        String realpath = request.getRealPath("/files");    // ������ ���ε�� ���� tomcat ������ WebContent ����

        try{

        multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

        } catch(Exception e) {
           e.printStackTrace();
        }
		
        String rFile = multi.getFilesystemName("rFile");
		
        if(rFile == null) {
        	rFile = multi.getParameter("rFileog");
        } else {
        	rFile = "\\files\\"+rFile;
        }
        
		ReviewDataBean article = new ReviewDataBean();
		int reviewNo = Integer.parseInt(multi.getParameter("reviewNo"));
		request.setAttribute("reviewNo", reviewNo);
		
		article.setReviewNo(reviewNo);
		article.setrContents(multi.getParameter("rContents"));
		article.setrDate(new Timestamp(System.currentTimeMillis()));
		article.setrFile(rFile);
		
		ReviewDBBean dbPro = ReviewDBBean.getInstance();
		dbPro.modifyReview(article);
		
		
		/*request.setAttribute("pageNum", new Integer(pageNum));*/
		
		 return "/Review/reviewUpdatePro.jsp";
	}
}
