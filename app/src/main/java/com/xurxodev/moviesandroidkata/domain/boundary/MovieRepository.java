package com.xurxodev.moviesandroidkata.domain.boundary;

import com.xurxodev.moviesandroidkata.domain.entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();
}