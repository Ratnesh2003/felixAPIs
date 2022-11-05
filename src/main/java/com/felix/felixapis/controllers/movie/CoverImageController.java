package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.payload.request.movie.MovieCoverRequest;
import com.felix.felixapis.services.CoverImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class CoverImageController {

    private final CoverImageStorageService service;

    public CoverImageController(CoverImageStorageService service) {
        this.service = service;
    }

    @PostMapping("/api/admin/add-cover-image")
    public ResponseEntity<?> uploadCoverImage(@RequestParam("coverImage")MultipartFile file, MovieCoverRequest request) throws IOException {
        String uploadCoverImage =  service.uploadCoverImage(file, request.getMovieId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadCoverImage);
    }

    @GetMapping("/api/home/get-cover-image")
    public ResponseEntity<?> downloadCoverImage(@RequestParam("movieId") Long movieId) {
        byte[] coverImageData = service.downloadCoverImage(movieId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(coverImageData);
    }
}
