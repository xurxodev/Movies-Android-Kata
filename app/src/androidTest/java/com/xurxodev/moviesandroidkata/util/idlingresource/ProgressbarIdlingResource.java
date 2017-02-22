package com.xurxodev.moviesandroidkata.util.idlingresource;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.view.View;

import com.xurxodev.moviesandroidkata.R;

public class ProgressbarIdlingResource implements IdlingResource {

    private Activity activity;
    private ResourceCallback callback;

    public ProgressbarIdlingResource(Activity activity){
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "ProgressbarIdlingResource";
    }

    @Override
    public boolean isIdleNow() {

        View view = activity.findViewById(R.id.pb_loading);

        boolean loadingHide = !view.isShown();

        if (loadingHide)
            callback.onTransitionToIdle();

        return loadingHide;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
