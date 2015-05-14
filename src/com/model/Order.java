package com.model;

import java.util.Date;
import java.util.List;

/**
 * Created by SRawla on 4/11/2015.
 */
public class Order {

    private int orderID;
    private Date orderTime;
    private Date shippedTime;
    private String shipTo;
    private String orderStatus;
    private List<OrderItem> orderItemds;
    private String payment;
    private User buyer;
    private User seller;
private double totalAmount;
    public Order() {
        
    }

    public Order(int orderID, Date orderTime, Date shippedTime, String shipTo, String orderStatus) {
        this();
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.shippedTime = shippedTime;
        this.shipTo = shipTo;
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the shippedTime
     */
    public Date getShippedTime() {
        return shippedTime;
    }

    /**
     * @param shippedTime the shippedTime to set
     */
    public void setShippedTime(Date shippedTime) {
        this.shippedTime = shippedTime;
    }

    /**
     * @return the shipTo
     */
    public String getShipTo() {
        return shipTo;
    }

    /**
     * @param shipTo the shipTo to set
     */
    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.getOrderID();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        return other.getOrderID() == this.getOrderID();
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", orderTime=" + orderTime + ", shippedTime=" + shippedTime + ", shipTo=" + shipTo + ", orderStatus=" + orderStatus + ", payment=" + payment + ", buyer=" + buyer + ", totalAmount=" + totalAmount + '}';
    }


    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the orderItemds
     */
    public List<OrderItem> getOrderItemds() {
        return orderItemds;
    }

    /**
     * @param orderItemds the orderItemds to set
     */
    public void setOrderItemds(List<OrderItem> orderItemds) {
        this.orderItemds = orderItemds;
    }

	/**
	 * @return the seller
	 */
	public User getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(User seller) {
		this.seller = seller;
	}

}
