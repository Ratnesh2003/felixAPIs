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

    public MovieResponse() {
    }

    public MovieResponse(Long id, String movieName, String movieDescription, String movieCast, int movieYear, String movieRestriction, String movieLength, List<Genre> genres, String coverImageServingPath, String streamMoviePath) {
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
}