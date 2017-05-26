package com.fjbatresv.callrest.lib.DI;

import com.fjbatresv.callrest.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie_000 on 8/29/2016.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class})
public interface LibComponent {
}
