package com.xurxodev.moviesandroidkata.test;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xurxodev.moviesandroidkata.presentation.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.robot.MoviesRobot;
import com.xurxodev.moviesandroidkata.util.idlingresource.ProgressbarIdlingResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MovieDetailTitleFeature {
    @Rule
    public ActivityTestRule<MoviesActivity> activityTestRule =
            new ActivityTestRule<>(MoviesActivity.class);

    @Before
    public void initialize(){
        Espresso.registerIdlingResources(
                new ProgressbarIdlingResource(activityTestRule.getActivity()));
    }

    @Test
    public void show_movie_title_as_screen_title() {
        MoviesRobot moviesRobot = new MoviesRobot(activityTestRule.getActivity());

        moviesRobot.waitForMovies();

        int numMovies = moviesRobot.getNumMoviesRows();

        for (int i = 0; i < numMovies; i++) {

            String expectedTitle = moviesRobot.getMovieTitle(i);

            moviesRobot
                    .navigateToMovie(i)
                    .verifyTitle(expectedTitle)
                    .goBack();
        }
    }
}
