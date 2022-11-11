package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.models.movie.AddCategoryModel;
import com.felix.felixapis.models.movie.Category;
import com.felix.felixapis.payload.request.CategoryTypeRequest;
import com.felix.felixapis.repository.movie.AddCategoryRepository;
import com.felix.felixapis.services.AddCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddCategoryController {
    @Autowired
    AddCategoryService addCategoryService;

    @Autowired
    AddCategoryRepository addCategoryRepository;

    @PostMapping("/api/admin/add-new-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryTypeRequest categoryRequest){
       return addCategoryService.addCategory(categoryRequest.getCategoryType());
    }
    @GetMapping("/api/admin/get-all-category")
    public List<AddCategoryModel> AllCategory(){
        return addCategoryService.getAllCategories();
}
}
