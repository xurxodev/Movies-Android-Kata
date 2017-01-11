package com.xurxodev.moviesandroidkata.domain.usecase;

import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.entity.Movie;

import java.util.List;

public class GetMoviesUseCase {
    public interface Callback {
        void onMoviesLoaded(List<Movie> movies);

        void onConnectionError();
    }

    MovieRepository movieRepository;

    public GetMoviesUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void execute(final Callback callback) {
        AsyncTask<Void, Void, List<Movie>> moviesAsyncTask =
                new AsyncTask<Void, Void, List<Movie>>() {
                    @Override
                    protected List<Movie> doInBackground(Void... params) {

                        try {
                            return movieRepository.getMovies();
                        } catch (Exception exception) {
                            callback.onConnectionError();
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(List<Movie> movies) {
                        if (movies != null) {
                            callback.onMoviesLoaded(movies);
                        }
                    }
                };

        moviesAsyncTask.execute();
    }
}
