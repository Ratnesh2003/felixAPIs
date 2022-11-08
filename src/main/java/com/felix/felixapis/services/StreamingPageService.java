package com.felix.felixapis.services;

import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.response.MovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StreamingPageService {

    public ResponseEntity<MovieResponse> getStreamingPageDetails(Movie movieDetails, List<Genre> genres, HttpServletRequest httpRequest) {
        String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();
        String coverImageURL = baseURL + "/api/home/get-movie-cover/" + movieDetails.getCoverImagePath();
        movieDetails.setCoverImageServingPath(coverImageURL);
        String movieURL = baseURL + "/stream-movie/" + movieDetails.getStreamMovieName();
        movieDetails.setStreamMoviePath(movieURL);

        return ResponseEntity.status(HttpStatus.OK).body(new MovieResponse(
                movieDetails.getId(),
                movieDetails.getMovieName(),
                movieDetails.getMovieDescription(),
                movieDetails.getMovieCast(),
                movieDetails.getMovieYear(),
                movieDetails.getMovieRestriction(),
                movieDetails.getMovieLength(),
                movieDetails.getGenres(),
                movieDetails.getCoverImageServingPath(),
                movieDetails.getStreamMoviePath()
        ));

    }
}
