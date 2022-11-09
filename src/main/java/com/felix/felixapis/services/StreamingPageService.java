package com.felix.felixapis.services;

import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.response.MovieResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StreamingPageService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MoviesRepository moviesRepository;

    public ResponseEntity<MovieResponse> getStreamingPageDetails(Movie movieDetails, List<Genre> genres, HttpServletRequest httpRequest) {
        String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();
        String coverImageURL = baseURL + "/api/home/get-movie-cover/" + movieDetails.getCoverImagePath();
        movieDetails.setCoverImageServingPath(coverImageURL);
        String movieURL = baseURL + "/stream-movie/" + movieDetails.getStreamMovieName();
        movieDetails.setStreamMoviePath(movieURL);

        // Get wishlist and check whether already present or not
        Boolean addedToWishlist = false;
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        List<Movie> wishlist = moviesRepository.findWishlistWhereUserId(userId);

        if (wishlist.contains(movieDetails)) {
            addedToWishlist = true;
        }

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
                movieDetails.getStreamMoviePath(),
                addedToWishlist
        ));

    }
}
