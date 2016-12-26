package com.xurxodev.moviesandroidkata.data;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskMovieRepository {
    private Context applicationContext;

    public DiskMovieRepository(Application applicationContext){
        this.applicationContext = applicationContext;
    }

    public List<Movie> getMovies() {
        String jsonString = null;

        try {
            InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.movies);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            jsonString = new String(b);
        } catch (IOException e){
            //TODO: fix io exception
        }

        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(jsonString, Movie[].class);

        simulateDelay();

        return Arrays.asList(movies);
    }

    private void simulateDelay(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
