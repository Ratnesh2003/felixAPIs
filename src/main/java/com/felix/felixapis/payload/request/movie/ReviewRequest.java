package com.felix.felixapis.payload.request.movie;

public class ReviewRequest {
    private long userId;
    private long movieId;
    private int rating;
    private String reviewText;

    public ReviewRequest() {
    }

    public ReviewRequest(long movieId, int rating, String reviewText) {
        this.movieId = movieId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
