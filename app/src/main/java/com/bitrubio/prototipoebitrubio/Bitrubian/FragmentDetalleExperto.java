package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 21/06/2016.
 * Muestra el fragmento con los detalles del experto
 */
//TODO falta cerar el backend para recuperar la info del experto
public class FragmentDetalleExperto extends Fragment{
    Typeface tf;
    @Bind(R.id.txt_descripcion)
    TextView txtDescripcion;

    @Bind(R.id.txt_estudios)
    TextView txtEstudios;

    @Bind(R.id.txt_promociones)
    TextView txtPromociones;

    @Bind(R.id.txt_actualizaciones)
    TextView txtActualizaciones;

    @Bind(R.id.txt_instalaciones)
    TextView txtInstalaciones;

    @Bind(R.id.txt_seguros)
    TextView txtSeguros;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.layout_expero_detalle, container, false);
        ButterKnife.bind(this,rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        setTypeAndSize(txtDescripcion).setTypeface(tf,Typeface.ITALIC);
        setTypeAndSize(txtEstudios);
        setTypeAndSize(txtPromociones);
        setTypeAndSize(txtActualizaciones);
        setTypeAndSize(txtInstalaciones);
        setTypeAndSize(txtSeguros);
        return rootView;
    }
    private TextView setTypeAndSize(TextView textView) {
        textView.setTypeface(tf);
        textView.setTextSize(14);
        return textView;
    }
}
