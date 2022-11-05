package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.MoviesRequest;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MoviesRepository moviesRepository;

    private final SearchService searchService;

    public MovieController(MoviesRepository moviesRepository, SearchService searchService) {
        this.moviesRepository = moviesRepository;
        this.searchService = searchService;
    }


    @PostMapping("/api/admin/add-new-movie")
    public ResponseEntity<?> addNewMovie(@RequestBody MoviesRequest moviesRequest) {
        moviesRepository.save(moviesRequest.getMovie());
        return ResponseEntity.status(HttpStatus.OK)
                .body(moviesRequest.getMovie());
    }

    @GetMapping("/api/home/movies")
    public List<Movie> getMovies(@RequestParam String category) {
        return moviesRepository.findAllMoviesWhereCategory(category);
    }

    @GetMapping("/api/home/search")
    public List<Movie> searchMovies(@RequestParam String searchText) {
        List<Movie> result = searchService.searchUsingMovieName(searchText);
        if(!result.isEmpty()) {
            return result;
        } else {
            return searchService.searchUsingGenreName(searchText.toUpperCase());
        }
    }
}
