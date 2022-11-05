package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_writers\" from \"felix_movies\", \"category\" where \"category_name\" like ?1 and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereCategory(String category);

//    @Query(value = "select distinct \"movie_name\" from \"felix_movies\", \"genre\" where (upper(\"movie_name\") like upper(?1%) and \"movie_id\" = \"id\") or (upper(\"genre_name\") like upper(?1%) and \"movie_id\" = \"id\")", nativeQuery = true)
//    List<Movie> findMoviesByMovieNameOrGenre(String searchText);

//    @Query(value = "select distinct \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_writers\" from \"felix_movies\", \"genre\" where (upper(\"movie_name\") like upper(\'ab%\') and \"id\" = \"movie_id\") or (upper(\"genre_name\") like upper(\'?1%\') and \"id\" = \"movie_id\")", nativeQuery = true)
//    List<Movie> searchMoviesByMovieNameOrGenre(String searchText);

    List<Movie> searchMoviesByMovieNameContainingIgnoreCase(String searchText);

    @Query(value = "select \"id\", \"movie_name\", \"movie_description\", \"movie_cast\", \"movie_writers\" from \"felix_movies\", \"genre\" where upper(\"genre_name\") like %?1% and \"movie_id\" = \"id\"", nativeQuery = true)
    List<Movie> findAllMoviesWhereGenre(String searchText);

//    List<Movie> searchMoviesByGenresContainingIgnoreCase(String searchText);


}
