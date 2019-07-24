package io.jagoketik.model;

public class results_artist {
    private String name;
    private String url;
    private String id;
    private String urlimage;

    public results_artist(String name, String url, String id, String urlimage) {
        this.name = name;
        this.url = url;
        this.id = id;
        this.urlimage = urlimage;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getName() {
        return name.trim();
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}
