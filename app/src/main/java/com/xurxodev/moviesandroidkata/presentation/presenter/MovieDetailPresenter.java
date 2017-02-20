package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.entity.Movie;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMovieDetailUseCase;
import com.xurxodev.moviesandroidkata.presentation.presenter.boundary.Navigator;

public class MovieDetailPresenter {

    private View view;
    private GetMovieDetailUseCase getMovieDetailUseCase;


    private Movie movie;

    public MovieDetailPresenter(GetMovieDetailUseCase getCompetitorDetailsUseCase) {
        this.getMovieDetailUseCase = getCompetitorDetailsUseCase;
    }

    public void attachView(View view, String movieName) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
        loadCompetitor(movieName);
    }

    public void loadCompetitor(String movieTitle) {
        view.showLoading();

        getMovieDetailUseCase.execute(movieTitle,
                new GetMovieDetailUseCase.Callback() {
                    @Override
                    public void onCompetitorDetailLoaded(Movie retrievedMovie) {

                        movie = retrievedMovie;

                        if (view.isReady()) {
                            view.hideLoading();
                            view.renderImage(movie.getImage());
                            view.renderOverview(movie.getOverview());
                            view.renderTitle(movie.getTitle());
                        }
                    }

                    @Override
                    public void onCompetitorDetailNotFound() {
                        view.hideLoading();
                        //TODO: show message in view
                    }

                    @Override
                    public void onConnectionError() {
                        view.hideLoading();
                        //TODO: show message in view
                    }
                });

    }


    public interface View {
        void renderImage(String image);

        void renderOverview(String biography);

        void renderTitle(String title);

        void showLoading();

        void hideLoading();

        boolean isReady();
    }
}