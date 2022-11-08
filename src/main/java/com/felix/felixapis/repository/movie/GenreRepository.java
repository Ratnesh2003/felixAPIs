package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
