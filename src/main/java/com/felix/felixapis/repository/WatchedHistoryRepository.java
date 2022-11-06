package com.felix.felixapis.repository;

import com.felix.felixapis.models.movie.WatchedHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedHistoryRepository extends JpaRepository<WatchedHistory,Long> {

    String deleteByWatchedMovieIdAndUserId(long watchedMovieId, long userId);

    String deleteAllByUserId(long userId);
}
