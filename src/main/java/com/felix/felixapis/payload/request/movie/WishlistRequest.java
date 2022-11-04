package com.felix.felixapis.payload.request.movie;

public class WishlistRequest {
    private Long movieId;

    public WishlistRequest() {
    }

    public WishlistRequest(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
