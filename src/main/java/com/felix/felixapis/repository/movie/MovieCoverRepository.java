package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.MovieCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieCoverRepository extends JpaRepository<MovieCover, Long> {

//    Optional<MovieCover> findByCoverName(Long aLong);
    Optional<MovieCover> findByMovieId(Long movieId);
}
