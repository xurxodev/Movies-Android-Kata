package com.xurxodev.moviesandroidkata.view.boundary;

import android.content.Context;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();
}
