package com.xurxodev.moviesandroidkata.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.presentation.view.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_TITLE = "MovieTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initializeToolBar();
        showUpButtonInActionBar();

        if (savedInstanceState == null) {
            showDetailFragment();
        }
    }

    private void showDetailFragment() {
        String movieTitle = (String) getIntent().getStringExtra(MovieDetailActivity.MOVIE_TITLE);

        Bundle arguments = new Bundle();
        arguments.putSerializable(MovieDetailActivity.MOVIE_TITLE, movieTitle);

        MovieDetailFragment fragment = new MovieDetailFragment();

        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_detail_container, fragment)
                .commit();
    }

    private void initializeToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
    }

    private void showUpButtonInActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent createIntent(Context context, String movieTitle) {
        Intent intent = new Intent(context, MovieDetailActivity.class);

        intent.putExtra(MOVIE_TITLE, movieTitle);

        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            supportFinishAfterTransition();

            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
