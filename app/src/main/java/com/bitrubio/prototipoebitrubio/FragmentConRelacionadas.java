package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 30/05/2016.
 */
public class FragmentConRelacionadas extends Fragment {
    Typeface tf;
    Toolbar toolbar;
    @Bind(R.id.txt_nomMedico)
    TextView txtNomMedico;

    @Bind(R.id.txt_especialidad)
    TextView txtEspecialidad;

    @Bind(R.id.txt_fecha)
    TextView txtFecha;


    @Bind(R.id.txt_diagnostico)
    TextView txtDiagnostico;

    @Bind(R.id.txt_tratamiento)
    TextView txtTratamiento;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consultas_relacionadas, container, false);
        ButterKnife.bind(this, rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) getActivity().getWindow().findViewById(R.id.toolbar);
        toolbar.setTitle("");

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText("Consultas \n relacionadas");
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);

        inicializaCampos(rootView);
        return rootView;
    }

    private void inicializaCampos(View rootView) {
        setTypeSize(txtNomMedico).setTypeface(tf, Typeface.BOLD);
        setTypeSize(txtEspecialidad);
        setTypeSize(txtFecha);
        setTypeSize(txtDiagnostico);
        setTypeSize(txtTratamiento);
    }
    private TextView setTypeSize(TextView text) {
        text.setTextSize(14);
        text.setTypeface(tf);
        return text;
    }
}
