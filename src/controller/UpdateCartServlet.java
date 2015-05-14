package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDAO;
import com.model.Cart;
import com.model.CartItem;
import com.model.User;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCartServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User loggedInUser = (User) request.getSession().getAttribute("user");
		if (null != loggedInUser) {
			String itemId = request.getParameter("itemId");
			String quantity = request.getParameter("quantity");
			CartDAO cartDAO = new CartDAO();
			Cart cart =cartDAO.getCart(loggedInUser.getUserId());
			int flag = cartDAO.updateQuantity(cart.getCartId(),
					Integer.parseInt(itemId), Integer.parseInt(quantity));
			 cart = cartDAO.getCart(loggedInUser.getUserId());
			int itemCount = 0;

			if (null != cart) {
				itemCount= cart.getItems().size();
			}
			int[] arr = new int[2];
			arr[0] = flag;
			arr[1] = itemCount;
			response.getWriter().write(flag);
			response.getWriter().write(itemCount);
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
