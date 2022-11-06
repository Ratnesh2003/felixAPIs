package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.FileUploadHelper;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    @Value("${project.image}")
    private String UPLOAD_DIR;
    private final MoviesRepository moviesRepository;

    private final SearchService searchService;

    private FileUploadHelper fileUploadHelper;

    private final MovieService movieService;

    public MovieController(MoviesRepository moviesRepository, SearchService searchService, MovieService movieService, FileUploadHelper fileUploadHelper) {
        this.moviesRepository = moviesRepository;
        this.searchService = searchService;
        this.movieService = movieService;
        this.fileUploadHelper = fileUploadHelper;
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

        boolean uploadStatus = fileUploadHelper.uploadFile(file);
        if(uploadStatus) {
            Movie newMovie = movieService.getJson(moviesRequest, file);
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
        List<MoviesWithCategoryResponse> moviesWithCategoryResponses = new ArrayList<>();

        for (Movie movie : moviesByCategory) {
            String baseURL = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
            String coverImageURL = baseURL + "/api/home/get-movie-cover/" + movie.getCoverImagePath();
            movie.setCoverImageServingPath(coverImageURL);
            MoviesWithCategoryResponse moviesWithCategory = new MoviesWithCategoryResponse(
                    movie.getId(),
                    movie.getCoverImageServingPath()
            );
            moviesWithCategoryResponses.add(moviesWithCategory);

        }
        return ResponseEntity.status(HttpStatus.OK).body(moviesWithCategoryResponses);
    }

    @GetMapping("/api/home/movies")
    public List<Movie> getMovies(@RequestParam String category) {
        return moviesRepository.findAllMoviesWhereCategory(category);
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
