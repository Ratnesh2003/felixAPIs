package com.felix.felixapis.payload.request.movie;

import com.felix.felixapis.models.movie.Movie;

public class MoviesRequest {
    private Movie movie;

    public MoviesRequest() {
    }

    public MoviesRequest(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
