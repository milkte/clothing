/**
 * 
 */
package com.model;

/**
 * @author dijadhav
 *
 */
public class CartItem {
	private Item item;
	private int quantity;
	private int colorId;
	private int sizeId;

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the colorId
	 */
	public int getColorId() {
		return colorId;
	}

	/**
	 * @param colorId the colorId to set
	 */
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	/**
	 * @return the sizeId
	 */
	public int getSizeId() {
		return sizeId;
	}

	/**
	 * @param sizeId the sizeId to set
	 */
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

}
