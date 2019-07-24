package io.jagoketik.model;

public class result_album {
    private String title;
    private String id;
    private String imgSrc;

    public result_album(String title, String id, String imgSrc) {
        this.title = title;
        this.id = id;
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
