package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.net.Uri;

/**
 * Created by Mario on 27/01/2016.
 */
public class GlobalClass {

    private static GlobalClass instance;
    private Uri img_profile;
    private GlobalClass(){}

    public Uri getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(Uri img_profile) {
        this.img_profile = img_profile;
    }
    public static synchronized GlobalClass getInstance(){
        if (instance == null){
            instance = new GlobalClass();
        }
        return instance;
    }
}
