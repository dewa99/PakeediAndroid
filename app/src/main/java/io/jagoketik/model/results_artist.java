package io.jagoketik.model;

public class results_artist {
    private String name;
    private String url;
    private String id;

    public results_artist(String name, String url, String id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
