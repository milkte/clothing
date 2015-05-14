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
import com.google.gson.Gson;
import com.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class SellerSignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;
    Gson gson=new Gson();
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
        
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");  
        String company = req.getParameter("company");
        String accno = req.getParameter("account");
        String routingno = req.getParameter("routing");
        String email = req.getParameter("email");
        String phno = req.getParameter("phno");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        System.out.print(country);
        String pincode = req.getParameter("pincode");
        String addrs1 = req.getParameter("addressl1");
        String addrs2 = req.getParameter("addressl2");
        String street = req.getParameter("street");

		// validate user is available or not
          if (req.getParameter("validate") == null) {
                AuthDAO.enterUserName(username, password,fname,lname,email,phno,addrs1,addrs2,street,city,state,country,Long.parseLong(pincode),company,accno,routingno,0,AuthDAO.SELLER_ROLE);
                User user = AuthDAO.getUserByUserName(username);
                if (user != null) {
                	NotificationDao notificationDao = new NotificationDao();
					List<User> admins = AuthDAO.retrieveUserByRole(AuthDAO.ADMIN);
					for (User admin : admins) {
						notificationDao.addNotification(
								"Authorization Required", String.valueOf(admin.getUserId()),
								String.valueOf(user.getUserId()),"Authorization");
					}
					CartDAO cartDAO = new CartDAO();
					cartDAO.creatCart(user.getUserId());
					msg = "user created successfully";
					
                    url = "/index.jsp";
                }
        } else {
            boolean userNameAvailable = AuthDAO.isUserNameAvailable(username);
          System.out.println(userNameAvailable);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(String.valueOf(userNameAvailable));
           return;
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
