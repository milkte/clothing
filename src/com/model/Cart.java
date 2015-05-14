package com.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {
	private int cartId;
	private int userId;
	private double total;
	private List<CartItem> items = new LinkedList<CartItem>();

	/**
	 * @return the cartId
	 */
	public int getCartId() {
		return cartId;
	}

	/**
	 * @param cartId
	 *            the cartId to set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void addItem(CartItem item) {
		items.add(item);
	}

	/**
	 * 
	 * @return the items
	 */
	public List<CartItem> getItems() {
		return this.items;
	}
}
