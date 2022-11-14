package com.felix.felixapis.controllers.movie;


import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.response.MovieResponse;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.services.movie.StreamingPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StreamingPageController {

    final
    MoviesRepository moviesRepository;

    final
    StreamingPageService streamingPageService;

    public StreamingPageController(MoviesRepository moviesRepository, StreamingPageService streamingPageService) {
        this.moviesRepository = moviesRepository;
        this.streamingPageService = streamingPageService;
    }


    @GetMapping("/api/media-streaming")
    public ResponseEntity<MovieResponse> getAllDetailsOfMovie(@RequestParam long movieId, HttpServletRequest httpRequest) {
        Movie movieDetails = moviesRepository.findMovieById(movieId);
//        List<Genre> genres = moviesRepository.findGenreNamesFromMovieId(movieId);
        return streamingPageService.getStreamingPageDetails(movieDetails, httpRequest);
    }
}
