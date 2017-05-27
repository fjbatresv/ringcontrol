package com.fjbatresv.callrest;

import android.app.Application;

import com.fjbatresv.callrest.firebase.DI.FireBaseModule;
import com.fjbatresv.callrest.lib.DI.LibsModule;

//import com.fjbatresv.callrest.main.DI.DaggerMainComponent;
import com.fjbatresv.callrest.main.DI.MainComponent;
import com.fjbatresv.callrest.main.DI.MainModule;
import com.fjbatresv.callrest.main.ui.MainActivity;
import com.fjbatresv.callrest.main.ui.MainView;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.HashMap;

/**
 * Created by javie_000 on 8/29/2016.
 */
public class App extends Application {
    private AppModule appModule;
    private LibsModule libsModule;
    private FireBaseModule fireBaseModule;
    private final static String LOGGED_USER_SHARED_PREFERENCES = "ringControl";
    @Override
    public void onCreate() {
        super.onCreate();
        initModule();
        initDb();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DbTearDown();
    }

    private void initDb() {
        FlowManager.init(this);
    }

    private void DbTearDown(){
        FlowManager.destroy();
    }

    //Getter
    public static String getLoggedUserSharedPreferences() {
        return LOGGED_USER_SHARED_PREFERENCES;
    }

    private void initModule() {
        appModule = new AppModule(this);
        libsModule = new LibsModule();
        fireBaseModule = new FireBaseModule();
    }


    //Components injection
    public MainComponent mainComponent(MainView view){
        /*return DaggerMainComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .fireBaseModule(fireBaseModule)
                .mainModule(new MainModule(view))
                .build();*/
        return null;
    }
}
