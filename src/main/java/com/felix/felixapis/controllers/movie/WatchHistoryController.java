package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.movie.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class WatchHistoryController {
    final
    MoviesRepository moviesRepository;
    final
    JwtUtil jwtUtil;
    final
    ImageIDFromMovie imageIDFromMovie;
    final HistoryService historyService;

    public WatchHistoryController(MoviesRepository moviesRepository, JwtUtil jwtUtil, ImageIDFromMovie imageIDFromMovie, HistoryService historyService) {
        this.moviesRepository = moviesRepository;
        this.jwtUtil = jwtUtil;
        this.imageIDFromMovie = imageIDFromMovie;
        this.historyService = historyService;
    }

    @PostMapping("/api/history/add")
    public ResponseEntity<?> addToWatchedHistory(@RequestBody WishlistRequest watchedHistoryRequest, HttpServletRequest httpRequest) {
        return historyService.addToWatchHistory(watchedHistoryRequest.getMovieId(), httpRequest);
    }


    @GetMapping("/api/history/get")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getWatchedHistory(HttpServletRequest httpRequest) {
        return historyService.getWatchHistory(httpRequest);
    }
    @DeleteMapping("/api/history/clear")
    public ResponseEntity<?> deleteWatchedMovies(HttpServletRequest httpRequest) {
       return historyService.clearWatchHistory(httpRequest);
    }

    @DeleteMapping("/api/history/delete")
    public ResponseEntity<?> deleteWatchedMovie(@RequestParam long movieId,HttpServletRequest httpRequest) {
        return historyService.deleteMovieFromHistory(movieId, httpRequest);
    }

}
