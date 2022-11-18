package com.felix.felixapis.payload.response;

import com.felix.felixapis.models.movie.Genre;
import java.util.List;

public class MovieResponse {

    private Long id;

    private String movieName;

    private String movieDescription;

    private String movieCast;

    private int movieYear;

    private String movieRestriction;

    private String movieLength;

    private List<Genre> genres;

    private String coverImageServingPath;

    private String streamMoviePath;

    private Boolean addedToWishlist;

    private Boolean liked;

    private String rating;

    private int totalReviews;

    public MovieResponse() {
    }

    public MovieResponse(Long id, String movieName, String movieDescription, String movieCast, int movieYear, String movieRestriction, String movieLength, List<Genre> genres, String coverImageServingPath, String streamMoviePath, Boolean addedToWishlist, Boolean liked, String rating, int totalReviews) {
        this.id = id;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieCast = movieCast;
        this.movieYear = movieYear;
        this.movieRestriction = movieRestriction;
        this.movieLength = movieLength;
        this.genres = genres;
        this.coverImageServingPath = coverImageServingPath;
        this.streamMoviePath = streamMoviePath;
        this.addedToWishlist = addedToWishlist;
        this.liked = liked;
        this.rating = rating;
        this.totalReviews = totalReviews;
    }
    public MovieResponse(String movieName, String movieDescription, int movieYear, List<Genre> genres) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieYear = movieYear;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieRestriction() {
        return movieRestriction;
    }

    public void setMovieRestriction(String movieRestriction) {
        this.movieRestriction = movieRestriction;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getCoverImageServingPath() {
        return coverImageServingPath;
    }

    public void setCoverImageServingPath(String coverImageServingPath) {
        this.coverImageServingPath = coverImageServingPath;
    }

    public String getStreamMoviePath() {
        return streamMoviePath;
    }

    public void setStreamMoviePath(String streamMoviePath) {
        this.streamMoviePath = streamMoviePath;
    }

    public Boolean getAddedToWishlist() {
        return addedToWishlist;
    }

    public void setAddedToWishlist(Boolean addedToWishlist) {
        this.addedToWishlist = addedToWishlist;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }
}
