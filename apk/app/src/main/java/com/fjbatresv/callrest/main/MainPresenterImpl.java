package com.fjbatresv.callrest.main;

import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.main.events.MainEvent;
import com.fjbatresv.callrest.main.ui.MainView;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 24/05/2017.
 */

public class MainPresenterImpl implements MainPresenter {
    private EventBus bus;
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(EventBus bus, MainView view, MainInteractor interactor) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        bus.unRegister(this);
    }

    @Override
    public void reviewIntro() {
        view.loading(true);
        interactor.reviewIntro();
    }

    @Override
    public void loadUser() {
        view.loading(true);
        interactor.loadUser();
    }

    @Subscribe
    @Override
    public void onEvent(MainEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()) {
            view.showToast(event.getError());
        } else {
            if (event.getMensaje() != null && !event.getMensaje().isEmpty()) {
                view.showSnack(event.getMensaje());
            }
            switch (event.getTipo()) {
                case MainEvent.REVIEW_INTRO:
                    view.loadIntro((boolean) event.getObject());
                    break;
                case MainEvent.LOAD_USER:
                    view.loadUser((FirebaseUser) event.getObject());
                    break;
            }
        }
    }

    @Override
    public void introDone() {
        view.loading(true);
        interactor.introDone();
    }
}
