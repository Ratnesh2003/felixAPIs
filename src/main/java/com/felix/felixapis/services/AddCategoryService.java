package com.felix.felixapis.services;

import com.felix.felixapis.models.movie.AddCategoryModel;
import com.felix.felixapis.repository.movie.AddCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddCategoryService {

    @Autowired
    AddCategoryRepository addCategoryRepository;
//    final AddCategoryModel addCategoryModel;
//
//    public AddCategoryService(AddCategoryModel addCategoryModel) {
//        this.addCategoryModel = addCategoryModel;
//    }

    public ResponseEntity<?>  addCategory(String categoryType){
      if(addCategoryRepository.findAddCategoryModelByCategoryType(categoryType)!=null) {
          return ResponseEntity.status(HttpStatus.CONFLICT).body("Category Already Exist");
      }
      else
      {
          AddCategoryModel newCategory = new AddCategoryModel(categoryType);
          addCategoryRepository.save(newCategory);
          return ResponseEntity.status(HttpStatus.OK).body("Added new category");
      }
    }
}
