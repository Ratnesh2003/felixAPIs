package com.felix.felixapis.services.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeUnlikeService {

    final
    MoviesRepository moviesRepository;

    public LikeUnlikeService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public Boolean increaseLike(Movie likedMovie) {
        int currentLikes = likedMovie.getLikesCount();
        likedMovie.setLikesCount(currentLikes + 1);
        moviesRepository.save(likedMovie);
        return true;
    }

    public Boolean decreaseLike(Movie likedMovie) {
        int currentLikes = likedMovie.getLikesCount();
        likedMovie.setLikesCount(currentLikes - 1);
        moviesRepository.save(likedMovie);
        return true;
    }
}
