package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
