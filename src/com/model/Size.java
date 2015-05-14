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
public class Size {
    private int id;
    private String sizeCode;
    private String description;

    public Size() {
    }

    public Size(int id, String sizeCode, String description) {
        this.id = id;
        this.sizeCode = sizeCode;
        this.description = description;
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
     * @return the sizeCode
     */
    public String getSizeCode() {
        return sizeCode;
    }

    /**
     * @param sizeCode the sizeCode to set
     */
    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.sizeCode);
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
        final Size other = (Size) obj;
        if (!Objects.equals(this.sizeCode, other.sizeCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sizeCode=" + sizeCode + ", description=" + description ;
    }
    
}
