package io.jagoketik.model;

public class results_artist {
    private String name;
    private String url;

    public results_artist(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
