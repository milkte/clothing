/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ItemDAOImpl;
import com.dao.OrderItemDao;
import com.google.gson.Gson;
import com.model.Item;
import com.model.OrderItem;

/**
 *
 * @author shruti
 */
public class OrderItemServlet extends HttpServlet {


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
        System.out.println("OrderServlet");
        String get = request.getParameter("getOrderItemForSeller");
        if (get != null) {
            System.out.println("in get");
            String seller = request.getParameter("seller");
            String orderId = request.getParameter("id");
            System.out.println("seller: " + seller);
            List<OrderItem> getOrderItem = OrderItemDao.GetOrderItem(orderId, seller);

            System.out.println("orderBySeller" + getOrderItem);
            String json = new Gson().toJson(getOrderItem);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        Integer userid = (Integer) request.getSession().getAttribute("userid");
        int noOfitems = Integer.parseInt(request.getParameter("noOfitems"));
        String payment = request.getParameter("payment");
        Set<Integer> sellerSet = new HashSet<>();
        String id = request.getParameter("id_0");
        int qty = Integer.parseInt(request.getParameter("qty_0"));
        String[] split = id.split("-");
        Item item = new ItemDAOImpl().getItemById(Integer.parseInt(split[0]));
        int sellerid = item.getSeller().getUserId();
        sellerSet.add(sellerid);

//            int orderid = orderDao.addInitialOrder(0, sellerid, "1", userid, payment, ((Integer) qty).toString());
//            OrderItemDao.addOrderItem(split[0], split[1], split[2], orderid, qty, item.getPrice());
//            for (int i = 1; i < noOfitems; i++) {
//                id = request.getParameter("id_" + i);
//                qty = Integer.parseInt(request.getParameter("qty_" + i));
//                split = id.split("-");
//                item = new ItemDAOImpl().getItemById(Integer.parseInt(split[0]));
//                sellerid = item.getSeller().getUser().getUserId();
//                if (sellerSet.add(sellerid)) {
//                    orderDao.addInitialOrder(orderid, sellerid, "1", userid, payment, ((Integer) qty).toString());
//                }
//                 OrderItemDao.addOrderItem(split[0], split[1], split[2], orderid, qty, item.getPrice());
//                System.out.println("splitlength" + split.length);
//
//            }
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
