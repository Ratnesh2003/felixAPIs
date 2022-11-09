package com.felix.felixapis.models.movie;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "all_category")
public class AddCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long allCategoryId;
    @Column(name="category_type")
    private String categoryType;


    public AddCategoryModel(String categoryType) {
        this.categoryType = categoryType;

    }

    public AddCategoryModel() {

    }

    public long getAllCategoryId() {
        return allCategoryId;
    }

    public void setAllCategoryId(long allCategoryId) {
        this.allCategoryId = allCategoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
