package com.felix.felixapis.models.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mediaId;
    private String mediaName;
    private String mediaURL;

    public Media() {
    }

    public Media(String mediaName, String mediaURL) {
        this.mediaName = mediaName;
        this.mediaURL = mediaURL;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }
}
