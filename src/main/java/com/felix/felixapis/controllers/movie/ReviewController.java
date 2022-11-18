package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.payload.request.movie.ReviewRequest;
import com.felix.felixapis.payload.response.ReviewResponse;
import com.felix.felixapis.services.movie.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ReviewController {

    final
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/movie/add-feedback")
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest reviewRequest, HttpServletRequest httpRequest) {
        return reviewService.addFeedback(reviewRequest, httpRequest);
    }

    @GetMapping("/api/movie/get-feedbacks")
    public ResponseEntity<List<ReviewResponse>> getReviews(@RequestParam long movieId, HttpServletRequest httpRequest) {
        return reviewService.getFeedback(movieId, httpRequest);
    }

    @DeleteMapping("/api/movie/delete-feedback")
    public ResponseEntity<?> deleteReview(@RequestParam long movieId,  HttpServletRequest httpRequest) {
        return reviewService.deleteFeedback(movieId, httpRequest);
    }

}
