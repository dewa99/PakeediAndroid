package io.jagoketik.model;

public class results {
    private String title;
    private String urlimage;

    public results(String title, String urlimage) {
        this.title = title;
        this.urlimage = urlimage;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlimage() {
        return urlimage;
    }
}
