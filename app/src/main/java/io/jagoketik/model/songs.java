package io.jagoketik.model;

import java.util.ArrayList;

public class songs {

    private String title;
    private String artist;
    private String url;
    private String image;

    public songs(String title, String artist, String url, String image) {
        this.title = title;
        this.artist = artist;
        this.url = url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image.replace("%d" , "100");
    }
}

