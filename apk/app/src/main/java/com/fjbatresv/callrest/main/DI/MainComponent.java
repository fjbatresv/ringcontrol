package com.fjbatresv.callrest.main.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.firebase.DI.FireBaseModule;
import com.fjbatresv.callrest.lib.DI.LibsModule;
import com.fjbatresv.callrest.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 24/05/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, FireBaseModule.class, MainModule.class})
public interface MainComponent {
    void injet(MainActivity activity);
}
