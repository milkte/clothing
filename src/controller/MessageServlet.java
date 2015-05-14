/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MessageDao;
import com.google.gson.Gson;
import com.model.Message;

/**
 *
 * @author shruti
 */
public class MessageServlet extends HttpServlet {
MessageDao messageDao=new MessageDao();
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
        String get = request.getParameter("getAllMessages");
        String markseen = request.getParameter("markseen");
        String add = request.getParameter("addNotification");
        String getMsg = request.getParameter("getmessage");
        System.out.println("GETCOUNT" + getServletContext().ORDERED_LIBS);
        System.out.println("sessionfgh " + (session != null));
       if (add != null) {

            if (session != null) {
                String userid = ((Integer) session.getAttribute("userid")).toString();
                String to = request.getParameter("forUser");
                String message = request.getParameter("message");
                messageDao.addMessage(to, userid, message);
            }
        } 
         else if (markseen != null) {
           System.out.println("In markseen");
                String msg = request.getSession().getAttribute("userid").toString();
                messageDao.markAsRead(msg);
        } 
        else if (get != null) {

            if (session != null) {
                String userid = ((Integer) session.getAttribute("userid")).toString();
                List<Message> messagesForUser = messageDao.getMessagesForUser(userid);
                String toJson = new Gson().toJson(messagesForUser);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(toJson);
                
                return;
            }
        }else if(getMsg!=null){
             String msgId = ((Integer) session.getAttribute("msgId")).toString();
            Message messageById = messageDao.getMessageById(msgId);
                String toJson = new Gson().toJson(messageById);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(toJson);
                
                return;
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
