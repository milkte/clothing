package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.model.Cart;
import com.model.CartItem;
import com.model.Item;

public class CartDAO {

	/**
	 * Add item to the cart
	 * 
	 * @param itemId
	 * @param userId
	 * @param colorId
	 * @param sizeId
	 * @return
	 */
	public int addItemToCart(int itemId, int sizeId, int colorId, int userId) {
		int flag = 1;
		ResultSet result;
		PreparedStatement ps = null;
		String sql = "select cartId from cart where userId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			result = ps.executeQuery();
			int cartId = 0;
			if (null != result && result.next()) {
				cartId = result.getInt(1);
			}
			ps.close();

			if (0 != cartId) {
				ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
				Item item = itemDAOImpl.getItemById(itemId);
				if (null != item) {
					sql = "insert into cart_item(cartId,itemId,quantity,sizeId,colorId) values(?,?,?,?,?)";
					ps = SqlUtil.getConnection().prepareStatement(sql);
					ps.setInt(1, cartId);
					ps.setInt(2, itemId);
					ps.setInt(3, 1);
					ps.setInt(4, sizeId);
					ps.setInt(5, colorId);
					ps.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			flag = 1;
		}

		return flag;

	}

	public CartItem getItemById(int itemId, int userId) {
		CartItem cartItem = null;
		ResultSet result;
		PreparedStatement ps = null;
		String sql = "select cartId from cart where userId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			result = ps.executeQuery();
			int cartId = 0;
			if (null != result && result.next()) {
				cartId = result.getInt(1);
			}
			ps.close();

			if (0 != cartId) {
				ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
				Item item = itemDAOImpl.getItemById(itemId);
				if (null != item) {
					sql = "select * from cart_item where cartId =? and itemId =? ";
					ps = SqlUtil.getConnection().prepareStatement(sql);
					ps.setInt(1, cartId);
					ps.setInt(2, itemId);
					result = ps.executeQuery();
					if (null != result && result.next()) {
						cartItem = new CartItem();
						cartItem.setItem(item);
						cartItem.setColorId(result.getInt(4));
						cartItem.setSizeId(result.getInt(5));
						cartItem.setQuantity(result.getInt(3));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItem;
	}

	public Cart getCart(int userId) {

		Cart cart = new Cart();
		ResultSet result;
		PreparedStatement ps = null;
		String sql = "select cartId from cart where userId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			result = ps.executeQuery();
			int cartId = 0;
			if (null != result && result.next()) {
				cartId = result.getInt(1);
			}
			ps.close();

			if (0 != cartId) {

				sql = "select * from cart_item where cartId =? ";
				ps = SqlUtil.getConnection().prepareStatement(sql);
				ps.setInt(1, cartId);
				result = ps.executeQuery();
				if (null != result) {
					while (result.next()) {
						ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
						Item item = itemDAOImpl.getItemById(result.getInt(2));
						CartItem cartItem = new CartItem();
						cartItem.setItem(item);
						cartItem.setQuantity(result.getInt(3));
						cartItem.setColorId(result.getInt(4));
						cartItem.setSizeId(result.getInt(5));
						cart.addItem(cartItem);
						cart.setCartId(cartId);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	public int deleteCartItem(int cartId, int itemId) {
		int flag = 1;
		PreparedStatement ps = null;
		String sql = "delete from cart_item where cartId=? and itemId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, cartId);
			ps.setInt(2, itemId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}

	public int updateQuantity(int cartId, int itemId, int quantity) {
		int flag = 1;
		PreparedStatement ps = null;
		String sql = "update cart_item set quantity= ? where cartId=? and itemId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, cartId);
			ps.setInt(3, itemId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}

	public int creatCart(int userId) {
		int flag = 1;
		PreparedStatement ps = null;
		String sql = "insert into cart(userId) values(?)";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	
	public int isCartExist(int userId) {
		int flag = 1;
		PreparedStatement ps = null;
		ResultSet result;
		String sql = "select cardId cart where userId=?";
		try {
			ps = SqlUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			result=ps.executeQuery();
			if(null!=result && result.next()){
				flag=1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	
}
