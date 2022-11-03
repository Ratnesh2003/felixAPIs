package com.felix.felixapis.payload.request.movie;

public class MovieCoverRequest {
    private Long movieId;

    public MovieCoverRequest() {
    }

    public MovieCoverRequest(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
