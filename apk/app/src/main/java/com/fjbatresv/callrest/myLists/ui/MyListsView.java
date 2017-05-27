package com.fjbatresv.callrest.myLists.ui;

import com.fjbatresv.callrest.entities.List;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by javie on 27/05/2017.
 */

public interface MyListsView {
    void loading(boolean load);

    void showToast(String msg);
    void showSnack(String msg);

    void loadLists(ArrayList<List> listas);

    void loadUser(FirebaseUser user);
}
