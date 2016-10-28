package com.xurxodev.moviesandroidkata.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        initializeToolBar();

        if (savedInstanceState == null) {
            showDetailFragment();
        }
    }

    private void showDetailFragment() {
        MoviesFragment fragment = new MoviesFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.movies_list_container, fragment)
                .commit();
    }

    private void initializeToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
