package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_year\", \"movie_restriction\", \"movie_length\", \"cover_image_serving_path\", \"cover_image_path\", \"stream_movie_name\", \"stream_movie_path\", \"upload_date\", \"likes_count\" from \"felix_movies\", \"category\" where \"category_name\" like ?1 and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereCategory(String category);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_year\", \"movie_restriction\", \"movie_length\", \"cover_image_serving_path\", \"cover_image_path\", \"stream_movie_name\", \"stream_movie_path\", \"upload_date\", \"likes_count\" from \"felix_movies\", \"watched_history\" where \"user_id\" = ?1 and \"watched_movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findWatchedHistoryWhereUserId(Long userId);

    List<Movie> searchMoviesByMovieNameContainingIgnoreCase(String searchText);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"cover_image_serving_path\", \"cover_image_path\", \"movie_length\", \"movie_restriction\", \"movie_year\", \"stream_movie_name\", \"stream_movie_path\", \"upload_date\", \"likes_count\" from \"felix_movies\", \"genre\" where upper(\"genre_name\") like %?1% and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereGenre(String searchText);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"cover_image_serving_path\", \"cover_image_path\", \"movie_length\", \"movie_restriction\", \"movie_year\", \"stream_movie_name\", \"stream_movie_path\", \"upload_date\", \"likes_count\" from \"felix_movies\", \"wishlist\" where \"user_id\" = ?1 and \"id\" = \"movie_id\"", nativeQuery = true)
    List<Movie> findWishlistWhereUserId(Long userId);


    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"cover_image_serving_path\", \"cover_image_path\", \"movie_length\", \"movie_restriction\", \"movie_year\", \"stream_movie_name\", \"stream_movie_path\", \"upload_date\", \"likes_count\" from \"felix_movies\", \"liked_movies\" where \"user_id\" = ?1 and \"id\" = \"liked_movie_id\"", nativeQuery = true)
    List<Movie> findLikedMoviesWhereUserId(Long userId);


    Movie findMovieById(Long movieId);

    Boolean existsById(long movieId);

    @Query(value = "select \"genre_name\" from \"genre\" where \"movie_id\" = ?1 ", nativeQuery = true)
    List<Genre> findGenreNamesFromMovieId(Long movieId);

}

