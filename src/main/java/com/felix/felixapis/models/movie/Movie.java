package com.felix.felixapis.models.movie;


//import com.felix.felixapis.models.WatchedMovie;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "felix_movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;

    private String movieDescription;

    private String movieCast;

    private int movieYear;

    private String movieRestriction;

    private String movieLength;

    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Genre> genres;

    @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Category> categories;

    private String coverImagePath;

    private String coverImageServingPath;

    private String streamMovieName;

    private String streamMoviePath;

    private int likesCount = 0;

    private Date uploadDate = new Date();

    public Movie() {
    }

    public Movie(String movieName,
                 String movieDescription,
                 String movieCast, int movieYear,
                 String movieRestriction,
                 String movieLength,
                 List<Genre> genres,
                 List<Category> categories,
                 String coverImagePath,
                 String streamMovieName) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieCast = movieCast;
        this.movieYear = movieYear;
        this.movieRestriction = movieRestriction;
        this.movieLength = movieLength;
        this.genres = genres;
        this.categories = categories;
        this.coverImagePath = coverImagePath;
        this.streamMovieName = streamMovieName;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public String getCoverImageServingPath() {
        return coverImageServingPath;
    }

    public void setCoverImageServingPath(String coverImageServingPath) {
        this.coverImageServingPath = coverImageServingPath;
    }

    public String getStreamMovieName() {
        return streamMovieName;
    }

    public void setStreamMovieName(String streamMovieName) {
        this.streamMovieName = streamMovieName;
    }

    public String getStreamMoviePath() {
        return streamMoviePath;
    }

    public void setStreamMoviePath(String streamMoviePath) {
        this.streamMoviePath = streamMoviePath;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
