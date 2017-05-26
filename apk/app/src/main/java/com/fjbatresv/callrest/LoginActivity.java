package com.fjbatresv.callrest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.fjbatresv.callrest.main.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        verifyPlayServices();
        verifyAuth();
    }

    private void verifyPlayServices() {
        try {
            int v = getPackageManager().getPackageInfo("com.google.android.gms", 0 ).versionCode;
            if (v < 10298000){
                Toast.makeText(this, getString(R.string.common_google_play_services_unknown_issue), Toast.LENGTH_LONG).show();
                Uri marketUri = Uri.parse("market://details?id=com.google.android.gms");
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, getString(R.string.no_play_services), Toast.LENGTH_LONG).show();
            Uri marketUri = Uri.parse("market://details?id=com.google.android.gms");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    private void verifyAuth() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            authValid();
        }
    }

    @OnClick(R.id.login_account_signin)
    public void account_signin(){
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setProviders(
                        Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()
                        )
                )
                .setIsSmartLockEnabled(false)
                .setTheme(com.fjbatresv.callrest.R.style.GreenTheme)
                .build(), RC_SIGN_IN);
        getString(R.string.twitter_consumer_secret);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            //Login exitoso
            if (resultCode == ResultCodes.OK) {
                authValid();
            } else {
                //Fallo
                if (response == null) {
                    Log.e("Auth", "Cancelado");
                }else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.e("Auth", "Sin Internet");
                }else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Auth", "Error no conocido");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void authValid() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP
                |Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
