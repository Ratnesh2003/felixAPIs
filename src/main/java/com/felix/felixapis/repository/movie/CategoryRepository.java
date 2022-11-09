package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
