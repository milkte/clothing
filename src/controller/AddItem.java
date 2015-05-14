package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.dao.ItemDAOImpl;
import com.google.gson.Gson;
import com.model.Color;
import com.model.Size;
import com.model.Style;
import com.model.User;

import java.util.List;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("Ingetitem");
     
        String selectedcategory = request.getParameter("selectedcategory");
        String get = request.getParameter("get");
        if (selectedcategory != null) {
            List<Style> styleByCategory = itemDAO.getStyleByCategory(Integer.parseInt(selectedcategory));
            System.out.println("styles: " + styleByCategory);
            request.setAttribute("styles", styleByCategory);
            String json = new Gson().toJson(styleByCategory);
            System.out.println("json= " + json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
        else if (get != null && get.equals("colors")) {
            List<Color> allColors = itemDAO.getAllColors();
            String json = new Gson().toJson(allColors);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
        else if (get != null && get.equals("sizes")) {
            List<Size> allSizes = itemDAO.getAllSizes();
            String json = new Gson().toJson(allSizes);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        List<Color> allColors = itemDAO.getAllColors();
        List<Size> allSizes = itemDAO.getAllSizes();

        request.setAttribute("colors", allColors);
        request.setAttribute("sizes", allSizes);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/addItem.jsp");
        System.out.println("dispatcher " + dispatcher + " " + request + " " + response);
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   String username = (String) request.getSession().getAttribute("username");
           String password =  (String) request.getSession().getAttribute("password");

           User user=AuthDAO.getUserByUserNamePassword(username, password);
        String msg = "<h5 style=\"color:green\">Item Added successfully.</h5>";
        String url = "/addItem.jsp";
        // read user info from request
        String name = request.getParameter("name");
        String style = request.getParameter("style");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int noOfDetailEntries = Integer.parseInt(request.getParameter("noOfDetailEntries"));
        System.out.println("name" + name + "g");
        if (name == null || name.trim().equals("")) {
            msg = "<h5 style=\"color:red\">Could not add item. Item name is null</h5>";
        } else {
            String[] colors = new String[noOfDetailEntries + 1];
            String[] sizes = new String[noOfDetailEntries + 1];
            String[] stock = new String[noOfDetailEntries + 1];
            for (int i = 0; i <= noOfDetailEntries; i++) {
                colors[i] = request.getParameter("color_" + i);
                sizes[i] = request.getParameter("size_" + i);
                stock[i] = request.getParameter("stock_" + i);
            }

            itemDAO.addItem(name, price, colors, sizes, style, category,stock,user.getUserId());
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        System.out.println("dispatcher " + dispatcher + " " + request + " " + response);
        dispatcher.include(request, response);
    }

}
