package com.xurxodev.moviesandroidkata.data;

import static android.R.attr.name;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.domain.entity.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.repository.MovieRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class DiskMovieRepository implements MovieRepository {

    private Context applicationContext;
    private Gson gson;

    @Inject
    public DiskMovieRepository(Application applicationContext, Gson gson){
       this.applicationContext = applicationContext;
       this.gson = gson;
    }

    @Override
    public List<Movie> getMovies() {
        Movie[] movies = extractMovies();

        simulateDelay();

        return Arrays.asList(movies);
    }

    @Override
    public Movie getMovieByTitle(String movieTitle) {
        Movie movieResult = null;
        Movie[] movies = extractMovies();

        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                movieResult = movie;
            }
        }

        simulateDelay();

        return movieResult;
    }

    private Movie[] extractMovies() {
        String jsonString = null;

        try {
            InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.movies);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            jsonString = new String(b);
        } catch (IOException e){
            //TODO: fix io exception
        }

        return gson.fromJson(jsonString, Movie[].class);
    }

    private void simulateDelay(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
