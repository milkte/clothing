package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryDao;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/updateCategory")
public class UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 CategoryDao categoryDao = new CategoryDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String msg = "Category Added successfully.";
	        String url = "/updateCategory.jsp";
	        // read user info from request
	        String name = request.getParameter("name");
	      
	        String description = request.getParameter("description");
	        String parentCategory = request.getParameter("parentCategory");
//	        	categoryDao.addCategory(name, description, parentCategory);
	         RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.include(request, response);
	}

}
