package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.enterprise.EnterpriseDataBean;

public class EnterpriseModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("UTF-8");
		
		MultipartRequest multi = null;   

	     int sizeLimit = 100 * 1024 * 1024 ; // 100메가입니다.

	     String realpath = request.getRealPath("/files");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

	     try{

	     multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

	     } catch(Exception e) {
	        e.printStackTrace();
	     } 
	     EnterpriseDataBean ent = new EnterpriseDataBean();
	      
	    String eLogoFile = multi.getFilesystemName("eLogoFile");
	     
	    if(eLogoFile == null) { 
	    	eLogoFile = multi.getParameter("eLogoFileOG");
	    } else if(eLogoFile != null) {
	    	eLogoFile = "\\files\\"+eLogoFile;
	    }
		
		ent.seteId(multi.getParameter("eId"));
		ent.setePw(multi.getParameter("ePw"));
		ent.seteZipcode(multi.getParameter("zipcode"));
		ent.seteAddress(multi.getParameter("address")+"/"+multi.getParameter("addressdt"));
		
		String etype = multi.getParameter("eType");
		
		if(etype.equals("1")) {
			etype = "item";
		} else if(etype.equals("2")) {
			etype = "space";
		}
		ent.seteType(etype); 
		ent.setTptbName(multi.getParameter("tptbName"));
		ent.setTptbTel(multi.getParameter("tptbTel"));
		ent.seteAccountNo(multi.getParameter("eAccountNo"));
		
		String eBankName = multi.getParameter("eBankName");
		
		if(eBankName.equals("ibk")) {
			eBankName = "기업";
		} else if(eBankName.equals("woori")) {
			eBankName = "우리";
		} else if(eBankName.equals("nh")) {
			eBankName = "농협";
		} else if(eBankName.equals("kb")) {
			eBankName = "국민";
		} else if(eBankName.equals("sh")) {
			eBankName = "신한";
		}
		
		ent.seteBankName(eBankName);
		ent.seteDepositor(multi.getParameter("eDepositor"));
		ent.seteLogo(eLogoFile);
		
		
		
		EnterpriseDBBean dbPro = EnterpriseDBBean.getInstance();
		dbPro.updateMember(ent);
		
		
		
		return "/Enterprise/enterpriseModifyPro.jsp"; // 해당 뷰

	
	}

}
