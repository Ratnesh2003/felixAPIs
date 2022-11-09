package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Genre;
import com.felix.felixapis.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
