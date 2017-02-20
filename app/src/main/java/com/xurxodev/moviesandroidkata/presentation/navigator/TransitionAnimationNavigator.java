package com.xurxodev.moviesandroidkata.presentation.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.domain.entity.Movie;
import com.xurxodev.moviesandroidkata.presentation.presenter.boundary.Navigator;
import com.xurxodev.moviesandroidkata.presentation.view.activity.MovieDetailActivity;
import com.xurxodev.moviesandroidkata.presentation.view.adapter.MoviesAdapter;

public class TransitionAnimationNavigator implements Navigator {

    private final Context activityContext;

    public TransitionAnimationNavigator(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void openMovieDetail(Movie movie) {

        if (movie == null) {
            throw new IllegalArgumentException(
                    "Has ocurrer a error load movie detail");
        }

        Intent intent = MovieDetailActivity.createIntent(activityContext, movie.getTitle());

        startCompetitorActivity(intent, movie);
    }

    private void startCompetitorActivity(Intent intent, Movie movie) {

        ActivityOptionsCompat options = configureTransition(movie);

        if (options != null) {
            ActivityCompat.startActivity((Activity)activityContext, intent, options.toBundle());
        } else {
            activityContext.startActivity(intent);
        }
    }

    private ActivityOptionsCompat configureTransition(Movie movie) {

        ActivityOptionsCompat options = null;

        View itemView = getItemView(movie);

        if (itemView != null) {

            View sourceTransitionView = itemView.findViewById(R.id.item_movie_poster);
            String targetTransitionName = activityContext.getString(R.string.image_transition_name);

            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (Activity) activityContext, sourceTransitionView, targetTransitionName);
        }

        return options;
    }

    private View getItemView(Movie movie) {
        View itemView = null;

        Activity activity = (Activity) activityContext;


        RecyclerView recyclerView = (RecyclerView) activity.findViewById(
                R.id.recyclerview_movies);

        if (recyclerView != null) {

            int position = ((MoviesAdapter) recyclerView.getAdapter()).movies.indexOf(movie);

            itemView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
        }

        return itemView;
    }
}
