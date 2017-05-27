package com.fjbatresv.callrest.myLists;

import com.fjbatresv.callrest.myLists.events.MyListsEvent;

/**
 * Created by javie on 27/05/2017.
 */

public interface MyListPresenter {
    void onCreate();
    void onDestroy();

    void onEvent(MyListsEvent event);

    void loadLists();

    void loadUser();
}
