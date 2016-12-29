package com.xurxodev.moviesandroidkata.presenter.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();
}