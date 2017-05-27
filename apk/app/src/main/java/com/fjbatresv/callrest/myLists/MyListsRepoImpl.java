package com.fjbatresv.callrest.myLists;

import android.content.Context;
import android.content.SharedPreferences;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.db.Queries;
import com.fjbatresv.callrest.entities.List;
import com.fjbatresv.callrest.firebase.FireBaseHelper;
import com.fjbatresv.callrest.firebase.FirebaseSearchListener;
import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.myLists.events.MyListsEvent;

import java.util.ArrayList;

/**
 * Created by javie on 27/05/2017.
 */

public class MyListsRepoImpl implements MyListsRepo {
    private FireBaseHelper helper;
    private EventBus bus;
    private Context context;

    public MyListsRepoImpl(FireBaseHelper helper, EventBus bus, Context context) {
        this.helper = helper;
        this.bus = bus;
        this.context = context;
    }

    @Override

            public void loadLists() {
                helper.getListas(new FirebaseSearchListener() {
                    @Override
                    public void onSuccess(Object obj) {
                        bus.post(new MyListsEvent(MyListsEvent.LOAD_LISTS, Queries.crossLists((ArrayList<List>) obj)));
                    }
            @Override
            public void onError(String error) {
                bus.post(new MyListsEvent(error));
            }
        });
    }

    @Override
    public void loadUser() {
        helper.getUser(new FirebaseSearchListener() {
            @Override
            public void onSuccess(Object obj) {
                bus.post(new MyListsEvent(MyListsEvent.LOAD_USER, obj));
            }

            @Override
            public void onError(String error) {
                bus.post(new MyListsEvent(context.getString(R.string.user_invalid)));
            }
        });
    }
}
