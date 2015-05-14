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
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryDao categoryDao = new CategoryDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "<h5 style=\"color:green\">Category Added successfully.</h5>";
        String url = "/addCategory.jsp";
        // read user info from request
        
        String name = request.getParameter("name");
        System.out.println("name" + name + "g");
        if (name == null || name.trim().equals("")) {
            msg = "<h5 style=\"color:red\">Could not add category. Categoryname is null</h5>";
        } else {
            System.out.println("here");
            String description = request.getParameter("description");
            String parentCategory = request.getParameter("parentCategory");
            int noOfStyles = Integer.parseInt(request.getParameter("noOfStyles"));
            System.out.println("no of styles= " + noOfStyles);
            String[] styleNames=new String[noOfStyles+1];
            for (int i = 0; i <= noOfStyles; i++) {
                styleNames[i]=request.getParameter("styleName_"+i);
                System.out.println("styleNames[i] " + styleNames[i]);
            }
            categoryDao.addCategory(name, description, parentCategory,styleNames);

        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        System.out.println("dispatcher " + dispatcher + " " + request + " " + response);
        dispatcher.include(request, response);
    }

}
