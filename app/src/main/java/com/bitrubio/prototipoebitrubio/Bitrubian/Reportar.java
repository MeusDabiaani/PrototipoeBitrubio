package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 07/06/2016.
 * Vista para reportar un bitrubian
 */
public class Reportar extends AppCompatActivity {

    Toolbar toolbar;
    Typeface tf;
    @Bind(R.id.lnr_molestando)
    LinearLayout lnrMolestando;

    @Bind(R.id.lnr_falso)
    LinearLayout lnrFalso;

    @Bind(R.id.lnr_sustituto)
    LinearLayout lnrSustituto;

    @Bind(R.id.lnr_ofensivo)
    LinearLayout lnrOfensivo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_bitrubian);
        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setSupportActionBar(toolbar);
        TextView textTitulo = (TextView) toolbar.findViewById(R.id.txt_titulo);
        textTitulo.setText("Reportar");
        textTitulo.setTextSize(16);
        textTitulo.setTypeface(tf);


        lnrMolestando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogReporteRealizado();
            }
        });

        lnrFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReporteRealizado();
            }
        });

        lnrSustituto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogReporteRealizado();
            }
        });

        lnrOfensivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogReporteRealizado();
            }
        });


    }

    private void dialogReporteRealizado() {

        Dialog dialog = new Dialog(Reportar.this, R.style.DialogTheme);
        dialog.setContentView(R.layout.dialog_reporte_realizado);
        dialog.show();

    }
}
