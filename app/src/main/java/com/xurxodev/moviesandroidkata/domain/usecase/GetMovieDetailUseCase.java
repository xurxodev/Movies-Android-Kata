package com.xurxodev.moviesandroidkata.domain.usecase;

import com.xurxodev.moviesandroidkata.domain.boundary.executor.AsyncExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.MainExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.repository.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.entity.Movie;


public class GetMovieDetailUseCase implements UseCase {

    public interface Callback {
        void onCompetitorDetailLoaded(final Movie competitor);

        void onCompetitorDetailNotFound();

        void onConnectionError();
    }

    private MovieRepository competitorRepository;
    private AsyncExecutor asyncExecutor;
    private MainExecutor mainExecutor;
    private Callback callback;
    private String movieTitle;

    public GetMovieDetailUseCase(MovieRepository competitorRepository, AsyncExecutor asyncExecutor,
            MainExecutor mainExecutor) {
        this.competitorRepository = competitorRepository;
        this.asyncExecutor = asyncExecutor;
        this.mainExecutor = mainExecutor;
    }

    public void execute(String movieTitle, Callback callback) {
        this.movieTitle = movieTitle;

        this.callback = callback;
        this.asyncExecutor.run(this);
    }

    @Override
    public void run() {
        Movie movie = competitorRepository.getMovieByTitle(this.movieTitle);

        notifyCompetitorDetailsLoaded(movie);

        //TODO:call to notify error
    }

    private void notifyError() {
        mainExecutor.run(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void notifyCompetitorDetailsLoaded(final Movie movie) {
        mainExecutor.run(new Runnable() {
            @Override
            public void run() {
                callback.onCompetitorDetailLoaded(movie);
            }
        });
    }
}