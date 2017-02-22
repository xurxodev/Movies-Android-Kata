package com.xurxodev.moviesandroidkata.robot;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MovieDetailRobot {
    private MoviesRobot moviesRobot;

    public MovieDetailRobot(MoviesRobot moviesRobot) {
        this.moviesRobot = moviesRobot;
    }

    public MovieDetailRobot verifyTitle(String expectedTitle) {
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(expectedTitle)));

        return this;
    }

    public MoviesRobot goBack() {
        pressBack();

        return moviesRobot;
    }
}
