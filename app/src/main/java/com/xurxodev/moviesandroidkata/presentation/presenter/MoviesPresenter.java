package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.entity.Movie;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.presenter.boundary.Navigator;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {

    GetMoviesUseCase getMoviesUseCase;
    Navigator navigator;

    MoviesView view;

    @Inject
    public MoviesPresenter(Navigator navigator,GetMoviesUseCase getMoviesUseCase) {
        this.navigator = navigator;
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

    public void onMovieClicked(Movie movie)
    {
        navigator.openMovieDetail(movie);
    }

    private void loadingMovies() {
        if (view.isReady()) {
            view.showLoading();
        }
    }

    private void showMovies(List<Movie> movies) {
        if (view.isReady()) {
            view.hideLoading();
            view.showMovies(movies);
            view.showTotalMovies(movies.size());
        }
    }

    public void onRefreshAction() {
        loadMovies();
    }

    public interface MoviesView {
        void showMovies(List<Movie> movies);

        void hideLoading();

        void showLoading();

        void showTotalMovies(int count);

        void showConnectionError();

        boolean isReady();
    }
}
