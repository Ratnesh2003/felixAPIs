package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.FileUploadHelper;
import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.services.MovieService;
import com.felix.felixapis.services.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
public class MovieController {
    @Value("${project.image}")
    private String UPLOAD_DIR;
    private final MoviesRepository moviesRepository;

    private final SearchService searchService;

    private FileUploadHelper fileUploadHelper;

    private final MovieService movieService;

    private final ImageIDFromMovie imageIDFromMovie;

    public MovieController(MoviesRepository moviesRepository, SearchService searchService, MovieService movieService, FileUploadHelper fileUploadHelper, ImageIDFromMovie imageIDFromMovie) {
        this.moviesRepository = moviesRepository;
        this.searchService = searchService;
        this.movieService = movieService;
        this.fileUploadHelper = fileUploadHelper;
        this.imageIDFromMovie = imageIDFromMovie;
    }


    @PostMapping(value = "/api/admin/add-new-movie", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> addNewMovie(@RequestPart("movie") String moviesRequest, @RequestPart("coverImage")MultipartFile file) throws IOException {

        if(file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File cannot be empty");
        }
        if(!file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Image format must be JPEG only");
        }

        String fileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        fileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        boolean uploadStatus = fileUploadHelper.uploadFile(file, fileName);
        if(uploadStatus) {

            Movie newMovie = movieService.getJson(moviesRequest, fileName);
            moviesRepository.save(newMovie);
            return ResponseEntity.status(HttpStatus.OK).body("Movie uploaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("api/home/get-movie-cover/{coverImageName}")
    public void getMovieCover(@PathVariable("coverImageName") String coverImageName, HttpServletResponse response) throws IOException {
        String fullPath = UPLOAD_DIR + File.separator + coverImageName;
        InputStream is = new FileInputStream(fullPath);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
    }

    @GetMapping(value = "/api/home/movies-by-category")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getHomeMovies(@RequestParam String category, HttpServletRequest request) {

        List<Movie> moviesByCategory = moviesRepository.findAllMoviesWhereCategory(category);

        return imageIDFromMovie.getImageAndIdFromMovieModel(moviesByCategory, request);

    }

    @GetMapping("/api/home/search")
    public List<Movie> searchMovies(@RequestParam String searchText) {
        List<Movie> result = searchService.searchUsingMovieName(searchText);
        if(!result.isEmpty()) {
            return result;
        } else {
            return searchService.searchUsingGenreName(searchText.toUpperCase());
        }
    }
}
