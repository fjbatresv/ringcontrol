package com.fjbatresv.callrest.firebase;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TabHost;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fjbatresv.callrest.BuildConfig;
import com.fjbatresv.callrest.R;

import com.fjbatresv.callrest.utils.ConfigKeys;
import com.fjbatresv.callrest.utils.GeneralUtil;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;

/**
 * Created by javie on 15/10/2016.
 */
public class FireBaseHelper {
    private FirebaseAuth mAuth;
    private Context context;
    private FirebaseRemoteConfig config;
    private DatabaseReference database;
    private FirebaseDatabase data;
    private FirebaseAnalytics analytics;
    private String androidId;
    private boolean conectado = false;

    public FireBaseHelper(Context context) {
        Trace myTrace = FirebasePerformance.getInstance().newTrace(ConfigKeys.CONNECT_FIREBASE);
        myTrace.start();
        this.mAuth = FirebaseAuth.getInstance();
        this.data = FirebaseDatabase.getInstance();
        this.analytics = FirebaseAnalytics.getInstance(context);
        this.database = this.data.getReference();
        this.context = context;
        buildConfig();
        myTrace.stop();
        this.data.getReference(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if (connected) {
                    conectado = true;
                } else {
                    conectado = false;
                }
                Log.e("conectadoList", String.valueOf(conectado));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                conectado = false;
                Log.e("conectadoList", String.valueOf(conectado));
            }
        });
    }

    private void buildConfig() {
        this.config = FirebaseRemoteConfig.getInstance();
        this.config.setConfigSettings(new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG).build());
        config.setDefaults(R.xml.config_default);
        config.fetch(10).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    config.activateFetched();
                } else {
                }
            }
        });
    }

    //Este metodo a su vez valida la autenticación del usuario
    public void getUser(FirebaseSearchListener listener) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            listener.onSuccess(user);
        } else {
            sendLog(context.getString(R.string.firebase_error_user_null));
            listener.onError(context.getString(R.string.firebase_error_user_null));
        }
    }

    public void logout(FirebaseListener listener) {
        mAuth.signOut();
        listener.onSuccess();
    }

    public String getParameterString(String nombre) {
        sendLog("get param " + nombre);
        return config.getString(nombre);
    }

    public void sendError(String error) {
        if (error != null && !error.isEmpty() && conectado) {
            //try{FirebaseCrash.report(new Exception(error));}catch(Exception ex){}
            Log.e("sendError", error);
        }
    }

    public void sendLog(String log) {
        if (log != null && !log.isEmpty() && conectado) {
            //try{FirebaseCrash.log(log);}catch(Exception ex){}
            Log.e("sendLog", log);
        }
    }

    public void getListas(final FirebaseSearchListener listener) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (!conectado){
            listener.onSuccess(null);
            sendLog("not connected");
        }
        final ArrayList<com.fjbatresv.callrest.entities.List> listas =
                new ArrayList<com.fjbatresv.callrest.entities.List>();
        this.database.child("listas").child(user.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            com.fjbatresv.callrest.entities.List lista =
                                    data.getValue(com.fjbatresv.callrest.entities.List.class);
                            listas.add(lista);
                        }
                        listener.onSuccess(listas);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        sendError(databaseError.getMessage());
                        listener.onError(databaseError.getMessage());
                    }
                });
    }

}