/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.Date;

/**
 *
 * @author shruti
 */
public class Notification {
    private int id;
    private String subject;
    private Date onDate;
    private User forUser;
    private User associatedUser;
    private boolean isSeen;

    public Notification() {
    }

    public Notification(int id, String subject, Date onDate, User forUser, User associatedUser, boolean isSeen) {
        this.id = id;
        this.subject = subject;
        this.onDate = onDate;
        this.forUser = forUser;
        this.associatedUser = associatedUser;
        this.isSeen = isSeen;
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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the onDate
     */
    public Date getOnDate() {
        return onDate;
    }

    /**
     * @param onDate the onDate to set
     */
    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    /**
     * @return the forUser
     */
    public User getForUser() {
        return forUser;
    }

    /**
     * @param forUser the forUser to set
     */
    public void setForUser(User forUser) {
        this.forUser = forUser;
    }

    /**
     * @return the associatedUser
     */
    public User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * @param associatedUser the associatedUser to set
     */
    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    /**
     * @return the isSeen
     */
    public boolean isIsSeen() {
        return isSeen;
    }

    /**
     * @param isSeen the isSeen to set
     */
    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }
    
}
