package com.niec.ieee.innoviz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;


public class MainActivity extends AppCompatActivity {

    private  facebookfragment fb;
    private  googlefragment gl;
    //private loginfragment ln;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
        {
            fb= new facebookfragment();
            gl= new googlefragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fb).commit();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, gl).commit();
        }
        else
        {
            fb= (facebookfragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
            gl= (googlefragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fb.callbackManager.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if (requestCode == gl.RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                gl.handleSignInResult(result);

            }
        }
    }


}




