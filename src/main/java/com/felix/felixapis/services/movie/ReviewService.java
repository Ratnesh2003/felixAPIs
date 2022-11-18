package com.felix.felixapis.services.movie;

import com.felix.felixapis.TimeGranularity;
import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.models.movie.Reviews;
import com.felix.felixapis.payload.request.movie.ReviewRequest;
import com.felix.felixapis.payload.response.ReviewResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.repository.movie.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

    final MoviesRepository moviesRepository;

    public ReviewService(GetDetailsFromUser getDetailsFromUser, ReviewRepository reviewRepository, UserRepository userRepository, MoviesRepository moviesRepository) {
        this.getDetailsFromUser = getDetailsFromUser;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.moviesRepository = moviesRepository;
    }



    public ResponseEntity<?> addFeedback(ReviewRequest reviewRequest, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);

        if (checkMovieExistence(reviewRequest.getMovieId())) {
            if (checkFeedbackExistence(reviewRequest.getMovieId(), httpRequest)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("You have already added a feedback.");
            } else {
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
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }

    public ResponseEntity<List<ReviewResponse>> getFeedback(long movieId, HttpServletRequest httpRequest) {

        if (checkNullFeedback(movieId)) {
            long userId = getDetailsFromUser.getUserId(httpRequest);
            Reviews userReview = reviewRepository.findByMovieIdAndUserId(movieId, userId);
            List<Reviews> reviews = reviewRepository.findByMovieIdAndUserIdNot(movieId, userId);
            reviews.add(0, userReview);

            List<ReviewResponse> reviewResponses = new ArrayList<>();


            for (Reviews review : reviews) {
                long differenceInSeconds = (new Date().getTime() - review.getDateAdded().getTime()) / 1000;
//                Date differenceInTime = new Date(differenceInSeconds);
                TimeGranularity timeFormat = TimeGranularity.SECONDS;

                if (differenceInSeconds >= 60 && differenceInSeconds < 3600 ) {
                    timeFormat = TimeGranularity.MINUTES;
                }
                if (differenceInSeconds >= 3600 && differenceInSeconds < 86400 ) {
                    timeFormat = TimeGranularity.HOURS;
                }
                if (differenceInSeconds >= 86400 && differenceInSeconds < 2678400 ) {
                    timeFormat = TimeGranularity.DAYS;
                }
                if (differenceInSeconds >= 2678400 && differenceInSeconds < 31536000 ) {
                    timeFormat = TimeGranularity.MONTHS;
                }
                if (differenceInSeconds >= 31536000) {
                    timeFormat = TimeGranularity.YEARS;
                }

                String timeAgo = calculateTimeAgoByTimeGranularity(review.getDateAdded(), timeFormat);
                if (timeAgo.startsWith("1 ")) {
                    timeAgo = timeAgo.replace("s ago", " ago");
                }
                ReviewResponse response = new ReviewResponse(
                        review.getFullName(),
                        review.getRole(),
                        review.getReviewText(),
                        review.getRating(),
                        timeAgo
                );
                reviewResponses.add(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(reviewResponses);
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(null);
        }
    }

    public Boolean checkFeedbackExistence(long movieId, HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        return reviewRepository.existsByMovieIdAndUserId(movieId, userId);
    }

    public Boolean checkMovieExistence(long movieId) {
        return moviesRepository.existsById(movieId);
    }

    public Boolean checkNullFeedback(long movieId) {
        return reviewRepository.existsByMovieId(movieId);
    }

    @Transactional
    public ResponseEntity<?> deleteFeedback(long movieId, HttpServletRequest httpRequest) {
        if (checkMovieExistence(movieId)) {
            if (checkFeedbackExistence(movieId, httpRequest)) {
                long userId = getDetailsFromUser.getUserId(httpRequest);
                reviewRepository.deleteByMovieIdAndUserId(movieId, userId);
                return ResponseEntity.status(HttpStatus.OK).body("Review deleted");

            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User has not given any feedback yet");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }

    private static String calculateTimeAgoByTimeGranularity(Date pastTime, TimeGranularity timeGranularity) {
        Date date = new Date();
        long timeDifferenceInMillis = date.getTime() - pastTime.getTime();
        return timeDifferenceInMillis / timeGranularity.toMillis() + " " + timeGranularity.name().toLowerCase() + " ago";
    }

}
