package com.felix.felixapis.services.movie;

import com.felix.felixapis.TimeGranularity;
import com.felix.felixapis.payload.request.movie.ReviewRequest;
import com.felix.felixapis.payload.response.ReviewResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface ReviewService {

    ResponseEntity<?> addFeedback(ReviewRequest reviewRequest, HttpServletRequest httpRequest);

    ResponseEntity<List<ReviewResponse>> getFeedback(long movieId, HttpServletRequest httpRequest);

    Boolean checkFeedbackExistence(long movieId, HttpServletRequest httpRequest);

    ResponseEntity<?> deleteFeedback(long movieId, HttpServletRequest httpRequest);

}
