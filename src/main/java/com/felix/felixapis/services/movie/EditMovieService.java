package com.felix.felixapis.services.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.EditMovieRequest;
import com.felix.felixapis.payload.response.MovieResponse;
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
   public MovieResponse getMovieDetails(long movieId){
     Movie movieDetails = moviesRepository.findMovieById(movieId);
     return new MovieResponse(movieDetails.getMovieName(),movieDetails.getMovieDescription(),
             movieDetails.getMovieYear(),
             movieDetails.getGenres());
   }

  public ResponseEntity<?> editMovie(EditMovieRequest editMovieRequest){
    Movie movieDetails = moviesRepository.findMovieById(editMovieRequest.getMovieId());
    movieDetails.setMovieName(editMovieRequest.getNewMovieName());
    movieDetails.setMovieDescription(editMovieRequest.getNewMovieDescription());
    movieDetails.setMovieYear(editMovieRequest.getNewMovieYear());
    movieDetails.setMovieCast(editMovieRequest.getNewMovieCast());
    List<Genre> oldGenre = movieDetails.getGenres();
    List<Category> oldCategories = movieDetails.getCategories();
    if(editMovieRequest.getNewGenre()!=oldGenre) {
      movieDetails.getGenres().clear();
      movieDetails.setGenres(editMovieRequest.getNewGenre());
    }
    if(editMovieRequest.getNewCategory()!=oldCategories) {
      movieDetails.getCategories().clear();
      movieDetails.setCategories(editMovieRequest.getNewCategory());
    }
    moviesRepository.save(movieDetails);
    return ResponseEntity.status(HttpStatus.OK).body("Movie Details Updated Updated");
  }


}
