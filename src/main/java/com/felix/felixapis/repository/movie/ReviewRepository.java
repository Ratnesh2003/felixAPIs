package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    Reviews findByUserId(long userId);
    List<Reviews> findByMovieIdAndUserIdNot(long movieId, long userId);
}
