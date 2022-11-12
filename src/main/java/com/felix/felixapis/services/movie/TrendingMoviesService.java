package com.felix.felixapis.services.movie;

import com.felix.felixapis.models.movie.Movie;
import com.felix.felixapis.repository.movie.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrendingMoviesService {



    final
    MoviesRepository moviesRepository;
    Date currentDate = new Date();

    public TrendingMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> getTrendingMovies() {
        List<Movie> allMovies = moviesRepository.findAll();
        List<Movie> trendingMovies = new ArrayList<>();

        List<float[]> listOfMoviesWithLikeRatios = new ArrayList<float[]>();


        for(Movie movie : allMovies) {
            float[] individualMovieRatios = new float[2];
            individualMovieRatios[0] = movie.getId();
            individualMovieRatios[1] =  (float)movie.getLikesCount() / (currentDate.getTime() - movie.getUploadDate().getTime());
            listOfMoviesWithLikeRatios.add(individualMovieRatios);
        }

        Comparator<float[]> compareByLikedRatio = new Comparator<float[]>() {
            @Override
            public int compare(float[] o1, float[] o2) {
                return Float.compare(o2[1], o1[1]);
            }
        };
        listOfMoviesWithLikeRatios.sort(compareByLikedRatio);

        for (float[] movie : listOfMoviesWithLikeRatios) {
            trendingMovies.add(moviesRepository.findMovieById((long)movie[0]));
        }

        return trendingMovies.stream().limit(6).collect(Collectors.toList());

    }
}
