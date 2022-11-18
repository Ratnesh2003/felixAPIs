package com.felix.felixapis.payload.request.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EditMovieRequest {

    @NotBlank(message="Must not be null")
    long movieId;
    @NotBlank(message="Must not be null")
    String newMovieDescription;
    @NotBlank(message="Must not be null")
    String newMovieName;
    @NotBlank(message = "Must not be blank")
    String newMovieCast;
    @NotBlank(message="Must not be null")
    int newMovieYear;
    @NotBlank(message="Must not be null")
    List<Genre> newGenre;
    @NotBlank(message="Must not be null")
    List<Category>newCategory;


    public String getNewMovieDescription() {
        return newMovieDescription;
    }

    public void setNewMovieDescription(String newMovieDescription) {
        this.newMovieDescription = newMovieDescription;
    }

    public String getNewMovieName() {
        return newMovieName;
    }

    public void setNewMovieName(String newMovieName) {
        this.newMovieName = newMovieName;
    }

    public int getNewMovieYear() {
        return newMovieYear;
    }

    public void setNewMovieYear(int newMovieYear) {
        this.newMovieYear = newMovieYear;
    }

    public List<Genre> getNewGenre() {
        return newGenre;
    }

    public void setNewGenre(List<Genre> newGenre) {
        this.newGenre = newGenre;
    }

    public List<Category> getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(List<Category> newCategory) {
        this.newCategory = newCategory;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getNewMovieCast() {
        return newMovieCast;
    }

    public void setNewMovieCast(String newMovieCast) {
        this.newMovieCast = newMovieCast;
    }
}
