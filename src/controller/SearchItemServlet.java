package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.dao.ItemDAOImpl;
import com.google.gson.Gson;
import com.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class LoginServlet
 */
public class SearchItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            java.io.IOException {
        String msg = "";
        String url = "/search.jsp";
        // read user info from request
        String itemId = request.getParameter("item");
        String iscatstr = request.getParameter("iscat");
        boolean iscat = Boolean.parseBoolean(iscatstr);
        String filter = request.getParameter("filter");
        Item item = null;
        if (itemId != null && !itemId.isEmpty()) {
        	
            item = itemDAO.getItemById(Integer.parseInt(itemId));
			if (!iscat) {
				String json = new Gson().toJson(item);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				return;
			}else{
				List<Item> searchItems = new ArrayList<Item>();
				searchItems.add(item);
				request.setAttribute("iscat", false);
				request.setAttribute("items", searchItems);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/search.jsp");
				dispatcher.forward(request, response);
			}
        } else if (filter != null) {
            String colors[] = request.getParameterValues("color");
            String sizes[] = request.getParameterValues("size");
            StringBuilder result = new StringBuilder();
            if (colors != null && colors.length > 0) {
                for (int i = 0; i < colors.length - 1; i++) {
                    result.append(colors[i]);
                    result.append(",");
                }
                result.append(colors[colors.length - 1]);
            }
            String colorparams = result.toString();
            if (sizes != null && sizes.length > 0) {
                result = new StringBuilder();
                for (int i = 0; i < sizes.length - 1; i++) {
                    result.append(sizes[i]);
                    result.append(",");
                }
                result.append(sizes[sizes.length - 1]);
            }
			String sizeParams = result.toString();
			 List<Item> searchItems = itemDAO.searchItems(colorparams.trim(),
					sizeParams.trim());
			request.setAttribute("items", searchItems);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/search.jsp");
			dispatcher.forward(request, response);
      
        }
        // set message to request
        try {
            // close DB connection
            AuthDAO.DB_Close();
        } catch (Throwable ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemId = request.getParameter("item");
        Item item = null;
        if (itemId != null && !itemId.isEmpty()) {
            item = itemDAO.getItemById(Integer.parseInt(itemId));
            String json = new Gson().toJson(item);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
    }

}
