package com.fjbatresv.callrest.firebase;

import java.util.Objects;

/**
 * Created by javie_000 on 10/17/2016.
 */
public interface FirebaseSearchListener {
    void onSuccess(Object obj);
    void onError(String error);
}
