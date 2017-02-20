package com.xurxodev.moviesandroidkata.domain.entity;

public class Movie {
    private String image;
    private String title;
    private String overview;

    public Movie(String image, String title, String overview) {
        this.image = image;
        this.title = title;
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
