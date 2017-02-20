package com.xurxodev.moviesandroidkata.di.component;

import com.xurxodev.moviesandroidkata.di.module.ActivityModule;
import com.xurxodev.moviesandroidkata.di.scope.ActivityScope;
import com.xurxodev.moviesandroidkata.presentation.view.fragment.MovieDetailFragment;
import com.xurxodev.moviesandroidkata.presentation.view.fragment.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent( modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MoviesFragment fragment);

    void inject(MovieDetailFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);

        ActivityComponent build();
    }
}
