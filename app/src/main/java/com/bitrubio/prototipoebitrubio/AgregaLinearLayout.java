package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by Orion on 28/07/2016.
 */
public class AgregaLinearLayout {

    Context context;
    int id ;

    public AgregaLinearLayout(Context ctx,  int id) {
        this.context = ctx;
        this.id = id;
    }

    public LinearLayout CreaLayoutHorizontal(){

        LinearLayout lnrchild = new LinearLayout(context);
        // parametros del layout horizontal
        LinearLayout.LayoutParams parmetrosLnrHorizontal = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
        lnrchild.setOrientation(LinearLayout.HORIZONTAL);

        int left = 0;
        int right= 0;
        int top= 48;
        int bottom = 0;

        parmetrosLnrHorizontal.setMargins(left, top, right, bottom);
        lnrchild.setLayoutParams(parmetrosLnrHorizontal);

        return lnrchild;
    }
}
