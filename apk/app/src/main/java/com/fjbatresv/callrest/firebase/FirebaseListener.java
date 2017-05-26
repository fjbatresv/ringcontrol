package com.fjbatresv.callrest.firebase;

/**
 * Created by javie on 15/10/2016.
 */
public interface FirebaseListener {
    void onSuccess();
    void onError(String error);
}
