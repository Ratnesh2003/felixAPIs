package com.felix.felixapis.payload.request.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Genre;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EditMovieRequest {

    @NotNull
    long movieId;
    @NotNull
    String newMovieDescription;
    @NotNull
    String newMovieName;
    @NotNull
    int newMovieYear;
    @NotNull
    List<Genre> newGenre;
    @NotNull
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
}
