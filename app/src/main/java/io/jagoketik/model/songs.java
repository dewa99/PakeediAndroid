package io.jagoketik.model;

import java.util.ArrayList;

public class songs {
    private int id;
    private String title;
    ArrayList<Object> songs = new ArrayList<>();

    public songs(int id, String title) {
        this.id = id;
        this.title = title;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Object> getSongs() {
        return songs;
    }
}
