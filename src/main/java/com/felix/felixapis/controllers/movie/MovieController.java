package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.MoviesRequest;
import com.felix.felixapis.repository.movie.CategoryRepository;
import com.felix.felixapis.repository.movie.GenreRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/")
public class MovieController {
    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/api/admin/addnewmovie")
    public String addNewMovie(@RequestBody MoviesRequest moviesRequest) {
        moviesRepository.save(moviesRequest.getMovie());
        return "Movie added successfully";
    }

    @GetMapping("/api/home/movies")
    public List<Movie> getMovies(@RequestParam String category) {
        return moviesRepository.findAllMoviesWhereCategory(category);
    }

    @GetMapping("/api/home/search")
    public List<Movie> searchMovies(@RequestParam String searchText) {

    }
}
