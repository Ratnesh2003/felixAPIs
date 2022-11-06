package com.felix.felixapis.models;




import javax.persistence.*;

@Entity

    public class WatchedHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchedId;

    private Long watchedMovieId;

    private Long userId;

    public WatchedHistory(Long userId,Long watchedMovieId) {
        this.userId = userId;
        this.watchedMovieId= watchedMovieId;
    }

    public WatchedHistory() {

    }

    public Long getWatchedId() {
        return watchedId;
    }

    public void setWatchedId(Long watchedId) {
        this.watchedId = watchedId;
    }

    public Long getMovieId() {
        return watchedMovieId;
    }

    public void setMovieId(Long movieId) {
        this.watchedMovieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

