package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

/**
 * Servlet implementation class LoginServlet
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
System.out.println("in logout");

	// set loggedIn value false
	req.setAttribute("loggedIn",null);

        // remove user from session
        session.setAttribute("username",null);
        session.setAttribute("password",null);
        session.setAttribute("loggedIn",null);
        session.setAttribute("role",null);
        session.setAttribute("userid",null);
        
        req.setAttribute("msg", "");
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    
    }

}
