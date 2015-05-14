/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.Objects;

/**
 *
 * @author shruti
 */
public class ItemDetail {
    private Item item;
    private Size size;
    private Color color;

    public ItemDetail() {
    }

    public ItemDetail(Item item, Size size, Color color) {
        this.item = item;
        this.size = size;
        this.color = color;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.item);
        hash = 89 * hash + Objects.hashCode(this.size);
        hash = 89 * hash + Objects.hashCode(this.color);
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
        final ItemDetail other = (ItemDetail) obj;
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemDetail{" + "item=" + item + ", size=" + size + ", color=" + color + '}';
    }
    
}
