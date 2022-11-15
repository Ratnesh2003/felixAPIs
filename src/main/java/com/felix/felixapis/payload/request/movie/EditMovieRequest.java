package com.felix.felixapis.payload.request.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Genre;

import java.util.List;

public class EditMovieRequest {
    long movieId;
    String newMovieDescription;
    String newMovieName;
    int newMovieYear;
    List<Genre> newGenre;
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
