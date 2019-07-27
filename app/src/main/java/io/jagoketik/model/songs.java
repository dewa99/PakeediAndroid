package io.jagoketik.model;

import java.util.ArrayList;

public class songs {

    private String title;
    private String artist;
    private String url;

    public String getUrl() {
        return url;
    }

    public songs(String title, String artist, String url) {
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }
}

