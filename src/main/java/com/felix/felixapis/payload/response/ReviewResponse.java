package com.felix.felixapis.payload.response;

import java.util.Date;

public class ReviewResponse {
    private String nameOfUser;
    private String role;
    private String reviewText;
    private int rating;
    private String dateAdded;

    public ReviewResponse(String nameOfUser, String role, String reviewText, int rating, String dateAdded) {
        this.nameOfUser = nameOfUser;
        this.role = role;
        this.reviewText = reviewText;
        this.rating = rating;
        this.dateAdded = dateAdded;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
