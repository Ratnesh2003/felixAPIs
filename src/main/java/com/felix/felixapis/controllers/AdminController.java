package com.felix.felixapis.controllers;

import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.models.movie.AllCategory;
import com.felix.felixapis.payload.request.auth.ConfirmOTPRequest;
import com.felix.felixapis.payload.request.movie.AllCategoryRequest;
import com.felix.felixapis.payload.request.movie.MoviesRequest;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.services.admin.AllCategoryService;
import com.felix.felixapis.services.admin.DeleteMovieService;
import com.felix.felixapis.services.admin.NewAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewAdminService newAdminService;

    @Autowired
    DeleteMovieService deleteMovieService;

    @Autowired
    AllCategoryService allCategoryService;

    @PutMapping("/api/admin/make-new-admin")
    public ResponseEntity<?> makeNewAdmin(@RequestBody ConfirmOTPRequest emailObject, HttpServletRequest httpRequest) {
        return newAdminService.makeNewAdmin(emailObject.getEmail(), httpRequest);
    }

    @DeleteMapping("/api/admin/delete-movie")
    public ResponseEntity<?> deleteMovie(@RequestParam long movieId, HttpServletRequest httpRequest) throws IOException {
        return deleteMovieService.deleteMovie(movieId, httpRequest);
    }

    @PostMapping("/api/admin/add-new-category")
    public ResponseEntity<?> addNewCategory(@RequestBody AllCategoryRequest allCategoryRequest, HttpServletRequest httpRequest) {
        return allCategoryService.addNewCategory(allCategoryRequest.getAllCategoryName(), httpRequest);
    }

    @GetMapping("/api/home/get-all-categories")
    public ResponseEntity<List<AllCategory>> getAllCategories() {
        return allCategoryService.getAllCategory();
    }

}
