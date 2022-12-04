package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.impl.HistoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WatchHistoryController {
    final
    MoviesRepository moviesRepository;
    final
    JwtUtil jwtUtil;
    final
    ImageIDFromMovie imageIDFromMovie;
    final HistoryServiceImpl historyServiceImpl;

    public WatchHistoryController(MoviesRepository moviesRepository, JwtUtil jwtUtil, ImageIDFromMovie imageIDFromMovie, HistoryServiceImpl historyServiceImpl) {
        this.moviesRepository = moviesRepository;
        this.jwtUtil = jwtUtil;
        this.imageIDFromMovie = imageIDFromMovie;
        this.historyServiceImpl = historyServiceImpl;
    }

    @PostMapping("/api/history/add")
    public ResponseEntity<?> addToWatchedHistory(@RequestBody WishlistRequest watchedHistoryRequest, HttpServletRequest httpRequest) {
        return historyServiceImpl.addToWatchHistory(watchedHistoryRequest.getMovieId(), httpRequest);
    }


    @GetMapping("/api/history/get")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getWatchedHistory(HttpServletRequest httpRequest) {
        return historyServiceImpl.getWatchHistory(httpRequest);
    }
    @DeleteMapping("/api/history/clear")
    public ResponseEntity<?> deleteWatchedMovies(HttpServletRequest httpRequest) {
       return historyServiceImpl.clearWatchHistory(httpRequest);
    }

    @DeleteMapping("/api/history/delete")
    public ResponseEntity<?> deleteWatchedMovie(@RequestParam long movieId,HttpServletRequest httpRequest) {
        return historyServiceImpl.deleteMovieFromHistory(movieId, httpRequest);
    }

}
