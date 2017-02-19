package com.xurxodev.moviesandroidkata.domain.usecase;

import com.xurxodev.moviesandroidkata.domain.boundary.Repository.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.AsyncExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.MainExecutor;
import com.xurxodev.moviesandroidkata.domain.entity.Movie;

import java.util.List;

public class GetMoviesUseCase implements UseCase {
    public interface Callback {
        void onMoviesLoaded(List<Movie> movies);

        void onConnectionError();
    }

    private MovieRepository movieRepository;
    private AsyncExecutor asyncExecutor;
    private MainExecutor mainExecutor;
    private Callback callback;

    public GetMoviesUseCase(MovieRepository movieRepository, AsyncExecutor asyncExecutor,
            MainExecutor mainExecutor) {
        this.movieRepository = movieRepository;
        this.mainExecutor = mainExecutor;
        this.asyncExecutor = asyncExecutor;
    }

    public void execute(final Callback callback) {
        this.callback = callback;

        asyncExecutor.run(this);
    }

    @Override
    public void run() {
        try {
            List<Movie> movies = movieRepository.getMovies();

            notifyMoviesLoaded(movies);
        } catch (Exception ex) {
            notifyConnectionError();
        }
    }

    private void notifyConnectionError() {
        mainExecutor.run(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void notifyMoviesLoaded(final List<Movie> movies) {
        mainExecutor.run(new Runnable() {
            @Override
            public void run() {
                callback.onMoviesLoaded(movies);
            }
        });
    }

}
