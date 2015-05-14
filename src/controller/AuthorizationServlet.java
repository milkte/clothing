package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AuthorizationServlet
 */
@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorizationServlet() {
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
		String get = request.getParameter("getSellers");
		String approve = request.getParameter("approve");
		String decline = request.getParameter("decline");
		String forList = request.getParameter("forList");
		User   user = (User) request.getSession().getAttribute("user");
		
		if (null != get) {
			user = AuthDAO.getUserByUserName(user.getUserName());
			if(forList!=null && user.getRole().equals(AuthDAO.ADMIN)){
				List<User> sellers = AuthDAO
						.retrieveUserByRole(AuthDAO.SELLER_ROLE);
				String json = new Gson().toJson(sellers);
				response.getWriter().write(json);
			}else if(forList!=null && !user.getRole().equals(AuthDAO.ADMIN)){
				return;
			}
			else{
				List<User> sellers = AuthDAO
						.retrieveUserByRole(AuthDAO.SELLER_ROLE);
				String json = new Gson().toJson(sellers);
				response.getWriter().write(json);
			}
			
			return;
			
		} else if (null != approve) {
			String userId = request.getParameter("userId");
			Seller seller = AuthDAO.getSellerById(Integer.parseInt(userId));
			seller.setLocked(false);
			AuthDAO.updateSellerStatus(seller);
			return;
		} else if (null != decline) {
			String userId = request.getParameter("userId");
			Seller seller = AuthDAO.getSellerById(Integer.parseInt(userId));
			seller.setLocked(true);
			AuthDAO.updateSellerStatus(seller);
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
