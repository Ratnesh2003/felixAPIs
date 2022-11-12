package com.felix.felixapis.payload.response;

public class SearchMovieResponse {
    private long movieId;
    private String movieName;
    private String movieCoverServingURL;

    public SearchMovieResponse() {
    }

    public SearchMovieResponse(long movieId, String movieName, String movieCoverServingURL) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieCoverServingURL = movieCoverServingURL;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieCoverServingURL() {
        return movieCoverServingURL;
    }

    public void setMovieCoverServingURL(String movieCoverServingURL) {
        this.movieCoverServingURL = movieCoverServingURL;
    }
}
