/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.dao.AuthDAO;
import com.google.gson.Gson;
import com.model.Seller;
import com.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shruti
 */
public class EmployeeServlet extends HttpServlet {

    Gson gson = new Gson();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String getUserLike = request.getParameter("getUserLike");
        if (getUserLike != null) {
            List<User> retrieveUserLike = AuthDAO.retrieveUserLike(request.getParameter("keyword"));
            String json = gson.toJson(retrieveUserLike);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        } else {
            String usserString = request.getParameter("userId");
            String url = request.getParameter("responseUrl");
            Integer userId = null;
            String isSeller = request.getParameter("isSeller");
            if (usserString == null) {
                HttpSession session = request.getSession();
                if (session != null) {
                    userId = (int) session.getAttribute("userid");
                }
            }else{
                 userId = Integer.parseInt(usserString);
            
            }
            if(isSeller.equals("true")){
                Seller seller = AuthDAO.getSellerById(userId);
                request.setAttribute("user", seller);
            }else{
                User user = AuthDAO.getUserById(userId);
                request.setAttribute("user", user);
            }
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        System.out.println("dispatcher " + dispatcher + " " + request + " " + response);
        dispatcher.include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
