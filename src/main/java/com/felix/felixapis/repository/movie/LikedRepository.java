package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.LikedMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedRepository extends JpaRepository<LikedMovies, Long> {
    void deleteByLikedMovieIdAndUserId(long movieId, long userId);
}
