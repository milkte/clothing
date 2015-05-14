/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NotificationDao;
import com.dao.OrderDao;
import com.model.User;

/**
 *
 * @author shruti
 */
public class UpdateOrderServlet extends HttpServlet {
OrderDao orderDao=new OrderDao();
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
       System.out.println("in edit");
            String seller = request.getParameter("seller");
            String orderId = request.getParameter("orderId");
            String shippedOn = request.getParameter("shippedOn");
            String status = request.getParameter("status");
            User loggedInUser = (User) request.getSession().getAttribute("user");
            System.out.println("seller: " + seller + " orderId" + orderId + " shippedOn" + shippedOn+" status" +status)  ;
//         orderDao.updateOrder(orderId, null, shippedOn, seller, , user, seller, seller, seller)
            OrderDao.updateOrder(orderId, shippedOn, seller, status);
            NotificationDao dao = new NotificationDao();
            dao.addNotification("Order Updated", String.valueOf(seller), String.valueOf(loggedInUser.getUserId()), "Order Creation");
            return;
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
