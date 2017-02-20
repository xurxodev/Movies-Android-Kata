package com.xurxodev.moviesandroidkata.presentation.executor;

import android.os.Handler;
import android.os.Looper;

import com.xurxodev.moviesandroidkata.domain.boundary.executor.MainExecutor;

import javax.inject.Inject;


public class UIThreadExecutor implements MainExecutor {

    private Handler handler;

    @Inject
    public UIThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void run(Runnable runnable) {
        handler.post(runnable);
    }
}