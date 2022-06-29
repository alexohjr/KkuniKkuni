package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.enterprise.EnterpriseDBBean;
import bean.enterprise.EnterpriseDataBean;

public class EnterpriseInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		  request.setCharacterEncoding("UTF-8");
	      EnterpriseDataBean ent = new EnterpriseDataBean();
		  
		  MultipartRequest multi = null;   

	      int sizeLimit = 100 * 1024 * 1024 ; // 100�ް��Դϴ�.

	      String realpath = request.getRealPath("/files");    // ������ ���ε�� ���� tomcat ������ WebContent ����

	      try{

	      multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy()); 

	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      String eFile = "\\files\\"+multi.getFilesystemName("eFile");
	      String eLogoFile = "\\files\\"+multi.getFilesystemName("eLogoFile");
	      String eType = "";
	      String eBankName = "";
	      
	      ent.seteId(multi.getParameter("eId"));
	      ent.setePw(multi.getParameter("ePw"));
	      ent.seteName(multi.getParameter("eName"));
	      ent.setRegisterNo(multi.getParameter("registerNo"));
	      ent.seteZipcode(multi.getParameter("zipcode"));
	      ent.seteAddress(multi.getParameter("address")+"/"+multi.getParameter("addressdt"));
	      ent.seteMail(multi.getParameter("eMail"));
	      ent.seteConfirm("wait");
	      
	      eType = multi.getParameter("eType");
	      if(eType.equals("1")) {
	    	  eType = "item";
	      } else if(eType.equals("2")) {
	    	  eType = "space";
	      }
	      
	      /*if(request.getParameter("eType").equals("1"))	{
	    	  eType = "item";
	      } else if(request.getParameter("eType").equals("2")){
	    	  eType = "space";
	      }*/
	      
	      ent.seteType(eType);
	      ent.setTmkno(multi.getParameter("tmkno"));
	      ent.setTptbName(multi.getParameter("tptbName"));
	      ent.setTptbTel(multi.getParameter("tptbTel"));
	      
	      eBankName = multi.getParameter("eBankName");
	      if(eBankName.equals("ibk")) {
	    	  eBankName = "���";
	      } else if(eBankName.equals("woori")) {
	    	  eBankName = "�츮";
	      } else if(eBankName.equals("nh")) {
	    	  eBankName = "����";
	      } else if(eBankName.equals("kb")) {
	    	  eBankName = "����";
	      } else if(eBankName.equals("sh")) {
	    	  eBankName = "����";
	      }
	  
	      ent.seteBankName(eBankName);
	      ent.seteAccountNo(multi.getParameter("eAccountNo"));
	      ent.seteDepositor(multi.getParameter("eDepositor"));
	      
	      ent.seteFile(eFile); // ����ڵ����
	      ent.seteLogo(eLogoFile); // ��ü �ΰ�
	      
	      EnterpriseDBBean dbpro = EnterpriseDBBean.getInstance();
	      dbpro.enterpriseInsert(ent);
	      
	      return "/Enterprise/enterpriseInsertPro.jsp";
	}

}
