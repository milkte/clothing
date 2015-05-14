/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author shruti
 */
public class Category {

    private int id;
    private String categoryName;
    private String description;
    private Category parentCategory;
    private Set<Style> styles;

    public Category() {
        styles=new HashSet<>();
    }

    public Category(int id, String categoryName, String description, Category parentCategory) {
        this();
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategory = parentCategory;
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
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    /**
     * @return the parentCategory
     */
    public Category getParentCategory() {
        return parentCategory;
    }

    /**
     * @param parentCategory the parentCategory to set
     */
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Style> getStyles() {
        return styles;
    }

    public void setStyles(Set<Style> styles) {
        this.styles = styles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Category other = (Category) obj;
        return other.getId() == this.id;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + ", description=" + description + ", parentCategory=" + parentCategory + '}';
    }

}
