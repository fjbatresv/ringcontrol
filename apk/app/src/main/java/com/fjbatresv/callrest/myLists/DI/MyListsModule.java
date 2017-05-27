package com.fjbatresv.callrest.myLists.DI;

import android.content.Context;

import com.fjbatresv.callrest.firebase.FireBaseHelper;
import com.fjbatresv.callrest.lib.base.EventBus;
import com.fjbatresv.callrest.myLists.MyListPresenter;
import com.fjbatresv.callrest.myLists.MyListsInteractor;
import com.fjbatresv.callrest.myLists.MyListsInteractorImpl;
import com.fjbatresv.callrest.myLists.MyListsPresenterImpl;
import com.fjbatresv.callrest.myLists.MyListsRepo;
import com.fjbatresv.callrest.myLists.MyListsRepoImpl;
import com.fjbatresv.callrest.myLists.ui.MyListsView;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 27/05/2017.
 */
@Module
class MyListsModule {
    private MyListsView view;

    public MyListsModule(MyListsView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MyListsView providesMylistsView(){
        return this.view;
    }

    @Singleton
    @Provides
    MyListPresenter providesMyListPresenter(EventBus bus, MyListsView view, MyListsInteractor interactor){
        return new MyListsPresenterImpl(bus, view,interactor);
    }

    @Singleton
    @Provides
    MyListsInteractor providesMyListsInteractor(Context context, MyListsRepo repo, EventBus bus){
        return new MyListsInteractorImpl(context, repo, bus);
    }

    @Singleton
    @Provides
    MyListsRepo providesMyListsRepo(FireBaseHelper helper, EventBus bus, Context context){
        return new MyListsRepoImpl(helper, bus, context);
    }
}
