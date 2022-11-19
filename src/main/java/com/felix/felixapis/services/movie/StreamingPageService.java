package com.felix.felixapis.services.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.LikedMovies;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.models.movie.Reviews;
import com.felix.felixapis.payload.response.MovieResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.LikedRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.repository.movie.ReviewRepository;
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

    @Autowired
    GetDetailsFromUser getDetailsFromUser;

    @Autowired
    LikedRepository likedRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;


    public ResponseEntity<MovieResponse> getStreamingPageDetails(Movie movieDetails, HttpServletRequest httpRequest) {
        String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();
        String coverImageURL = baseURL + "/stream-movie/" + movieDetails.getCoverImagePath();
        movieDetails.setCoverImageServingPath(coverImageURL);
        String movieURL = baseURL + "/stream-movie/" + movieDetails.getStreamMovieName();
        movieDetails.setStreamMoviePath(movieURL);

        long userId = getDetailsFromUser.getUserId(httpRequest);

        // Get wishlist and check whether already present or not
        Boolean addedToWishlist = false;
        List<Movie> wishlist = moviesRepository.findWishlistWhereUserId(userId);

        // Get LikedMovies and check whether liked or not
        Boolean liked = false;
        List<Movie> likedMovies = moviesRepository.findLikedMoviesWhereUserId(userId);

        Boolean reviewed = false;

        if (wishlist.contains(movieDetails)) {
            addedToWishlist = true;
        }

        if (likedMovies.contains(movieDetails)) {
            liked = true;
        }

        if (reviewService.checkFeedbackExistence(movieDetails.getId(), httpRequest)) {
            reviewed = true;
        }

        float movieRating = getRatingOfMovie(movieDetails.getId());

        int totalReviews = reviewRepository.countByMovieId(movieDetails.getId());



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
                addedToWishlist,
                liked,
                String.format("%.1f", movieRating),
                totalReviews,
                reviewed,
                movieDetails.getCategories()
                ));

    }


    public float getRatingOfMovie(long movieId) {
        List<Reviews> movieReview = reviewRepository.findAllByMovieId(movieId);
        float rating = 0;
        for (Reviews review : movieReview) {
            rating += review.getRating();
        }

//        rating = Math.round(rating / movieReview.size());

        rating /= movieReview.size();
        return rating;

    }
}
