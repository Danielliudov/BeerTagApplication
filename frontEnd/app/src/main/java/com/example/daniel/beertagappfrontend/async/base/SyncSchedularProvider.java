package com.example.daniel.beertagappfrontend.async.base;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SyncSchedularProvider implements SchedulerProvider {
    @Override
    public Scheduler background() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
