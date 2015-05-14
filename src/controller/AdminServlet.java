/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.dao.AuthDAO;
import com.google.gson.Gson;
import com.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shruti
 */
public class AdminServlet extends HttpServlet {

    AuthDAO authDAO = new AuthDAO();
    Gson gson=new Gson();
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
        
        String add = request.getParameter("add");
        String remove = request.getParameter("remove");
        System.out.println("remove= " + remove);
        String get = request.getParameter("get");
        System.out.println("get= " + get);
        String userid = request.getParameter("user");
        if (add != null) {
            authDAO.updateRole(userid, AuthDAO.ADMIN);
        } else if (get != null) {
            List<User> users=null;
            if (get.equals("users")) {
                users=authDAO.retrieveUserByRole(AuthDAO.USER_ROLE);
                
            } else {
                users=authDAO.retrieveUserByRole(AuthDAO.ADMIN);
            }
            System.out.println("users" + users);
            if(users!=null){
                String json = gson.toJson(users);
                response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
            }
        } else {
            authDAO.updateRole(userid, AuthDAO.USER_ROLE);
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
