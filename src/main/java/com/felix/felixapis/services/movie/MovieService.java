package com.felix.felixapis.services.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.felixapis.models.movie.Movie;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MovieService {

    public Movie getJson(String movieRequest, String fileName, String videoFileName) throws IOException {
//        String coverImageName = StringUtils.cleanPath(file.getOriginalFilename());
//        String coverImageName = fileName;
        Movie newMovie = new Movie();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            newMovie = objectMapper.readValue(movieRequest, Movie.class);
        } catch (IOException e) {
            System.out.println("Error occurred while converting to JSON");
        }
        newMovie.setCoverImagePath(fileName);
        newMovie.setStreamMovieName(videoFileName);
        return newMovie;
    }
}
