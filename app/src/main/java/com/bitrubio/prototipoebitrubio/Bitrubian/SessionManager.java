package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.bitrubio.prototipoebitrubio.IntroActivity;
import com.bitrubio.prototipoebitrubio.LoginActivity;
import com.bitrubio.prototipoebitrubio.MainActivity;

import java.util.HashMap;

/**
 * Created by Orion on 11/03/2016.
 * maneja el inicio de sesion dentro de la aplicacion
 */


public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_IDUSER = "idUser";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    public static final String KEY_APE = "apellido";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_SUCESS = "email";
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name,String apellido ,String email,String sucess,String idUser){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_IDUSER,idUser);
        // Storing name in pref

        editor.putString(KEY_NAME, name);

        editor.putString(KEY_APE, apellido);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_SUCESS, sucess);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, IntroActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_IDUSER,pref.getString(KEY_IDUSER, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_APE, pref.getString(KEY_APE, null));
        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_SUCESS, pref.getString(KEY_SUCESS, null));
        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, IntroActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, true);
    }
}
