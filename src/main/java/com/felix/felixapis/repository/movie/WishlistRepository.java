package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    void deleteByMovieIdAndUserId(long movieId, long userId);
}
