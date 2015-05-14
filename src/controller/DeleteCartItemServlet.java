package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDAO;
import com.model.Cart;
import com.model.User;

/**
 * Servlet implementation class DeleteCartItemServlet
 */
@WebServlet("/DeleteCartItemServlet")
public class DeleteCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCartItemServlet() {
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
		String itemId = request.getParameter("itemId");
		User loggedInUser = (User) request.getSession().getAttribute("user");
		if (null != loggedInUser) {
			CartDAO cartDAO = new CartDAO();
			Cart cart = cartDAO.getCart(loggedInUser.getUserId());
			int flag = cartDAO.deleteCartItem(cart.getCartId(),
					Integer.parseInt(itemId));
			response.getWriter().write(flag);
		}
		return;
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
