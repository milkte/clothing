/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDAO;
import com.dao.ItemDAOImpl;
import com.dao.NotificationDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.google.gson.Gson;
import com.model.Cart;
import com.model.CartItem;
import com.model.Item;
import com.model.Order;
import com.model.User;

/**
 *
 * @author shruti
 */
public class OrderServlet extends HttpServlet {

	OrderDao orderDao = new OrderDao();

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.jsp";
		String msg = "<h5 style=\"color:green\">Order is submitted successfully.</h5>";
		System.out.println("OrderServlet");
		String sellerOrder = request.getParameter("getOrderForSeller");
		String buyerOrder = request.getParameter("getOrderForBuyer");
		String edit = request.getParameter("editOrder");
		String delete = request.getParameter("deleteOrder");
		String orderId = request.getParameter("id");
		if (sellerOrder != null) {
			System.out.println("in get");
			String seller = request.getParameter("seller");
			System.out.println("seller: " + seller);
			List<Order> orderBySeller = orderDao.getOrderBySeller(seller);
			System.out.println("orderBySeller" + orderBySeller);
			String json = new Gson().toJson(orderBySeller);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return;
		}else if (buyerOrder != null) {
			User loggedInUser = (User) request.getSession().getAttribute("user");
			System.out.println("in get");
			String buyer = request.getParameter("buyer");
			System.out.println("seller: " + buyer);
			List<Order> orderBySeller = orderDao.getOrderByBuyer(loggedInUser.getUserId());
			System.out.println("orderBySeller" + orderBySeller);
			String json = new Gson().toJson(orderBySeller);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			return;
		}
		if (edit != null) {

		}
		if (null != delete) {
			boolean isdeleted = orderDao.deleteOrderById(orderId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(isdeleted));
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		try {

			ItemDAOImpl itemDAO = new ItemDAOImpl();
			Integer userid = (Integer) request.getSession().getAttribute(
					"userid");
			Cart cart = new CartDAO().getCart(userid);
			List<CartItem> cartItems = cart.getItems();

			String payment = request.getParameter("payment");

			String addType = request.getParameter("add");
			String address = "";
			if (addType.equals("0")) {
				address += request.getParameter("street")+",";
				address += request.getParameter("city")+",";
				address += request.getParameter("state")+",";
				address += request.getParameter("pincode")+",";
			}

			for (CartItem cartItem : cartItems) {
				Item item = cartItem.getItem();
				item = new ItemDAOImpl().getItemById(item.getItemid());
				int sellerid = -1;
				if (null != item.getSeller()) {
					sellerid = item.getSeller().getUserId();

				}
				int orderid = orderDao.addInitialOrder(0, sellerid, address,
						userid, payment, "0");
				OrderItemDao.addOrderItem(String.valueOf(item.getItemid()),
						String.valueOf(cartItem.getColorId()),
						String.valueOf(cartItem.getSizeId()), sellerid,
						orderid, cartItem.getQuantity(), item.getPrice());
				itemDAO.reduceSttock(String.valueOf(item.getItemid()),
						String.valueOf(cartItem.getColorId()),
						String.valueOf(cartItem.getSizeId()),
						cartItem.getQuantity());
				NotificationDao dao = new NotificationDao();
				dao.addNotification("Order Created", String.valueOf(sellerid), String.valueOf(userid), "Order Creation");
			}
			for (CartItem cartItem : cartItems) {
				new CartDAO().deleteCartItem(cart.getCartId(), cartItem
						.getItem().getItemid());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.include(request, response);
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
