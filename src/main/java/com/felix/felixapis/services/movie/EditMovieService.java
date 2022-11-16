package com.felix.felixapis.services.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.movie.GenreRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditMovieService {
  @Autowired
  MoviesRepository moviesRepository;
  @Autowired
  GenreRepository genreRepository;

  public ResponseEntity<?> editMovieService(String newMovieName, String newDescription, List<Genre> newGenre, List<Category> newCategory, int newMovieYear, Long movieId){
    Movie movieDetails = moviesRepository.findMovieById(movieId);
    movieDetails.setMovieName(newMovieName);
    movieDetails.setMovieDescription(newDescription);
    movieDetails.setMovieYear(newMovieYear);
    if(newGenre!=null) {
      movieDetails.getGenres().clear();
      movieDetails.setGenres(newGenre);
    }
    if(newCategory!=null) {
      movieDetails.getCategories().clear();
      movieDetails.setCategories(newCategory);
    }
    moviesRepository.save(movieDetails);
    return ResponseEntity.status(HttpStatus.OK).body("Movie Details Updated Updated");
  }


}
