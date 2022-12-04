package com.felix.felixapis.services.admin;

import com.felix.felixapis.models.movie.AllCategory;
import com.felix.felixapis.repository.movie.AllCategoryRepository;
import com.felix.felixapis.services.impl.NewAdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AllCategoryService {

    final
    AllCategoryRepository allCategoryRepository;

    final
    NewAdminServiceImpl newAdminServiceImpl;

    public AllCategoryService(AllCategoryRepository allCategoryRepository, NewAdminServiceImpl newAdminServiceImpl) {
        this.allCategoryRepository = allCategoryRepository;
        this.newAdminServiceImpl = newAdminServiceImpl;
    }

    public ResponseEntity<?> addNewCategory(String allCategoryName, HttpServletRequest httpRequest) {
        if(newAdminServiceImpl.checkAdmin(httpRequest)) {

            if (allCategoryRepository.existsByAllCategoryName(allCategoryName)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This category already exists.");
            } else {
                AllCategory newAllCategory = new AllCategory(allCategoryName);
                allCategoryRepository.save(newAllCategory);
                return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to perform this action");
        }
    }


    public ResponseEntity<List<AllCategory>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(allCategoryRepository.findAll());

    }
}
