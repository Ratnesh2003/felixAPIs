package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.movie.LikedMovies;
import com.felix.felixapis.payload.request.movie.WishlistRequest;
import com.felix.felixapis.repository.movie.LikedRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;


@RestController
@Transactional
public class LikedController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    GetDetailsFromUser getDetailsFromUser;

    @Autowired
    LikedRepository likedRepository;

    @PostMapping("/api/home/add-to-liked")
    public ResponseEntity<?> addToLiked(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        LikedMovies likedMovie = new LikedMovies(wishlistRequest.getMovieId(), userId);
        likedRepository.save(likedMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Liked");
    }

    @DeleteMapping("/api/home/delete-from-liked")
    public ResponseEntity<?> deleteFromLiked(@RequestParam long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        likedRepository.deleteByLikedMovieIdAndUserId(movieId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Unliked");

    }

}
