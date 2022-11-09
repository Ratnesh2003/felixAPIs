package com.felix.felixapis.repository.movie;

import com.felix.felixapis.models.movie.AddCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddCategoryRepository extends JpaRepository<AddCategoryModel, Long> {
    AddCategoryModel findAddCategoryModelByCategoryType(String categoryType) ;
}
