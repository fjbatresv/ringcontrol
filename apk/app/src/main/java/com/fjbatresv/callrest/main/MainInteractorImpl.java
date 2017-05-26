package com.fjbatresv.callrest.main;

import android.content.Context;

import com.fjbatresv.callrest.lib.base.EventBus;

/**
 * Created by javie on 24/05/2017.
 */

public class MainInteractorImpl implements MainInteractor {
    private Context context;
    private EventBus bus;
    private MainRepo repo;

    public MainInteractorImpl(Context context, EventBus bus, MainRepo repo) {
        this.context = context;
        this.bus = bus;
        this.repo = repo;
    }

    @Override
    public void reviewIntro() {
        repo.reviewIntro();
    }

    @Override
    public void introDone() {
        repo.introDone();
    }

    @Override
    public void loadUser() {
        repo.loadUser();
    }
}
