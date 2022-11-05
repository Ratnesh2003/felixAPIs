package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.models.movie.Wishlist;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.repository.movie.WishlistRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WishlistController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    MoviesRepository moviesRepository;

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
    public List<Movie> getWishlist(HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        long userId = userRepository.findUserByEmailIgnoreCase(email).getId();
        List<Movie> wishlist = moviesRepository.findWishlistWhereUserId(userId);
        return wishlist;
    }
}
