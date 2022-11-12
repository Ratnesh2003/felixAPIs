package com.felix.felixapis.models.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AllCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long allCategoryId;

    private String allCategoryName;

    public AllCategory() {
    }

    public AllCategory(String allCategoryName) {
        this.allCategoryName = allCategoryName;
    }

    public long getAllCategoryId() {
        return allCategoryId;
    }

    public void setAllCategoryId(long allCategoryId) {
        this.allCategoryId = allCategoryId;
    }

    public String getAllCategoryName() {
        return allCategoryName;
    }

    public void setAllCategoryName(String allCategoryName) {
        this.allCategoryName = allCategoryName;
    }
}
