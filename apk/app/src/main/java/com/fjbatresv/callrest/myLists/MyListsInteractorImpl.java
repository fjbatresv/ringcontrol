package com.fjbatresv.callrest.myLists;

import android.content.Context;

import com.fjbatresv.callrest.lib.base.EventBus;

/**
 * Created by javie on 27/05/2017.
 */

public class MyListsInteractorImpl implements MyListsInteractor {
    private Context context;
    private MyListsRepo repo;
    private EventBus bus;

    public MyListsInteractorImpl(Context context, MyListsRepo repo, EventBus bus) {
        this.context = context;
        this.repo = repo;
        this.bus = bus;
    }

    @Override
    public void loadLists() {
        repo.loadLists();
    }

    @Override
    public void loadUser() {
        repo.loadUser();
    }
}
