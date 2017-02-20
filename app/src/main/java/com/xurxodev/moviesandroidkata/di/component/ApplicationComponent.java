package com.xurxodev.moviesandroidkata.di.component;

import com.xurxodev.moviesandroidkata.di.module.AppModule;
import com.xurxodev.moviesandroidkata.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface ApplicationComponent {
    ActivityComponent.Builder activityComponent();
}