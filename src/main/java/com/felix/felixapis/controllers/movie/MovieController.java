package com.felix.felixapis.controllers.movie;

import com.felix.felixapis.helper.FileUploadHelper;
import com.felix.felixapis.helper.ImageIDFromMovie;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.payload.request.movie.EditMovieRequest;
import com.felix.felixapis.payload.response.MovieResponse;
import com.felix.felixapis.payload.response.MoviesWithCategoryResponse;
import com.felix.felixapis.payload.response.SearchMovieResponse;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.services.movie.EditMovieService;
import com.felix.felixapis.services.movie.MovieService;
import com.felix.felixapis.services.movie.SearchService;
import com.felix.felixapis.services.movie.TrendingMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    @Autowired
    EditMovieService editMovieService;
//    @Value("${project.image}") //original
    @Value(("/app/target/classes/static"))
    private String UPLOAD_DIR;
    private final MoviesRepository moviesRepository;

    private final SearchService searchService;

    private final FileUploadHelper fileUploadHelper;

    private final MovieService movieService;

    private final ImageIDFromMovie imageIDFromMovie;

    private final TrendingMoviesService trendingMoviesService;

    public MovieController(MoviesRepository moviesRepository, SearchService searchService, MovieService movieService, FileUploadHelper fileUploadHelper, ImageIDFromMovie imageIDFromMovie, TrendingMoviesService trendingMoviesService) {
        this.moviesRepository = moviesRepository;
        this.searchService = searchService;
        this.movieService = movieService;
        this.fileUploadHelper = fileUploadHelper;
        this.imageIDFromMovie = imageIDFromMovie;
        this.trendingMoviesService = trendingMoviesService;
    }


    @PostMapping(value = "/api/admin/add-new-movie", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> addNewMovie(@RequestPart("movie") String moviesRequest, @RequestPart("coverImage")MultipartFile file, @RequestPart("movieVideo")MultipartFile video) throws IOException {

        if(file.isEmpty() || video.isEmpty()) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File cannot be empty");
        }
        if(!(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(), "image/png"))) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Image format must be JPEG only");
        }
        if(!Objects.equals(video.getContentType(), "video/mp4")) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Incorrect video format");
        }

        String videoFileName = video.getOriginalFilename();
        String fileName = file.getOriginalFilename();

        String randomIdVideo = UUID.randomUUID().toString();
        String randomId = UUID.randomUUID().toString();

        videoFileName = randomIdVideo.concat(videoFileName.substring(videoFileName.lastIndexOf(".")));
        fileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        boolean uploadStatus = fileUploadHelper.uploadFile(file, fileName);
        boolean videoUploadStatus = fileUploadHelper.uploadFile(video, videoFileName);
        if(uploadStatus && videoUploadStatus) {

            Movie newMovie = movieService.getJson(moviesRequest, fileName, videoFileName);
            moviesRepository.save(newMovie);
            return ResponseEntity.status(HttpStatus.OK).body("Movie uploaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    

    @GetMapping(value = "/api/home/movies-by-category")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getHomeMovies(@RequestParam String category, HttpServletRequest request) {

        List<Movie> moviesByCategory = moviesRepository.findAllMoviesWhereCategory(category.toLowerCase());

        return imageIDFromMovie.getImageAndIdFromMovieModel(moviesByCategory, request);
    }

    @GetMapping(value = "/api/home/limited-movies-by-category")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getLimitedHomeMovies(@RequestParam String category, HttpServletRequest request) {
        List<Movie> moviesByCategory = moviesRepository.findAllMoviesWhereCategory(category.toLowerCase());
        List<Movie> limitedMoviesByCategory = moviesByCategory.stream().limit(5).collect(Collectors.toList());
        return imageIDFromMovie.getImageAndIdFromMovieModel(limitedMoviesByCategory, request);
    }

    @GetMapping("/api/home/trending")
    public ResponseEntity<List<MoviesWithCategoryResponse>> getTrendingMovies(HttpServletRequest request) {
        List<Movie> trendingMovies = trendingMoviesService.getTrendingMovies();
        return imageIDFromMovie.getImageAndIdFromMovieModel(trendingMovies, request);
    }

    @GetMapping("/api/home/search")
    public ResponseEntity<List<SearchMovieResponse>> searchMovies(@RequestParam String searchText, HttpServletRequest httpRequest) {
        List<Movie> result = searchService.searchUsingMovieName(searchText);
        if(!result.isEmpty()) {
            return imageIDFromMovie.getImageIdNameFromMovieModel(result, httpRequest);
        } else {
            return imageIDFromMovie.getImageIdNameFromMovieModel(searchService.searchUsingGenreName(searchText.toUpperCase()), httpRequest);
        }
    }
    @GetMapping("/api/home/get-movie-details")
    public MovieResponse getMovieDetails(@RequestParam long movieId){
        return editMovieService.getMovieDetails(movieId);
    }
    @PutMapping("/api/home/edit-movie")
    public ResponseEntity<?> editMovie(@RequestBody EditMovieRequest editMovieRequest) {
//        return editMovieService.editMovieService(editMovieRequest.getNewMovieName(), editMovieRequest.getNewMovieDescription(),
//                editMovieRequest.getNewGenre(),editMovieRequest.getNewMovieYear());
        return editMovieService.editMovie(editMovieRequest);
    }
}
