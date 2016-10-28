package com.xurxodev.moviesandroidkata.data;

import android.content.Context;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskMovieRepository implements MovieRepository {

    private Context context;

    public DiskMovieRepository(Context context){
       this.context = context;
    }

    @Override
    public List<Movie> getMovies() {
        String jsonString = null;

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.movies);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            jsonString = new String(b);
        } catch (IOException e){
            //TODO: fix io exception
        }

        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(jsonString, Movie[].class);

        return Arrays.asList(movies);
    }
}
