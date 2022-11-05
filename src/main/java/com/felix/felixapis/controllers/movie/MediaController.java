package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.payload.response.MediaResponse;
import com.felix.felixapis.services.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin("http://localhost:8080")
public class MediaController {
    @Autowired
    MediaStorageService mediaStorageService;

    @PostMapping("/api/admin/upload-media")
    public ResponseEntity<MediaResponse> uploadMedia(@RequestParam("video")MultipartFile file) {
        String message = "";

        try {
            mediaStorageService.upload(file);
            message = "Video uploaded successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new MediaResponse(message));
        } catch (Exception e) {
            message = "Could not upload the video";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MediaResponse(message));
        }
    }

    @GetMapping("/api/home/video")
    @ResponseBody
    public ResponseEntity<Resource> getMovieVideo(@RequestParam String fileName) {
        Resource file = mediaStorageService.download(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getFilename() + "\"").body(file);
    }
}
