package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ItemDAOImpl;
import com.google.gson.Gson;
import com.model.Item;

/**
 * Servlet implementation class SellerItemsServlet
 */
@WebServlet("/SellerItemsServlet")
public class SellerItemsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerItemsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param request
	 * @param response
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String get = request.getParameter("getItemByName");
		String seller = request.getParameter("seller");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (get != null && get.equals("getItemByName")) {
			String keyword = request.getParameter("keyword");
			if (null != keyword && !keyword.trim().isEmpty()) {
				if ("*".equals(keyword)) {
					keyword = "";
				}
				Collection<Item> itemsLikeName = new ItemDAOImpl()
						.getItemsLikeName(keyword);
				String json = gson.toJson(itemsLikeName);
				response.getWriter().write(json);
				return;
			}

		} else if (seller != null) {
			List<Item> items = new ItemDAOImpl().getItemBySeller(Integer
					.parseInt(seller));
			String json = gson.toJson(items);
			response.getWriter().write(json);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
