package com.xurxodev.moviesandroidkata.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xurxodev.moviesandroidkata.presentation.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.robot.MoviesRobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoviesLoadingTextFeature {
    @Rule
    public ActivityTestRule<MoviesActivity> activityTestRule =
            new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void show_loading_text_while_retrieve_movies() {
        MoviesRobot moviesRobot = new MoviesRobot(activityTestRule.getActivity());

        moviesRobot
                .waitForMovies()
                .refreshMovies()
                .verifyLoadingText();
    }
}
