package io.jagoketik.model;

public class results {
    private String title;
    private String urlimage;
    private String id;

    public results(String title, String urlimage, String id) {
        this.title = title;
        this.urlimage = urlimage;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlimage() {
        return urlimage.replace("id","100");
    }

    public String getId() {
        return id;
    }
}
