package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.AllCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllCategoryRepository extends JpaRepository<AllCategory, Long> {
    boolean existsByAllCategoryName(String name);

}
