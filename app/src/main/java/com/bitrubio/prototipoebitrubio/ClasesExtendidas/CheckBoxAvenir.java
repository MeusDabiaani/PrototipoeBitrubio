package com.bitrubio.prototipoebitrubio.ClasesExtendidas;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Orion on 30/06/2016.
 */
public class CheckBoxAvenir extends CheckBox{
    public CheckBoxAvenir(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-light.ttf");
        setTypeface(myTypeface);
    }
}
