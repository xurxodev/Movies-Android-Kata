package com.xurxodev.moviesandroidkata.model;

public class Movie {
    private String image;
    private String title;

    public Movie(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
