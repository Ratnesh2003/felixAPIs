package com.felix.felixapis.payload.request.movie;

public class AllCategoryRequest {
    private String allCategoryName;

    public AllCategoryRequest() {
    }

    public AllCategoryRequest(String allCategoryName) {
        this.allCategoryName = allCategoryName;
    }

    public String getAllCategoryName() {
        return allCategoryName;
    }

    public void setAllCategoryName(String allCategoryName) {
        this.allCategoryName = allCategoryName;
    }
}
