package com.xurxodev.moviesandroidkata.robot;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

import android.graphics.drawable.Drawable;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.presentation.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.presentation.view.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.util.idlingresource.ProgressbarIdlingResource;

public class MoviesRobot {
    private MoviesActivity activity;
    RecyclerView recyclerView;
    ProgressbarIdlingResource progressbarIdlingResource;

    public MoviesRobot(MoviesActivity activity) {
        this.activity = activity;
        this.recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview_movies);
        this.progressbarIdlingResource = new ProgressbarIdlingResource(activity);

        removeAnimateDrawableFromProgressForTest();
    }

    public int getNumMoviesRows() {
        return recyclerView.getAdapter().getItemCount();
    }

    public MoviesRobot waitForMovies() {
        Espresso.registerIdlingResources(progressbarIdlingResource);

        onView(withId(R.id.recyclerview_movies)).check(matches(isDisplayed()));

        Espresso.unregisterIdlingResources(progressbarIdlingResource);

        return this;
    }

    public String getMovieTitle(int i) {
        return ((MoviesAdapter) recyclerView.getAdapter()).movies.get(i).getTitle();
    }

    public MovieDetailRobot navigateToMovie(int position) {
        onView(withId(R.id.recyclerview_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

        return new MovieDetailRobot(this);
    }

    public MoviesRobot verifyTextWithMoviesCount(int numMovies) {
        String formatCountText = activity.getString(R.string.movies_count_text);
        String expectedText = String.format(formatCountText, numMovies);

        onView(withId(R.id.movies_title_text_view)).check(matches(withText(expectedText)));

        return this;
    }

    public MoviesRobot refreshMovies() {
        onView(withId(R.id.refresh_button)).perform(click());
        return this;
    }

    public MoviesRobot verifyLoadingText() {
        String text = activity.getString(R.string.loading_movies_text);

        onView(withId(R.id.movies_title_text_view)).check(
                matches(withText(text)));

        return this;
    }

    public MoviesRobot verifyProgressIsVisible() {
        onView(withId(R.id.pb_loading)).check(
                matches(isDisplayed()));

        return this;
    }

    public MoviesRobot verifyProgressIsHide() {
        onView(withId(R.id.pb_loading)).check(
                matches(not(isDisplayed())));

        return this;
    }

    private void removeAnimateDrawableFromProgressForTest() {
        Drawable notAnimatedDrawable = ContextCompat.getDrawable(activity, R.drawable.ic_refresh);
        ((ProgressBar) activity.findViewById(R.id.pb_loading)).setIndeterminateDrawable(
                notAnimatedDrawable);

    }
}
