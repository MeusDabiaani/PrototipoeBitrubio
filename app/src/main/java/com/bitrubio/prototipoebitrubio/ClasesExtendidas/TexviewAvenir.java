package com.bitrubio.prototipoebitrubio.ClasesExtendidas;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.content.res.TypedArray;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 22/06/2016.
 */
public class TexviewAvenir extends android.support.v7.widget.AppCompatTextView {
    Typeface myTypeface ;
    public TexviewAvenir(Context context) {
        super(context);
        isInEditMode();

    }


    public TexviewAvenir(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) {
            myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-light.ttf");
            setTypeface(myTypeface);
        }
    }
}

