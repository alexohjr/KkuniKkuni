package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;

public class ItemUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		MultipartRequest multi = null;
		
		int sizeLimit = 10 * 1024 * 1024; // 10¸Þ°¡
		String realpath = request.getRealPath("/files");

		try {
			multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String thumbnail = multi.getFilesystemName("thumbnail");
		String detail1 = multi.getFilesystemName("detail1");
		String detail2 = multi.getFilesystemName("detail2");
		String detail3 = multi.getFilesystemName("detail3");

		String tRealpath = "\\files\\" + thumbnail;
		String d1Realpath = "\\files\\" + detail1;
		String d2Realpath = "\\files\\" + detail2;
		String d3Realpath = "\\files\\" + detail3;

		ItemDataBean article = new ItemDataBean();
		
		article.setItemNo(multi.getParameter("itemNo"));
		article.setItemId(multi.getParameter("itemId"));
		article.setPrice(Integer.parseInt(multi.getParameter("price")));
		article.setAmount(Integer.parseInt(multi.getParameter("amount")));
		article.setItemstate(multi.getParameter("itemstate"));
		article.setRentalP(Integer.parseInt(multi.getParameter("rentalP")));
		
		if(thumbnail == null) {
			article.setThumbnail(multi.getParameter("thumbnailo"));
			article.settRealpath(multi.getParameter("tRealpath"));
		} else if (thumbnail != null) {
			article.setThumbnail(thumbnail);
			article.settRealpath(tRealpath);
		}
		
		if(detail1 == null) {
			article.setDetail1(multi.getParameter("detail1o"));
			article.setD1Realpath(multi.getParameter("d1Realpath"));
		} else if (detail1 != null) {
			article.setDetail1(detail1);
			article.setD1Realpath(d1Realpath);
		}
		
		if(detail2 == null) {
			article.setDetail2(multi.getParameter("detail2o"));
			article.setD2Realpath(multi.getParameter("d2Realpath"));
		} else if (detail2 != null) {
			article.setDetail2(detail2);
			article.setD2Realpath(d2Realpath);
		}
		
		if(detail3 == null) {
			article.setDetail3(multi.getParameter("detail3o"));
			article.setD3Realpath(multi.getParameter("d3Realpath"));
		} else if (detail3 != null) {
			article.setDetail3(detail3);
			article.setD3Realpath(d3Realpath);
		}
		
		article.setRzipcode(multi.getParameter("rzipcode"));
		article.setRaddress(multi.getParameter("address")+"/"+multi.getParameter("raddress"));
		
		
		article.seteId(multi.getParameter("eId"));
		article.setCategoryNo(Integer.parseInt(multi.getParameter("categoryNo")));

		ItemDBBean dbPro = ItemDBBean.getInstance();
		
		dbPro.itemUpdate(article);
		request.setAttribute("pageNum", pageNum);
		
		return "/Item/itemUpdatePro.jsp";
	}

}
