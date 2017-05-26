package com.fjbatresv.callrest.firebase.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.lib.DI.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 15/10/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, FireBaseModule.class})
public interface FireBaseComponent {
}
