package com.bitrubio.prototipoebitrubio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 23/06/2016.
 * dilog para cambiar el color de las tarjetas
 */
public class DialogColor extends DialogFragment  {

    @Bind(R.id.color_1)
    ImageView imgColor1;

    @Bind(R.id.color_2)
    ImageView imgColor2;

    @Bind(R.id.color_3)
    ImageView imgColor3;

    @Bind(R.id.color_4)
    ImageView imgColor4;

    @Bind(R.id.color_5)
    ImageView imgColor5;

    @Bind(R.id.color_6)
    ImageView imgColor6;

    @Bind(R.id.color_7)
    ImageView imgColor7;

    @Bind(R.id.color_8)
    ImageView imgColor8;

    public DialogColor() {
    }
    public interface CambiaColorListener {
        void setBackgroundFondo(int int_drawable);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_cambiar_color, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        imgColor1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            // TODO guardar el color para poder verlo siempre asi
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_azul);
                getDialog().dismiss();

            }
        });

        imgColor2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_verde);
                getDialog().dismiss();
            }
        });
        imgColor3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_anaranjada);
                getDialog().dismiss();
            }
        });
        imgColor4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_roja);
                getDialog().dismiss();
            }
        });
        imgColor5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_rosa);
                getDialog().dismiss();
            }
        });
        imgColor6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_morada);
                getDialog().dismiss();

            }
        });
        imgColor7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_verdeagua);
                getDialog().dismiss();
            }
        });
        imgColor8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CambiaColorListener activity = (CambiaColorListener) getActivity();
                activity.setBackgroundFondo(R.drawable.tarjeta_negra);
                getDialog().dismiss();
            }
        });

    }


}
