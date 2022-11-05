package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_year\", \"movie_restriction\", \"movie_length\" from \"felix_movies\", \"category\" where \"category_name\" like ?1 and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereCategory(String category);

    @Query(value = "select \"movie_name\" from \"felix_movies\", \"watched_history\" where \"user_id\" = ?1 and \"id\" = \"watchedmovie_id\"", nativeQuery = true)
    List<Movie> findWatchedHistoryWhereUserId(Long userId);

    List<Movie> searchMoviesByMovieNameContainingIgnoreCase(String searchText);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_writers\" from \"felix_movies\", \"genre\" where upper(\"genre_name\") like %?1% and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereGenre(String searchText);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_writers\" from \"felix_movies\", \"wishlist\" where \"user_id\" = ?1 and \"id\" = \"movie_id\"", nativeQuery = true)
    List<Movie> findWishlistWhereUserId(Long userId);

}
