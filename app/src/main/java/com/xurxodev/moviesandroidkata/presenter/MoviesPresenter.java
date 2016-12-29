package com.xurxodev.moviesandroidkata.presenter;

import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {

    MovieRepository movieRepository;

    MoviesView view;

    @Inject
    public MoviesPresenter(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void attachView(MoviesView moviesView){
        this.view = moviesView;

        loadMovies();
    }

    private void loadMovies() {
        loadingMovies();

        AsyncTask<Void, Void, List<Movie>> moviesAsyncTask =
                new AsyncTask<Void, Void, List<Movie>>() {
                    @Override
                    protected List<Movie> doInBackground(Void... params) {
                        return movieRepository.getMovies();
                    }

                    @Override
                    protected void onPostExecute(List<Movie> movies) {
                        showMovies(movies);
                    }
                };

        moviesAsyncTask.execute();
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

    public void onRefreshAction(){
        loadMovies();
    }

    public interface MoviesView {
        void showMovies(List<Movie> movies);
        void clearMovies();
        void showLoadingText();
        void showTotalMovies(int count);
        boolean isReady();
    }
}
