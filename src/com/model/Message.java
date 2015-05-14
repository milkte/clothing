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
public class Message {
    private int id;
    private int to;
    private int from;
    private String message;
    private boolean isSeen;
    private Date snetOn;

    public Message(int id, int to, int from, String message, boolean isSeen, Date snetOn) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.message = message;
        this.isSeen = isSeen;
        this.snetOn = snetOn;
    }

    public Message() {
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
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(int to) {
        this.to = to;
    }

    /**
     * @return the from
     */
    public int getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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

    /**
     * @return the snetOn
     */
    public Date getSnetOn() {
        return snetOn;
    }

    /**
     * @param snetOn the snetOn to set
     */
    public void setSnetOn(Date snetOn) {
        this.snetOn = snetOn;
    }
}
