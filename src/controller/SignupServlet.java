package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.dao.CartDAO;
import com.dao.NotificationDao;
import com.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1l;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = "";
		String url = "/signup.jsp";
		// read user info from request
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("conpassword");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String phno = req.getParameter("phno");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String pincode = req.getParameter("pincode");
		String addrs1 = req.getParameter("addressl1");
	    String addrs2 = req.getParameter("addressl2");
	    String street = req.getParameter("street");
		boolean validationPassed = true;
		// validate fields
		if (username.equals("")) {
			msg = "Username not filled in";
			url = "/signup.jsp";
			validationPassed = false;
		} else if (password.equals("")) {
			msg = "Password not filled in";
			url = "/signup.jsp";
			validationPassed = false;
		} else if (fname.equals("")) {
			msg = "First Name not filled in";
			url = "/signup.jsp";
			validationPassed = false;
		} else if (lname.equals("")) {
			msg = "Last Name not filled in";
			url = "/signup.jsp";
			validationPassed = false;
		} else if (!password.equals(cpassword)) {
			msg = "Password Does Not Match";
			url = "/signup.jsp";
			validationPassed = false;
		}

		// validate user is available or not
		if (req.getParameter("validate") == null) {
			if (validationPassed) {
				AuthDAO.enterUserName(username, password, fname, lname, email,
						phno,addrs1,addrs2,street, city, state, country, Long.parseLong(pincode),
						null, null, null, 1, AuthDAO.USER_ROLE);
				User user = AuthDAO.getUserByUserName(username);
				if (user != null) {
					NotificationDao notificationDao = new NotificationDao();
					List<User> admins = AuthDAO.retrieveUserByRole(AuthDAO.ADMIN);
					for (User admin : admins) {
						notificationDao.addNotification(
								"Authorization Required", String.valueOf(admin.getUserId()),
								String.valueOf(user.getUserId()),"Authorization");
					}
					msg = "user created successfully";
					url = "/index.jsp";
					CartDAO cartDAO = new CartDAO();
					cartDAO.creatCart(user.getUserId());
					
				}
			}
		} else {
			boolean userNameAvailable = AuthDAO.isUserNameAvailable(username);
			System.out.println("userNameAvailable " + userNameAvailable);
			if (userNameAvailable) {
				msg = "Username is available.";
				url = "/signup.jsp";

			} else {
				msg = "Username is not available.";
				username = "";
			}
		}
		// set message to request
		req.setAttribute("userName", username);
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.include(req, resp);
		try {
			// close DB connection
			AuthDAO.DB_Close();
		} catch (Throwable ex) {
			Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

}
