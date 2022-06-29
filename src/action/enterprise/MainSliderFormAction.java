package action.enterprise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.slider.SliderDBBean;
import bean.slider.SliderDataBean;

public class MainSliderFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		SliderDataBean slider = new SliderDataBean();
		SliderDBBean sliderPro = SliderDBBean.getInstance();
		slider = sliderPro.sliderSelect();
		
		request.setAttribute("slider", slider);
		
		
		
		return "/Enterprise/mainSlider.jsp";
	}

}
