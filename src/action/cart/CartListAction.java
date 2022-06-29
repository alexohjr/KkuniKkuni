package action.cart;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.cart.CartDBBean;
import bean.item.ItemDBBean;

public class CartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		List cartList = null;
		int cartcount = 0;
		
		String mId = request.getParameter("mId");
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ
		
		/*int itemNo = Integer.parseInt(request.getParameter("itemNo"));*/

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 8; // ���������� ���� ����
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // �� �������� ���۱� ��ȣ
		int end = currentPage * pageSize; // �� �������� ������ �۹�ȣ
		int count = 0;
		int number = 0;

		CartDBBean dbPro = CartDBBean.getInstance(); // DB����
		count = dbPro.getCartCount(mId); // ���ɻ�ǰ ����
		
		if (count > 0) {
			// ���� �������� �ش��ϴ� ��
			cartList = dbPro.getCartlist(start, end, mId); 
		} else {
			cartList = Collections.EMPTY_LIST;
		}

		number = count - (currentPage - 1) * pageSize; // �۸�Ͽ� ǥ���� �� ��ȣ
		// �ش� �信�� ����� �Ӽ�
		request.setAttribute("cartcount", cartcount);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("start", new Integer(start));
		request.setAttribute("end", new Integer(end));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("cartList", cartList);

		return "/cart/cartList.jsp";
	}

}
