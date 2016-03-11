package com.niec.ieee.innoviz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by sonali on 2/13/16.
 */
public class facebookfragment extends Fragment {
    @Nullable

    private LoginButton loginButton;

    CallbackManager callbackManager;
    ShareDialog dialog;
    ShareButton sb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        View v = inflater.inflate(R.layout.facebook_fragment, container, false);

        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        sb = (ShareButton) v.findViewById(R.id.share_button);


        //loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        dialog = new ShareDialog(this);
        loginButton.setReadPermissions("user_friends");


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("LOGIN_SUCCESS", "Success");
                loginButton.setVisibility(View.INVISIBLE);
                Intent intent= new Intent(facebookfragment.this.getActivity(),HomepageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {

                Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_LONG).show();

            }
        });

        /*dialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {

            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(MainActivity.this,"Successfully Posted",Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Oops! An Error Occured", Toast.LENGTH_LONG);

            }
        });*/


        return v;

    }



}



