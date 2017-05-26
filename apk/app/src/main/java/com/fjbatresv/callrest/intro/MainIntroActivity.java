package com.fjbatresv.callrest.intro;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.main.ui.MainActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by javie on 24/05/2017.
 */

public class MainIntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome!", "This is a demo of the AppIntro library, with permissions being requested on a slide..", R.drawable.messenger_bubble_large_blue, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Permission Request", "In order to access your camera, you must give permissions.", R.drawable.com_facebook_favicon_blue, Color.parseColor("#1976D2")));

        // Here we load a string array with a camera permission, and tell the library to request permissions on slide 2
        askForPermissions(new String[]{Manifest.permission.SEND_SMS}, 2);
    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        endIntro();
    }

    private void endIntro() {
        startActivity(new Intent(this, MainActivity.class)
                .putExtra(MainActivity.INTRO_DONE, "introdone")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP
                        |Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        endIntro();
    }
}
