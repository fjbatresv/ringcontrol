package com.fjbatresv.callrest.main.ui;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fjbatresv.callrest.App;
import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.intro.MainIntroActivity;
import com.fjbatresv.callrest.main.MainPresenter;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.core.models.Card;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private static final int INTRO_INTENT = 1012;
    public static final String INTRO_DONE = "INTRO_DONE";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.mainContent)
    CardView mainContent;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    RelativeLayout container;

    @Inject
    MainPresenter presenter;

    private App app;

    private int visible;
    private int gone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (App) getApplication();
        injection();
        presenter.onCreate();
        if(!getIntent().hasExtra(INTRO_DONE)){
            presenter.reviewIntro();
        }else{
            presenter.introDone();
        }
        navigation();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void injection() {
        visible = View.VISIBLE;
        gone = View.GONE;
        app.mainComponent(this).injet(this);
    }

    //--NAVIGATION BEGIN--//
    private void navigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // --NAVIGATION END--//

    //--MainView - Begin --//
    @Override
    public void loading(boolean load) {
        progressBar.setVisibility(load ? visible : gone);
        mainContent.setVisibility(load ? gone : visible);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnack(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void loadUser(FirebaseUser user) {

    }

    @Override
    public void loadIntro(boolean load) {
        if (load) {
            presenter.loadUser();
        } else {
            startActivity(new Intent(this, MainIntroActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    //--MainView - END --//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTRO_INTENT) {
            if (resultCode == RESULT_OK) {
                Log.e("intro", "la vio");
            } else {
                Log.e("intro", "le pelo");
            }
        }
    }
}
