package com.felix.felixapis.services.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.models.movie.WatchedHistory;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.WatchedHistoryRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class HistoryService {

    final
    WatchedHistoryRepository watchedHistoryRepository;

    final
    GetDetailsFromUser getDetailsFromUser;

    final
    MoviesRepository moviesRepository;

    final
    ImageIDFromMovie imageIDFromMovie;

    public HistoryService(WatchedHistoryRepository watchedHistoryRepository, GetDetailsFromUser getDetailsFromUser, MoviesRepository moviesRepository, ImageIDFromMovie imageIDFromMovie) {
        this.watchedHistoryRepository = watchedHistoryRepository;
        this.getDetailsFromUser = getDetailsFromUser;
        this.moviesRepository = moviesRepository;
        this.imageIDFromMovie = imageIDFromMovie;
    }

    public ResponseEntity<?> addToWatchHistory(long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);

        if(!moviesRepository.existsById(movieId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie does not exist");
        }

        if (movieExistsInHistory(movieId, userId)) {
            watchedHistoryRepository.deleteByWatchedMovieIdAndUserId(movieId, userId);
        }
        WatchedHistory WatchedMovie = new WatchedHistory(userId, movieId);
        watchedHistoryRepository.save(WatchedMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Added to watch history");
    }

    public ResponseEntity<List<MoviesWithCategoryResponse>> getWatchHistory(HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        List<Movie> watchHistory = moviesRepository.findWatchedHistoryWhereUserId(userId);
        Collections.reverse(watchHistory);
        return imageIDFromMovie.getImageAndIdFromMovieModel(watchHistory, httpRequest);
    }

    public Boolean movieExistsInHistory(long movieId, long userId) {
        return watchedHistoryRepository.existsByWatchedMovieIdAndUserId(movieId, userId);
    }

    public ResponseEntity<?> clearWatchHistory(HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        watchedHistoryRepository.deleteAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Watch history cleared");
    }

    public ResponseEntity<?> deleteMovieFromHistory(long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        watchedHistoryRepository.deleteByWatchedMovieIdAndUserId(movieId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Removed from Watch History");
    }
}

