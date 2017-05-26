package com.fjbatresv.callrest.main;

import com.fjbatresv.callrest.main.events.MainEvent;

/**
 * Created by javie on 24/05/2017.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void reviewIntro();
    void loadUser();

    void onEvent(MainEvent event);

    void introDone();
}
