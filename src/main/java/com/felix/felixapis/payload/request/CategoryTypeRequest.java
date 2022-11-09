package com.felix.felixapis.payload.request;

public class CategoryTypeRequest {
    private String categoryType;

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
