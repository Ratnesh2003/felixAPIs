package com.felix.felixapis.helper;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.payload.response.SearchMovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageIDFromMovie {
    public ResponseEntity<List<MoviesWithCategoryResponse>> getImageAndIdFromMovieModel(List<Movie> movieList, HttpServletRequest httpRequest) {
        List<MoviesWithCategoryResponse> moviesImageAndID = new ArrayList<>();

        for(Movie movie : movieList) {
            String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();
//            String coverImageURL = baseURL + "/api/home/get-movie-cover/" + movie.getCoverImagePath();
            String coverImageURL = baseURL + "/stream-movie/" + movie.getCoverImagePath();
            movie.setCoverImageServingPath(coverImageURL);
            MoviesWithCategoryResponse moviesWithCategory = new MoviesWithCategoryResponse(
                    movie.getId(),
                    movie.getCoverImageServingPath()
            );
            moviesImageAndID.add(moviesWithCategory);
        }
        return ResponseEntity.status(HttpStatus.OK).body(moviesImageAndID);
    }

    public ResponseEntity<List<SearchMovieResponse>> getImageIdNameFromMovieModel(List<Movie> movieList, HttpServletRequest httpRequest) {
        List<SearchMovieResponse> moviesImageIDName = new ArrayList<>();

        for(Movie movie : movieList) {
            String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();
//            String coverImageURL = baseURL + "/api/home/get-movie-cover/" + movie.getCoverImagePath();
            String coverImageURL = baseURL + "/stream-movie/" + movie.getCoverImagePath();
            movie.setCoverImageServingPath(coverImageURL);
            SearchMovieResponse searchMovieResponse = new SearchMovieResponse(
                    movie.getId(),
                    movie.getMovieName(),
                    movie.getCoverImageServingPath()
            );
            moviesImageIDName.add(searchMovieResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(moviesImageIDName);
    }
}
