package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.WatchedHistory;
import com.felix.felixapis.models.movie.Movie;

import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;

import com.felix.felixapis.repository.WatchedHistoryRepository;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class WatchHistoryController {
    private final WatchedHistoryRepository watchedHistoryRepository;
    private final UserRepository userRepository;
    final
    MoviesRepository moviesRepository;
    final
    JwtUtil jwtUtil;
    final
    ImageIDFromMovie imageIDFromMovie;

    public WatchHistoryController(WatchedHistoryRepository watchedHistoryRepository, UserRepository userRepository, MoviesRepository moviesRepository, JwtUtil jwtUtil, ImageIDFromMovie imageIDFromMovie) {
        this.watchedHistoryRepository = watchedHistoryRepository;
        this.userRepository = userRepository;
        this.moviesRepository = moviesRepository;
        this.jwtUtil = jwtUtil;
        this.imageIDFromMovie = imageIDFromMovie;
    }

    @PostMapping("/api/history/add")
    public ResponseEntity<?> addToWatchedHistory(@RequestBody WishlistRequest watchedHistoryRequest, HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        WatchedHistory WatchedMovie = new WatchedHistory(userId, watchedHistoryRequest.getMovieId());

        return ResponseEntity.status(HttpStatus.OK).body("Added to watch history");
    }


    @GetMapping("/api/history/get")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getWatchedHistory(HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        List<Movie> watchHistory = moviesRepository.findWatchedHistoryWhereUserId(userId);
        return imageIDFromMovie.getImageAndIdFromMovieModel(watchHistory, httpRequest);
     }
    @Transactional
    @DeleteMapping("/api/history/clear")
    public ResponseEntity<?> deleteWatchedMovie(HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        watchedHistoryRepository.deleteAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Watch history cleared");
    }


@Transactional
@DeleteMapping("/api/history/delete")
public ResponseEntity<?> deleteWatchedMovie(@RequestParam long movieId,HttpServletRequest httpRequest) {
     String requestTokenHeader = httpRequest.getHeader("Authorization");
     String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
     long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
     watchedHistoryRepository.deleteByWatchedMovieIdAndUserId(movieId, userId);
     return ResponseEntity.status(HttpStatus.OK).body("Removed from Watch History");
 }

}
