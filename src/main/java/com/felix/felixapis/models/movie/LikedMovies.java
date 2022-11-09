package com.felix.felixapis.models.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LikedMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likedId;

    private Long likedMovieId;

    private Long userId;

    public LikedMovies() {
    }

    public LikedMovies(Long likedMovieId, Long userId) {
        this.likedMovieId = likedMovieId;
        this.userId = userId;
    }

    public Long getLikedId() {
        return likedId;
    }

    public void setLikedId(Long likedId) {
        this.likedId = likedId;
    }

    public Long getLikedMovieId() {
        return likedMovieId;
    }

    public void setLikedMovieId(Long likedMovieId) {
        this.likedMovieId = likedMovieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
