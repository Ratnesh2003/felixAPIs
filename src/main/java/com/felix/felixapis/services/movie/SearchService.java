package com.felix.felixapis.services.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final MoviesRepository moviesRepository;

    public SearchService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> searchUsingMovieName(String searchText) {
        return moviesRepository.searchMoviesByMovieNameContainingIgnoreCase(searchText);
    }

    public List<Movie> searchUsingGenreName(String searchText) {
        return moviesRepository.findAllMoviesWhereGenre(searchText);
    }
}
