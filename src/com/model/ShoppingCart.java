package com.model;

/**
 * Created by SRawla on 4/10/2015.
 */
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

    private long id;
    private Date createdOn;
    private Set<Item> items;
    private User createdBy;

    public ShoppingCart() {
        this.items = new HashSet<>();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the items
     */
    public Set<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     *Adds item to shopping cart
     * @param item the item to add
     */
    public void addItem(Item item){
        assert item!=null:"Item Should not be null";
     items.add(item);
    }

    /**
     *Removes item from shopping cart
     * @param item the item to remove
     */
    public void removeItem(Item item){
        assert item!=null:"Item Should not be null";
     items.remove(item);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ShoppingCart other = (ShoppingCart) obj;
        return this.id == other.getId();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "id=" + id + ", createdOn=" + createdOn + ", items=" + items + ", createdBy=" + createdBy + '}';
    }
    
}
