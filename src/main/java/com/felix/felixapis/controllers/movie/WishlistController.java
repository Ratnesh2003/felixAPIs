package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.models.movie.Wishlist;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.repository.movie.WishlistRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WishlistController {

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

    public WishlistController(JwtUtil jwtUtil, UserRepository userRepository, WishlistRepository wishlistRepository, MoviesRepository moviesRepository, ImageIDFromMovie imageIDFromMovie) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.moviesRepository = moviesRepository;
        this.imageIDFromMovie = imageIDFromMovie;
    }

    @PostMapping("/api/home/add-to-wishlist")
    public String addToWishlist(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        Wishlist newMovie = new Wishlist(userId, wishlistRequest.getMovieId());
        wishlistRepository.save(newMovie);
        return "Added to your wishlist" ;
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
