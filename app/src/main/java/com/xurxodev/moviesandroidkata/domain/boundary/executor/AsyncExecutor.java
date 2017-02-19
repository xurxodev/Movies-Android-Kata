package com.xurxodev.moviesandroidkata.domain.boundary.executor;


import com.xurxodev.moviesandroidkata.domain.usecase.UseCase;

public interface AsyncExecutor {

    void run(final UseCase interactor);
}
