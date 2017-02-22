package com.xurxodev.moviesandroidkata.robot;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;

import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.presentation.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.presentation.view.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.util.idlingresource.ProgressbarIdlingResource;

public class MoviesRobot {
    private MoviesActivity activity;
    RecyclerView recyclerView;

    public MoviesRobot(MoviesActivity activity) {
        this.activity = activity;
        this.recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview_movies);
        Espresso.registerIdlingResources(
                new ProgressbarIdlingResource(activity));
    }

    public int getNumMoviesRows() {
        return recyclerView.getAdapter().getItemCount();
    }

    public void waitForMovies(){
        onView(withId(R.id.recyclerview_movies)).check(matches(isDisplayed()));
    }

    public String getMovieTitle(int i) {
        return ((MoviesAdapter) recyclerView.getAdapter()).movies.get(i).getTitle();
    }

    public MovieDetailRobot navigateToMovie(int position) {
        onView(withId(R.id.recyclerview_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        return new MovieDetailRobot(this);
    }
}
