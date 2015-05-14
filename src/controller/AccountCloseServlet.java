package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.google.gson.Gson;
import com.model.Seller;
import com.model.User;

/**
 * Servlet implementation class AccountCloseServlet
 */
@WebServlet("/AccountCloseServlet")
public class AccountCloseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountCloseServlet() {
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
		System.out.println("Inside closing account");
		
		User user = (User) request.getSession().getAttribute("user");
		if (AuthDAO.SELLER_ROLE.equals(user.getRole())) {
			System.out.println("user is seller");
			Seller seller = (Seller) user;
			seller.setClosed(true);
			int flag=AuthDAO.updateUser(user);
			System.out.println("user account is closed");

			// set loggedIn value false
			request.setAttribute("loggedIn", null);

			// remove user from session
			request.getSession().setAttribute("username", null);
			request.getSession().setAttribute("password", null);
			request.getSession().setAttribute("loggedIn", null);
			request.getSession().setAttribute("role", null);
			request.getSession().setAttribute("user", null);
			request.getSession().invalidate();
			request.getSession().setAttribute("userid", null);			
			response.getWriter().write(String.valueOf(flag));
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
