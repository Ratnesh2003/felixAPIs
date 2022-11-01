package com.felix.felixapis.models.movie;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "felix_movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String movieName;

    private String movieDescription;

    private String movieCast;

//    private String movieWriters;

    private int movieYear;

    private String movieRestriction;

    private String movieLength;

    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Genre> genres;

    @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Category> categories;

    public Movie() {
    }

    public Movie(String movieName, String movieDescription, String movieCast, int movieYear, String movieRestriction, String movieLength, List<Genre> genres, List<Category> categories) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieCast = movieCast;
//        this.movieWriters = movieWriters;
        this.movieYear = movieYear;
        this.movieRestriction = movieRestriction;
        this.movieLength = movieLength;
        this.genres = genres;
        this.categories = categories;
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

//    public String getMovieWriters() {
//        return movieWriters;
//    }

//    public void setMovieWriters(String movieWriters) {
//        this.movieWriters = movieWriters;
//    }


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
}
