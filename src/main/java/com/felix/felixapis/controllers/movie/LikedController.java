package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.movie.LikedMovies;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.repository.movie.LikedRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.movie.LikeUnlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


@RestController
@Transactional
public class LikedController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    GetDetailsFromUser getDetailsFromUser;

    @Autowired
    LikedRepository likedRepository;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    LikeUnlikeService likeUnlikeService;

    @PostMapping("/api/home/add-to-liked")
    public ResponseEntity<?> addToLiked(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        Movie likedMovieDetails = moviesRepository.findMovieById(wishlistRequest.getMovieId());
        LikedMovies likedMovie = new LikedMovies(wishlistRequest.getMovieId(), userId);

        if(likeUnlikeService.increaseLike(likedMovieDetails)) {
            likedRepository.save(likedMovie);
            return ResponseEntity.status(HttpStatus.OK).body("Liked");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occurred. Try again");
        }
    }

    @DeleteMapping("/api/home/delete-from-liked")
    public ResponseEntity<?> deleteFromLiked(@RequestParam @NotNull long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        Movie likedMovieDetails = moviesRepository.findMovieById(movieId);
        if(likeUnlikeService.decreaseLike(likedMovieDetails)) {
            likedRepository.deleteByLikedMovieIdAndUserId(movieId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("Unliked");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occurred. Try again");
        }


    }

}
