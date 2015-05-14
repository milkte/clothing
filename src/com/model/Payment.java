package com.model;

/**
 * Created by SRawla on 4/10/2015.
 */
import java.util.Date;

public class Payment {

    private int paymentid;
    private Date paidDate;
    private boolean isCashOnDelivery;
    private String details;
    private double total;
    private long creditCardNumber;

    public Payment() {
    }

    public Payment(int paymentid, Date paidDate, boolean isCashOnDelivery, String details, double total, long creditCardNumber) {
        this.paymentid = paymentid;
        this.paidDate = paidDate;
        this.isCashOnDelivery = isCashOnDelivery;
        this.details = details;
        this.total = total;
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the paymentid
     */
    public int getPaymentid() {
        return paymentid;
    }

    /**
     * @param paymentid the paymentid to set
     */
    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    /**
     * @return the paidDate
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     * @param paidDate the paidDate to set
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * @return the isCashOnDelivery
     */
    public boolean isIsCashOnDelivery() {
        return isCashOnDelivery;
    }

    /**
     * @param isCashOnDelivery the isCashOnDelivery to set
     */
    public void setIsCashOnDelivery(boolean isCashOnDelivery) {
        this.isCashOnDelivery = isCashOnDelivery;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the creditCardNumber
     */
    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.paymentid;
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
        final Payment other = (Payment) obj;
        return this.paymentid == other.paymentid;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentid=" + paymentid + ", paidDate=" + paidDate + ", isCashOnDelivery=" + isCashOnDelivery + ", details=" + details + ", total=" + total + ", creditCardNumber=" + creditCardNumber + '}';
    }
    
}
