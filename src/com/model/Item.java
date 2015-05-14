package com.model;

import java.util.List;

/**
 * Created by SRawla on 4/10/2015.
 */
public class Item {

     private int itemid;
     private String itemName;
    private double price;
    private Seller seller;
    private Style style;
    //List of all available options for this item. should be null for items in cart.
    private List<ItemDetail> itemDetails;
    //Selected Item. this would be null initially and should be initialized when item is added to cart.
    private ItemDetail itemDetail;
     private Category category;

    public Item() {
    }

    public Item(int itemid, String itemName, double price, Seller seller, Style style, List<ItemDetail> itemDetails, ItemDetail itemDetail, Category category) {
        this.itemid = itemid;
        this.itemName = itemName;
        this.price = price;
        this.seller = seller;
        this.style = style;
        this.itemDetails = itemDetails;
        this.itemDetail = itemDetail;
        this.category = category;
    }


    /**
     * @return the itemid
     */
    public int getItemid() {
        return itemid;
    }

    /**
     * @param itemid the itemid to set
     */
    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @return the itemDetails
     */
    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    /**
     * @param itemDetails the itemDetails to set
     */
    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    /**
     * @return the itemDetail
     */
    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    /**
     * @param itemDetail the itemDetail to set
     */
    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.getItemid();
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
        final Item other = (Item) obj;
        return other.getItemid() == this.getItemid();
    }

    @Override
    public String toString() {
        return "Item{" + "itemid=" + itemid + ", itemName=" + itemName + ", price=" + price + ", seller=" + seller + ", style=" + style + ", itemDetails=" + itemDetails + ", itemDetail=" + itemDetail + ", category=" + category + '}';
    }

    }
