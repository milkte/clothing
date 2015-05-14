package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.dao.CategoryDao;
import com.dao.ItemDAOImpl;
import com.google.gson.Gson;
import com.model.Category;
import com.model.Color;
import com.model.Item;
import com.model.Size;
import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * Servlet implementation class LoginServlet
 */
public class CategoriesServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;
    CategoryDao categoryDao = new CategoryDao();
    ItemDAOImpl itemDAO = new ItemDAOImpl();	
    Gson gson=new Gson();
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,
            java.io.IOException {
    	
    	processRequest(request,response);
       /* String msg = "";
        String url = "/categories.jsp";
        // read user info from request
        String parentCategoryName = request.getParameter("parentCategory");
        String get = request.getParameter("get");
        
        List<Category> categories = null;
        if(get!=null && get.equals("categories")){
            categories=categoryDao.getAllCategories();
			String json = gson.toJson(categories);
			System.out.println("json= " + json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
            return;
        }
        if (parentCategoryName == null) {
            categories = categoryDao.getRootCategories();
        } else {
        	categories=categoryDao.getCategories(parentCategoryName);
        }
        if (categories != null) {
            for (Category category : categories) {
                System.out.println("category: " + category);
            }

        } else {
            msg = "<h5 style=\"color:red\">No sub categories found</h5>";
        }
        Object color = getServletContext().getAttribute("color");
        Object size = getServletContext().getAttribute("size");
        if(color==null){
            List<Color> allColors = new ItemDAOImpl().getAllColors();
            getServletContext().setAttribute("color",allColors);
        }
        if(size==null){
            List<Size> allSizes = new ItemDAOImpl().getAllSizes();
            getServletContext().setAttribute("size", allSizes);
        }
        List<Item> items= itemDAO.getItemsByCategory(Integer.parseInt(parentCategoryName));
        // set message to request
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        request.setAttribute("categories", categories);
        request.setAttribute("items", items);
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);
        try {
            // close DB connection
            AuthDAO.DB_Close();
        } catch (Throwable ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
*/
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(request, resp);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 // read user info from request
         String parentCategoryName = request.getParameter("parentCategory");
         String get = request.getParameter("get");
         
         List<Category> categories = null;
         PrintWriter writer = response.getWriter();
		if(get!=null && get.equals("categories")){
             categories=categoryDao.getAllCategories();
 			String json = gson.toJson(categories);
 			response.setContentType("application/json");
 			response.setCharacterEncoding("UTF-8");
 			writer.write(json);
           return;
         }else{
        	 if (parentCategoryName == null) {
                 categories = categoryDao.getRootCategories();
             } else {
             	categories=categoryDao.getCategories(parentCategoryName);
             }
			List<Color> allColors = new ItemDAOImpl().getAllColors();
			List<Size> allSizes = new ItemDAOImpl().getAllSizes();
			List<Item> items = itemDAO.getItemsByCategory(Integer
					.parseInt(parentCategoryName));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Object[]objects = new Object[4];
			objects[0]=allColors;
			objects[1]=allSizes;
			objects[2]=categories;
			objects[3]=items;
			
			writer.write(gson.toJson(objects));
			  try {
		            // close DB connection
		            AuthDAO.DB_Close();
		            
		        } catch (Throwable ex) {
		            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
		                    null, ex);
		        }
			return;			
         }
    	
	}
}
