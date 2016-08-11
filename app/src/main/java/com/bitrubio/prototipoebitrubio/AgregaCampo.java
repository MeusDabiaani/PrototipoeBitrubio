package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.EditTextAvenir;

import java.util.List;

/**
 * Created by Orion on 28/07/2016.
 * clase para agregar campos dinamicos
 */
public class AgregaCampo  {
    Typeface tf;
    Context context;
    private int id;
    private String hint;
    private String text;
    private int textSize;
    List<EditText> listEdit;

    public AgregaCampo(Context context, int id, String hint,String txt, int textSize, List<EditText> listEdit) {
        this.context = context;
        this.id = id;
        this.hint = hint;
        this.text = txt;
        this.textSize = textSize;
        this.listEdit = listEdit;
    }

    public EditText myEdit(){
        tf = Typeface.createFromAsset(context.getAssets(), "fonts/avenir-light.ttf");
        Resources res = context.getResources();
        EditText editText = new EditTextAvenir(context);
        LinearLayout.LayoutParams parmetrosEdit = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        editText.setLayoutParams(parmetrosEdit);
        editText.setId(id);
        editText.setHint(hint);
        editText.setText(text);
        editText.setTextSize(textSize);
        editText.setSingleLine(true);
        editText.setBackground(null);
        editText.setTextColor(res.getColor(R.color.textColorPrimary));
        editText.setHintTextColor(res.getColor(R.color.textColorhint));
        editText.setTypeface(tf);
        listEdit.add(editText);
        return  editText;

    }

    public ImageView setMyImageView(){
        Resources res = context.getResources();
        ImageView imgView = new ImageView(context);
        // paremetrs de la imagenButton
        LinearLayout.LayoutParams parmsButtonB = new LinearLayout.LayoutParams(72, 72);
        imgView.setLayoutParams(parmsButtonB);
        imgView.setId(id + 100);
        imgView.setImageDrawable(res.getDrawable(R.drawable.ic_menos));

        return imgView;
    }

    public View setMyView(){
        Resources res = context.getResources();
        LinearLayout.LayoutParams parmetrosView = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 2);
        int leftView = 0;
        int rightView = 68;
        int topView = 0;
        int bottomView = 0;

        parmetrosView.setMargins(leftView, topView, rightView, bottomView);
        // izq,arriba.derecha,abajo
        View view = new View(context);
        view.setLayoutParams(parmetrosView);
        view.setBackgroundColor(res.getColor(R.color.iron));
        return view;
    }



}
