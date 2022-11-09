package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.models.movie.Wishlist;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.repository.movie.WishlistRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
public class WishlistController {
    final
    GetDetailsFromUser getDetailsFromUser;

    final
    JwtUtil jwtUtil;

    final
    UserRepository userRepository;

    final
    WishlistRepository wishlistRepository;

    final
    MoviesRepository moviesRepository;

    final
    ImageIDFromMovie imageIDFromMovie;



    public WishlistController(GetDetailsFromUser getDetailsFromUser, JwtUtil jwtUtil, UserRepository userRepository, WishlistRepository wishlistRepository, MoviesRepository moviesRepository, ImageIDFromMovie imageIDFromMovie) {
        this.getDetailsFromUser = getDetailsFromUser;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.moviesRepository = moviesRepository;
        this.imageIDFromMovie = imageIDFromMovie;
    }

    @PostMapping("/api/home/add-to-wishlist")
    public ResponseEntity<?> addToWishlist(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        Wishlist newMovie = new Wishlist(userId, wishlistRequest.getMovieId());
        wishlistRepository.save(newMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Added to your wishlist");
    }

    @DeleteMapping("/api/home/remove-from-wishlist")
    public ResponseEntity<?> removeFromWishlist(@RequestParam long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        wishlistRepository.deleteByMovieIdAndUserId(movieId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Removed from wishlist");
    }

    @GetMapping("/api/home/wishlist")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getWishlist(HttpServletRequest httpRequest) {

        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        List<Movie> wishlist = moviesRepository.findWishlistWhereUserId(userId);
        return imageIDFromMovie.getImageAndIdFromMovieModel(wishlist, httpRequest);
    }

}
