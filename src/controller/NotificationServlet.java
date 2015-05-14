/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.dao.MessageDao;
import com.dao.NotificationDao;
import com.google.gson.Gson;
import com.model.Notification;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shruti
 */
public class NotificationServlet extends HttpServlet {

    NotificationDao notificationDao = new NotificationDao();

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
        HttpSession session = request.getSession(false);
        String get = request.getParameter("getNotification");
        String markseen = request.getParameter("markseen");
        String add = request.getParameter("addNotification");
        String getCount = request.getParameter("getCount");
        System.out.println("GETCOUNT" + getServletContext().ORDERED_LIBS);
        System.out.println("sessionfgh " + (session != null));
        if (getCount != null) {
            if (session != null) {
                System.out.println("in here");
                if(session.getAttribute("userid")!=null){
                String userid = ((Integer) session.getAttribute("userid")).toString();
                
                System.out.println("userid: " + userid);
                int notification = notificationDao.getNotificationCountForUser(userid);
                int message = new MessageDao().getMessageCountForUser(userid);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"notificationCount\" :\"" + notification + "\" , \"messageCount\" : \"" + message + "\"}");
                }
                return;
            }
        } else if (add != null) {

            if (session != null) {
                String userid = ((Integer) session.getAttribute("userid")).toString();
                String forUser = request.getParameter("forUser");
                String notification = request.getParameter("Notification");
                notificationDao.addNotification(notification, forUser, userid,"Notification");
            }
        } 
         else if (markseen != null) {

            if (session != null) {
                String userid = ((Integer) session.getAttribute("userid")).toString();
                String forUser = request.getParameter("forUser");
                String notification = request.getParameter("Notification");
                notificationDao.markAsReadNotification(userid);
            }
        } 
        else if (get != null) {

            if (session != null) {
                String userid = ((Integer) session.getAttribute("userid")).toString();
                List<Notification> notificationForUser = notificationDao.getNotificationForUser(userid);
                String toJson = new Gson().toJson(notificationForUser);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(toJson);
                
                return;
            }
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
