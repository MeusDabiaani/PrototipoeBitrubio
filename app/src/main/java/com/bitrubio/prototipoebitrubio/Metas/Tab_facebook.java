package com.bitrubio.prototipoebitrubio.Metas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.bitrubio.prototipoebitrubio.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.ads.MobileAds;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Orion on 08/08/2016.
 */
public class Tab_facebook extends Fragment {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    public static Tab_facebook newInstance (Bundle arguments){
        Tab_facebook fragment = new Tab_facebook();
        if (arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public Tab_facebook(){}

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        //FacebookSdk.getApplicationSignature(getContext());
/*        Log.e("hasd facebook " , "nameClass  :: "+ getClass().getName());
        Log.e("hasd facebook " , "value :: "+ FacebookSdk.getApplicationSignature(getContext()));*/
        View view  = inflater.inflate(R.layout.fragment_facebook,container,false);

        info = (TextView) view.findViewById(R.id.info);
        loginButton = (LoginButton)view.findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
                info.setTextColor(R.color.colorAccent);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
      //  getAppKeyHash();
        return  view ;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

   /* private void getAppKeyHash() {
        try {
            PackageInfo info = getContext().getPackageManager().getPackageInfo(
                    getContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                System.out.println("HASH  " + something);
                showSignedHashKey(something);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {

            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }
    public void showSignedHashKey(String hashKey) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        adb.setTitle("Note Signed Hash Key");
        adb.setMessage(hashKey);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        adb.show();
    }*/

}
