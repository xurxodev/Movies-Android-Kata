package com.xurxodev.moviesandroidkata.presentation.view.fragment;

import static com.xurxodev.moviesandroidkata.presentation.view.activity.MovieDetailActivity
        .MOVIE_TITLE;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.di.module.ActivityModule;
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;

import javax.inject.Inject;


public class MovieDetailFragment extends Fragment implements MovieDetailPresenter.View {

    View rootView;

    @Inject
    public MovieDetailPresenter presenter;

    private RecyclerView recyclerView;



    AppBarLayout appBarLayout;
    private ProgressBar progressBarLoading;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MoviesApplication) getActivity().getApplication()).getAppComponent().activityComponent
                ().activityModule(
                new ActivityModule(getActivity())).build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);


        if (getArguments().containsKey(MOVIE_TITLE)) {

            String competitorName = (String) getArguments().get(MOVIE_TITLE);

            appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.app_bar);
            progressBarLoading = (ProgressBar) getActivity().findViewById(R.id.pb_loading);

            initializePresenter(competitorName);
        }

        return rootView;
    }

    private void initializePresenter(String competitorName) {
        presenter.attachView(this, competitorName);
    }

    @Override
    public void renderImage(String image) {
        Activity activity = this.getActivity();

        ImageView imgtoolbar = (ImageView) activity.findViewById(R.id.imgToolbar);

        if (imgtoolbar != null) {
            Picasso.with(activity.getApplicationContext()).load(image)
                    .into(imgtoolbar);
        }
    }

    @Override
    public void renderOverview(String biography) {
        ((TextView) rootView.findViewById(R.id.movie_overview_content)).setText(biography);
    }

    @Override
    public void renderTitle(String name) {
        //setTitle from background working in phone
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(name);

        //setTitle from background working in tablet
        getActivity().setTitle(name);
    }

    @Override
    public void showLoading() {
        appBarLayout.setVisibility(View.GONE);
        rootView.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        appBarLayout.setVisibility(View.VISIBLE);
        rootView.setVisibility(View.VISIBLE);
        progressBarLoading.setVisibility(View.GONE);
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }
}

