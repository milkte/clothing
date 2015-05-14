package com.model;

/**
 * Created by SRawla on 4/10/2015.
 */
public class Status {
    private int id;
    private String status;

    public Status() {
    }

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Status other = (Status) obj;
        return other.getId() == this.id;
    }

    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", status=" + status + '}';
    }
    
}
