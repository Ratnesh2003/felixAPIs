package com.felix.felixapis.payload.request;

import com.felix.felixapis.models.movie.AddCategoryModel;

import java.util.List;

public class CategoryTypeRequest {
    private String categoryType;

    public List<AddCategoryModel> allCategories;

    public List<AddCategoryModel> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<AddCategoryModel> allCategories) {
        this.allCategories = allCategories;
    }

    public CategoryTypeRequest() {
    }

    public CategoryTypeRequest(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
