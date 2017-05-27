package com.fjbatresv.callrest.myLists;

import com.fjbatresv.callrest.entities.List;
import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.myLists.events.MyListsEvent;
import com.fjbatresv.callrest.myLists.ui.MyListsView;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by javie on 27/05/2017.
 */

public class MyListsPresenterImpl implements MyListPresenter {
    private EventBus bus;
    private MyListsView view;
    private MyListsInteractor interactor;

    public MyListsPresenterImpl(EventBus bus, MyListsView view, MyListsInteractor interactor) {
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
    public void onEvent(MyListsEvent event) {
        view.loading(false);
        if (event.getError() != null && !event.getError().isEmpty()){
            view.showToast(event.getError());
        }else{
            if (event.getMensaje() != null && !event.getMensaje().isEmpty()){
                view.showSnack(event.getMensaje());
            }
            switch (event.getTipo()){
                case MyListsEvent.LOAD_USER:
                    view.loadUser((FirebaseUser) event.getObject());
                    break;
                case MyListsEvent.LOAD_LISTS:
                    view.loadLists((ArrayList<List>) event.getObject());
                    break;
            }
        }
    }

    @Override
    public void loadLists() {
        view.loading(true);
        interactor.loadLists();
    }

    @Override
    public void loadUser() {
        view.loading(true);
        interactor.loadUser();
    }
}
