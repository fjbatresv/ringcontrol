package com.fjbatresv.callrest.main.DI;

import android.content.Context;
import android.content.SharedPreferences;

import com.fjbatresv.callrest.firebase.FireBaseHelper;
import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.main.MainInteractor;
import com.fjbatresv.callrest.main.MainInteractorImpl;
import com.fjbatresv.callrest.main.MainPresenter;
import com.fjbatresv.callrest.main.MainPresenterImpl;
import com.fjbatresv.callrest.main.MainRepo;
import com.fjbatresv.callrest.main.MainRepoImpl;
import com.fjbatresv.callrest.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 24/05/2017.
 */

@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainView provideMainView(){
        return this.view;
    }

    @Singleton
    @Provides
    MainPresenter providesMainPresenter(EventBus bus, MainView view, MainInteractor interactor){
        return new MainPresenterImpl(bus, view, interactor);
    }

    @Singleton
    @Provides
    MainInteractor provesMainInteractor(Context context, EventBus bus, MainRepo repo){
        return new MainInteractorImpl(context, bus, repo);
    }

    @Singleton
    @Provides
    MainRepo providesMainRepo(SharedPreferences preferences, FireBaseHelper helper, Context context, EventBus bus){
        return new MainRepoImpl(preferences, helper, context, bus);
    }
}
