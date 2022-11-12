package com.felix.felixapis.services.movie;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.models.movie.Reviews;
import com.felix.felixapis.payload.request.movie.ReviewRequest;
import com.felix.felixapis.payload.response.ReviewResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    final
    GetDetailsFromUser getDetailsFromUser;

    final
    ReviewRepository reviewRepository;

    final
    UserRepository userRepository;

    public ReviewService(GetDetailsFromUser getDetailsFromUser, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.getDetailsFromUser = getDetailsFromUser;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> addFeedback(ReviewRequest reviewRequest, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        User user = userRepository.findUserById(userId);
        Reviews newReview = new Reviews(
                reviewRequest.getRating(),
                reviewRequest.getReviewText(),
                reviewRequest.getMovieId(),
                userId,
                new Date(),
                user.getFirstName() + " " + user.getLastName(),
                user.getRole()
        );
        reviewRepository.save(newReview);
        return ResponseEntity.status(HttpStatus.OK).body("Review added.");
    }

    public ResponseEntity<List<ReviewResponse>> getFeedback(long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        Reviews userReview = reviewRepository.findByMovieIdAndUserId(movieId, userId);
        List<Reviews> reviews = reviewRepository.findByMovieIdAndUserIdNot(movieId, userId);
        reviews.add(0, userReview);

        List<ReviewResponse> reviewResponses = new ArrayList<>();

        for (Reviews review : reviews) {
            ReviewResponse response = new ReviewResponse(
                    review.getFullName(),
                    review.getRole(),
                    review.getReviewText(),
                    review.getRating(),
                    review.getDateAdded()
            );
            reviewResponses.add(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(reviewResponses);

    }

}
