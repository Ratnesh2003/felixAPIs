package com.felix.felixapis.models.movie;

import javax.persistence.*;

@Entity
public class MovieCover {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coverId;

    private String coverName;

    private String coverType;

    @Lob
    private byte[] coverData;

    private Long movieId;

    public MovieCover() {
    }

    public MovieCover(String coverName, String coverType, byte[] coverData, Long movieId) {
        this.coverName = coverName;
        this.coverType = coverType;
        this.coverData = coverData;
        this.movieId = movieId;
    }

    public Long getCoverId() {
        return coverId;
    }

    public void setCoverId(Long coverId) {
        this.coverId = coverId;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public byte[] getCoverData() {
        return coverData;
    }

    public void setCoverData(byte[] coverData) {
        this.coverData = coverData;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
