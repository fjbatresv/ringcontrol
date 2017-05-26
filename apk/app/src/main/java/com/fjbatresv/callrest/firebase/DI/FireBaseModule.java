package com.fjbatresv.callrest.firebase.DI;

import android.content.Context;

import com.fjbatresv.callrest.firebase.FireBaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 15/10/2016.
 */
@Module
public class FireBaseModule {
    @Singleton
    @Provides
    FireBaseHelper providesFireBaseHelper(Context context){
        return new FireBaseHelper(context);
    }
}
