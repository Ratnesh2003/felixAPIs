package com.felix.felixapis.repository;

import com.felix.felixapis.models.movie.WatchedHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedHistoryRepository extends JpaRepository<WatchedHistory,Long> {

    void deleteByWatchedMovieIdAndUserId(long watchedMovieId, long userId);

    void deleteAllByUserId(long userId);

    boolean existsByWatchedMovieIdAndUserId(long movieId, long userId);


}
