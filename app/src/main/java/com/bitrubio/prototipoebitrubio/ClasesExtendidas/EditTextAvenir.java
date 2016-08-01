package com.bitrubio.prototipoebitrubio.ClasesExtendidas;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 26/06/2016.
 */
public class EditTextAvenir extends EditText {


    Typeface myTypeface;

    public EditTextAvenir(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-light.ttf");
            setTypeface(myTypeface);
            //  init(attrs);
        }
    }

    public EditTextAvenir(Context context) {
        super(context);
        isInEditMode();
    }

}
