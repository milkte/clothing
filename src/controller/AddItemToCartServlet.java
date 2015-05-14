package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDAO;
import com.model.User;

/**
 * Servlet implementation class AddItemToCartServlet
 */
@WebServlet("/AddItemToCartServlet")
public class AddItemToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItemToCartServlet() {
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
			HttpServletResponse response) throws ServletException, IOException {
		String url="index.jsp";
		String itemId = request.getParameter("itemId");
		String colorId = request.getParameter("colorId");
		String sizeId = request.getParameter("sizeId");
		User loggedInUser = (User) request.getSession().getAttribute("user");
		if (null != loggedInUser) {			
			CartDAO cartDAO = new CartDAO();
			cartDAO.addItemToCart(Integer.valueOf(itemId),Integer.valueOf(sizeId),Integer.valueOf(colorId), loggedInUser.getUserId());
		}else{
			url="login.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
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
