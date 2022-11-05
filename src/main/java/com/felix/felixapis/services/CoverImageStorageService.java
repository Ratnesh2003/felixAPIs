package com.felix.felixapis.services;


import com.felix.felixapis.models.movie.MovieCover;
import com.felix.felixapis.repository.movie.MovieCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CoverImageStorageService {

    @Autowired
    private MovieCoverRepository movieCoverRepository;

    public String uploadCoverImage(MultipartFile file, long movieId) throws IOException {
        MovieCover movieCover = new MovieCover(
                file.getName(),
                file.getContentType(),
                CoverImageUtil.compressImage(file.getBytes()),
                movieId

        );
        if(movieCover != null) {
            movieCoverRepository.save(movieCover);
            return "Cover image uploaded";
        }
        return "Some error occurred";
    }

    public byte[] downloadCoverImage(long movieId) {
        Optional<MovieCover> coverImageData =  movieCoverRepository.findByMovieId(movieId);
        byte[] coverImage =  CoverImageUtil.decompressImage(coverImageData.get().getCoverData());
        return coverImage;
    }
}
