package com.fjbatresv.callrest.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.firebase.FireBaseHelper;
import com.fjbatresv.callrest.firebase.FirebaseListener;
import com.fjbatresv.callrest.firebase.FirebaseSearchListener;
import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.main.events.MainEvent;
import com.fjbatresv.callrest.utils.ConfigKeys;

/**
 * Created by javie on 24/05/2017.
 */

public class MainRepoImpl implements MainRepo {
    private SharedPreferences preferences;
    private FireBaseHelper helper;
    private Context context;
    private EventBus bus;

    public MainRepoImpl(SharedPreferences preferences, FireBaseHelper helper, Context context, EventBus bus) {
        this.preferences = preferences;
        this.helper = helper;
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void reviewIntro() {
        MainEvent event = new MainEvent();
        boolean visto = preferences.getBoolean(ConfigKeys.introView, false);
        event.setObject(visto);
        event.setTipo(MainEvent.REVIEW_INTRO);
        event.setMensaje(visto ? context.getString(R.string.welcome_message): null);
        bus.post(event);
    }

    @Override
    public void introDone() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ConfigKeys.introView, true);
        editor.commit();
        bus.post(new MainEvent(MainEvent.REVIEW_INTRO, context.getString(R.string.welcome_message), true));
    }

    @Override
    public void loadUser() {
        helper.getUser(new FirebaseSearchListener() {
            @Override
            public void onSuccess(Object obj) {
                bus.post(new MainEvent(MainEvent.LOAD_USER, obj));
            }

            @Override
            public void onError(String error) {
                bus.post(new MainEvent(MainEvent.LOAD_USER, error));
            }
        });
    }
}
