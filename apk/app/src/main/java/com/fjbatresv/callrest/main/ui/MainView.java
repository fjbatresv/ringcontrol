package com.fjbatresv.callrest.main.ui;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by javie on 24/05/2017.
 */

public interface MainView {
    void loading(boolean load);
    void showToast(String message);
    void showSnack(String message);

    void loadUser(FirebaseUser user);
    void loadIntro(boolean load);
}
