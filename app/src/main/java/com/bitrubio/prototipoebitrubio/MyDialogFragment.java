package com.bitrubio.prototipoebitrubio;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DialerFilter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.widget.LinearLayout;
/**
 * Created by Orion on 16/04/2016.
 */
public class MyDialogFragment extends DialogFragment {
    Typeface tf;
    @Bind(R.id.txt_term1)
    TextView txtTermino1;

    @Bind(R.id.txt_term2)
    TextView txtTermino2;

    @Bind(R.id.txt_term3)
    TextView txtTermino3;

    @Bind(R.id.txt_term4)
    TextView txtTermino4;

    @Bind(R.id.txt_term5)
    TextView txtTermino5;

    @Bind(R.id.btn_acpetoTermino)
    Button btnAcepto;

    @Bind(R.id.btn_canceloTermino)
    Button btnCancelar;

    @Bind(R.id.btn_cerrar)
    Button btnCerrar;

    LinearLayout lnr_opciones,lnr_cerrar;
    int value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_terminos, container, false);
        ButterKnife.bind(this, rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        Bundle bundle = this.getArguments();

        if(bundle != null) {
            value = bundle.getInt("value", 0);
        }else{
            value = 0 ;
        }

        lnr_cerrar = (LinearLayout) rootView.findViewById(R.id.lnr_cerrar);
        lnr_opciones = (LinearLayout) rootView.findViewById(R.id.lnr_opciones);
        SetTypeSize(txtTermino1);
        SetTypeSize(txtTermino2);
        SetTypeSize(txtTermino3);
        SetTypeSize(txtTermino4);
        SetTypeSize(txtTermino5);


        if (value==1){
            lnr_opciones.setVisibility(View.GONE);
            lnr_cerrar.setVisibility(View.VISIBLE);
        }else{
            lnr_opciones.setVisibility(View.VISIBLE);
            lnr_cerrar.setVisibility(View.GONE);
        }

        btnAcepto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AppCompatCheckBox chkCondiciones = (AppCompatCheckBox) getActivity().findViewById(R.id.checkboxCondiciones);
                chkCondiciones.setChecked(true);
                getDialog().dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AppCompatCheckBox chkCondiciones = (AppCompatCheckBox) getActivity().findViewById(R.id.checkboxCondiciones);
                chkCondiciones.setChecked(false);
                getDialog().dismiss();
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }

    private TextView SetTypeSize(TextView txt) {
        txt.setTypeface(tf);
        txt.setTextSize(14);
        return txt;
    }
}
