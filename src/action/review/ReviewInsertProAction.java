package action.review;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.review.ReviewDBBean;
import bean.review.ReviewDataBean;

public class ReviewInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		ReviewDataBean article = new ReviewDataBean();
		
		MultipartRequest multi = null;   

        int sizeLimit = 100 * 1024 * 1024 ; // 100메가입니다.

        String realpath = request.getRealPath("/files");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

        try{

        multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

        } catch(Exception e) {
           e.printStackTrace();
        }
		
        String rFile = "\\files\\"+multi.getFilesystemName("rFile");
        
		article.setrContents(multi.getParameter("rContents"));
		article.setrDate(new Timestamp(System.currentTimeMillis()));
		article.setrFile(rFile);
		article.setGrade("5");
		article.setmId(multi.getParameter("mId"));
		article.setItemNo(Integer.parseInt(multi.getParameter("itemNo")));
		
		ReviewDBBean dbPro = ReviewDBBean.getInstance();
		dbPro.insertReview(article);
		
        
		return "/Review/reviewInsertPro.jsp";
	}

}
