package com.felix.felixapis.services.admin;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.repository.movie.MoviesRepository;
import com.felix.felixapis.services.impl.NewAdminServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DeleteMovieService {
//    @Value("${project.image}") //local
    @Value(("/app/target/classes/static/"))
    private String UPLOAD_DIR;

    final
    UserRepository userRepository;

    final
    GetDetailsFromUser getDetailsFromUser;

    final
    NewAdminServiceImpl newAdminServiceImpl;

    final
    MoviesRepository moviesRepository;

    public DeleteMovieService(UserRepository userRepository, GetDetailsFromUser getDetailsFromUser, NewAdminServiceImpl newAdminServiceImpl, MoviesRepository moviesRepository) {
        this.userRepository = userRepository;
        this.getDetailsFromUser = getDetailsFromUser;
        this.newAdminServiceImpl = newAdminServiceImpl;
        this.moviesRepository = moviesRepository;
    }

    public ResponseEntity<?> deleteMovie(long movieId, HttpServletRequest httpRequest) throws IOException {
        if (newAdminServiceImpl.checkAdmin(httpRequest)) {
            Movie movieToBeDeleted = moviesRepository.findMovieById(movieId);
            movieToBeDeleted.getCategories().clear();
            movieToBeDeleted.getGenres().clear();
            moviesRepository.delete(movieToBeDeleted);


            String coverFilePath = UPLOAD_DIR + File.separator + movieToBeDeleted.getCoverImagePath();
            String videoFilePath = UPLOAD_DIR + File.separator + movieToBeDeleted.getStreamMovieName();

            Files.delete(Paths.get(coverFilePath));
            Files.delete(Paths.get(videoFilePath));

            return ResponseEntity.status(HttpStatus.OK).body("Movie " + movieToBeDeleted.getMovieName() + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You must be an ADMIN to perform this action.");
        }

    }


}
