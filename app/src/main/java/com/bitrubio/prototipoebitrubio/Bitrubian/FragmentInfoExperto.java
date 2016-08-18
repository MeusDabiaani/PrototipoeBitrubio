package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Paint;
/**
 * Created by Orion on 20/06/2016.
 * vista del fragmentto principal expertos
 */
//TODO falta crear Back
public class FragmentInfoExperto extends Fragment {
    @Bind(R.id.txt_descripcion)
    TextView txtDescripcion;
    @Bind(R.id.txt_opiniones)
    TextView txtOpiniones;
    @Bind(R.id.txt_beats)
    TextView txtBeats;
    @Bind(R.id.txt_num_pacientes)
    TextView txtPacientes;
    @Bind(R.id.txt_idiomas)
    TextView txtIdiomas;
    @Bind(R.id.txt_amigosComun)
    TextView txtAmigosComun;
    @Bind(R.id.txt_costo)
    TextView txtCosto;

    @Bind(R.id.btn_agregar_comunidad)
    Button btn_AgregarComuidad_;

    Typeface tf;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.layout_expero_info, container, false);
        ButterKnife.bind(this,rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        //txtDescripcion.setPaintFlags(txtDescripcion.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        setTypeAndSize(txtDescripcion).setTypeface(tf,Typeface.ITALIC);
        setTypeAndSize(txtOpiniones);
        setTypeAndSize(txtBeats);
        setTypeAndSize(txtPacientes);
        setTypeAndSize(txtIdiomas);
        setTypeAndSize(txtAmigosComun);
        setTypeAndSize(txtCosto);
        setTypeAndSize(btn_AgregarComuidad_);
        return rootView;
    }

    private TextView setTypeAndSize(TextView textView) {
        textView.setTextSize(14);
        textView.setTypeface(tf);
        return textView;
    }
}
