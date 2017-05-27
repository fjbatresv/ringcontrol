package com.fjbatresv.callrest.myLists.DI;

import com.fjbatresv.callrest.AppModule;
import com.fjbatresv.callrest.firebase.DI.FireBaseModule;
import com.fjbatresv.callrest.lib.DI.LibsModule;
import com.fjbatresv.callrest.myLists.ui.MyListsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 27/05/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, FireBaseModule.class, MyListsModule.class})
public interface MyListsComponent {
    void inject(MyListsActivity activity);
}
