package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

//    List<Genre> findGenresByMovieId(long movieId);
//        @Query(value = "delete from \"genre\" where \"movie_id\"= ?1")
//        List<Genre> deleteGenreByMovieId();
//        void
}
