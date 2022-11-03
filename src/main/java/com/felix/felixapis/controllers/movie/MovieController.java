package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.MoviesRequest;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MoviesRepository moviesRepository;

    public MovieController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
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
}
