/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

/**
 *
 * @author shruti
 */
public class UserRatings {
    private int id;
    private Item item;
    private User user;
    private int rating;
    private String comment;

    public UserRatings() {
    }

    public UserRatings(int id, Item item, User user, int rating, String comment) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
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
        final UserRatings other = (UserRatings) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserRatings{" + "id=" + id + ", item=" + item + ", user=" + user + ", rating=" + rating + ", comment=" + comment + '}';
    }

      
}
