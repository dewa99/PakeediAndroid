package io.jagoketik.model;

import java.util.ArrayList;

public class songs {

    private String title;
    private String artist;
    private String url;

    public songs(String title, String artist, String url) {
        this.title = title;
        this.artist = artist;
        this.url = url;
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


}

