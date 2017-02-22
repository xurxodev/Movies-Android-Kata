package com.xurxodev.moviesandroidkata.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xurxodev.moviesandroidkata.presentation.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.robot.MoviesRobot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoviesCountFeature {
    @Rule
    public ActivityTestRule<MoviesActivity> activityTestRule =
            new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void show_count_text_equal_to_rows_count() {
        MoviesRobot moviesRobot = new MoviesRobot(activityTestRule.getActivity());

        moviesRobot.waitForMovies();

        int numMovies = moviesRobot.getNumMoviesRows();

        moviesRobot.verifyTextWithMoviesCount(numMovies);
    }
}

