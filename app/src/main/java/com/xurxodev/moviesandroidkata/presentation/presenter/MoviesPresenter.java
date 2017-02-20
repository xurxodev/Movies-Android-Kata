package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.entity.Movie;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {

    GetMoviesUseCase getMoviesUseCase;

    MoviesView view;

    @Inject
    public MoviesPresenter(GetMoviesUseCase getMoviesUseCase) {
        this.getMoviesUseCase = getMoviesUseCase;
    }

    public void attachView(MoviesView moviesView) {
        this.view = moviesView;

        loadMovies();
    }

    private void loadMovies() {
        loadingMovies();

        getMoviesUseCase.execute(new GetMoviesUseCase.Callback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                showMovies(movies);
            }

            @Override
            public void onConnectionError() {
                view.showConnectionError();
            }
        });
    }

    private void loadingMovies() {
        if (view.isReady()) {
            view.clearMovies();
            view.showLoadingText();
        }
    }

    private void showMovies(List<Movie> movies) {
        if (view.isReady()) {
            view.showMovies(movies);
            view.showTotalMovies(movies.size());
        }
    }

    public void onRefreshAction() {
        loadMovies();
    }

    public interface MoviesView {
        void showMovies(List<Movie> movies);

        void clearMovies();

        void showLoadingText();

        void showTotalMovies(int count);

        void showConnectionError();

        boolean isReady();
    }
}
