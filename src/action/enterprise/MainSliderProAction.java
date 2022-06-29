package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import bean.slider.SliderDBBean;
import bean.slider.SliderDataBean;

public class MainSliderProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		MultipartRequest multi = null;

		int sizeLimit = 100 * 1024 * 1024; // 100메가입니다.

		String realpath = request.getRealPath("\\files\\"); // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준

		try {

			multi = new MultipartRequest(request, realpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String slider1 = multi.getFilesystemName("slider1");
		String slider2 = multi.getFilesystemName("slider2");
		String slider3 = multi.getFilesystemName("slider3");
		String slider4 = multi.getFilesystemName("slider4");
		String slider5 = multi.getFilesystemName("slider5");
		 
		System.out.println(slider1 + slider2 + slider3);
		SliderDataBean slider = new SliderDataBean();
		
		if (slider1 == null) {
			slider.setIntro1(multi.getParameter("intro1og"));
		} else {
			String intro1 = "\\files\\" + slider1;
			slider.setIntro1(intro1);
		}
		
		if (slider2 == null) {
			slider.setIntro2(multi.getParameter("intro2og"));
		} else {
			String intro2 = "\\files\\" + slider2;
			slider.setIntro2(intro2);
		}
		
		if (slider3 == null) {
			slider.setIntro3(multi.getParameter("intro3og"));
		} else {
			String intro3 = "\\files\\" + slider3;
			slider.setIntro3(intro3);
		}
		
		if (slider4 == null) {
			slider.setIntro4(multi.getParameter("intro4og"));
		} else {
			String intro4 = "\\files\\" + slider4;
			slider.setIntro4(intro4);
		}
		
		if (slider5 == null) {
			slider.setIntro5(multi.getParameter("intro5og"));
		} else {
			String intro5 = "\\files\\" + slider5;
			slider.setIntro5(intro5);
		}

		SliderDBBean sliderPro = SliderDBBean.getInstance();
		sliderPro.sliderUpdate(slider);
		  

		return "/Enterprise/adminMain.jsp";
	}

}
