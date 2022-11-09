package com.felix.felixapis.payload.response;

public class MoviesWithCategoryResponse {
    private Long movieId;
    private String coverImageServingPath;

    public MoviesWithCategoryResponse() {
    }

    public MoviesWithCategoryResponse(Long movieId, String coverImageServingPath) {
        this.movieId = movieId;
        this.coverImageServingPath = coverImageServingPath;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getCoverImageServingPath() {
        return coverImageServingPath;
    }

    public void setCoverImageServingPath(String coverImageServingPath) {
        this.coverImageServingPath = coverImageServingPath;
    }
}
