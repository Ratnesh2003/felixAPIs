package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.dto.WatchedHistoryRequest;
import com.felix.felixapis.models.WatchedHistory;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.UserRepository;
import com.felix.felixapis.repository.WatchedHistoryRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class WatchHistoryController {
    @Autowired
    private WatchedHistoryRepository watchedHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    MoviesRepository moviesRepository;
    @Autowired
    JwtUtil jwtUtil;

@PostMapping("/api/home/watchedMovie")
public String watchedHistory(@RequestBody WatchedHistoryRequest watchedHistoryRequest, HttpServletRequest httpRequest) {
    String requestTokenHeader = httpRequest.getHeader("Authorization");
    String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
    long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
    WatchedHistory WatchedMovie = new WatchedHistory(userId, watchedHistoryRequest.getMovieId());
    watchedHistoryRepository.save(WatchedMovie);
    return "saved in Watched History";
}


@GetMapping("/api/home/watched-history")
public List<Movie> getWatchedHistory(HttpServletRequest httpRequest) {
    String requestTokenHeader = httpRequest.getHeader("Authorization");
    String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
    long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
    List<Movie> WatchHistory = moviesRepository.findWatchedHistoryWhereUserId(userId);
    return WatchHistory;
}
@Transactional
@DeleteMapping("/api/watched-history/clear-all-history")
public String deleteWatchedMovie(HttpServletRequest httpRequest) {
    String requestTokenHeader = httpRequest.getHeader("Authorization");
    String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
    long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
    watchedHistoryRepository.deleteAllByUserId(userId);
    return "Watched History Cleared";
}


@Transactional
@DeleteMapping("/api/watched-history/delete/{movieId}")
public String deleteWatchedMovie(@PathVariable long movieId,HttpServletRequest httpRequest) {
     String requestTokenHeader = httpRequest.getHeader("Authorization");
     String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
     long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
     watchedHistoryRepository.deleteByWatchedMovieIdAndUserId(movieId, userId);
     return "Deleted Watched Movie from History";
 }

}
