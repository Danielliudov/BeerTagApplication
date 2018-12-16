package com.example.daniel.beertagappfrontend.async.base;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler background();

    Scheduler ui();
}
