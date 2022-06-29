package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.item.ItemDBBean;
import bean.item.ItemDataBean;

public class ItemInsertProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		MultipartRequest multi = null;
  
		int sizeLimit = 10 * 1024 * 1024;// 10¸Þ°¡SsSS
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
		article.setItemId(multi.getParameter("itemId"));
		article.setPrice(Integer.parseInt(multi.getParameter("price")));
		article.setAmount(Integer.parseInt(multi.getParameter("amount")));
		article.setItemstate(multi.getParameter("itemstate"));
		article.setRentalP(Integer.parseInt(multi.getParameter("rentalP")));
		article.setThumbnail(thumbnail);
		article.setDetail1(detail1);
		article.setDetail2(detail2);
		article.setDetail3(detail3);
		article.setRzipcode(multi.getParameter("rzipcode"));
		article.setRaddress(multi.getParameter("address")+"/"+multi.getParameter("raddress"));
		article.settRealpath(tRealpath);
		article.setD1Realpath(d1Realpath);
		article.setD2Realpath(d2Realpath);
		article.setD3Realpath(d3Realpath);
		article.seteId(multi.getParameter("eId"));
		article.setCategoryNo(Integer.parseInt(multi.getParameter("categoryNo")));

		ItemDBBean dbPro = ItemDBBean.getInstance();
		dbPro.iteminsert(article);
		return "/Item/iteminsertPro.jsp";

	}

}
