package com.bitrubio.prototipoebitrubio.ClasesExtendidas;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Orion on 30/06/2016.
 */
public class TextViewMedium extends TextView {
    Typeface myTypeface;
    public TextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
             myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-medium.ttf");
            setTypeface(myTypeface);
        }
    }

    public TextViewMedium(Context context) {
        super(context);
        isInEditMode();
    }
}
