package com.xurxodev.moviesandroidkata.di.module;

import android.app.Activity;

import com.xurxodev.moviesandroidkata.di.scope.ActivityScope;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMovieDetailUseCase;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.navigator.TransitionAnimationNavigator;
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.presenter.boundary.Navigator;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activityContext;

    public ActivityModule(Activity activityContext) {
        this.activityContext = activityContext;
    }

    @Provides
    @ActivityScope
    Activity provideActivityContext() {
        return this.activityContext;
    }

    @Provides
    @ActivityScope
    Navigator provideNavigator(Activity activityContext) {
        return new TransitionAnimationNavigator(activityContext);
    }

    @Provides
    @ActivityScope
    MoviesPresenter provideMoviesPresenter(
            Navigator navigator, GetMoviesUseCase getGetMoviesUseCase) {
        return new MoviesPresenter(navigator, getGetMoviesUseCase);
    }

    @Provides
    @ActivityScope
    MovieDetailPresenter provideMovieDetailPresenter(GetMovieDetailUseCase getMovieDetailUseCase) {
        return new MovieDetailPresenter(getMovieDetailUseCase);
    }
}
