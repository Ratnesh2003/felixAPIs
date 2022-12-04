package com.felix.felixapis.services.movie;

import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HistoryService {

    ResponseEntity<?> addToWatchHistory(long movieId, HttpServletRequest httpRequest);

    ResponseEntity<List<MoviesWithCategoryResponse>> getWatchHistory(HttpServletRequest httpRequest);

    Boolean movieExistsInHistory(long movieId, long userId);

    ResponseEntity<?> clearWatchHistory(HttpServletRequest httpRequest);

    ResponseEntity<?> deleteMovieFromHistory(long movieId, HttpServletRequest httpRequest);
}
