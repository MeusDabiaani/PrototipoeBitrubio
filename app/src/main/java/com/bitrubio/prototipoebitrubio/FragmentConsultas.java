package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 30/05/2016.
 */
public class FragmentConsultas extends Fragment {
    LinearLayout lnr_consultas_rel;
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

    @Bind(R.id.txt_observaciones)
    TextView txtObservaciones;

    @Bind(R.id.txt_conceptoObs)
    TextView txtConceptoObs;

    @Bind(R.id.txt_tittleTratamiento)
    TextView txtTitleTrata;

    @Bind(R.id.txt_conceptpTratamiento)
    TextView txtConceptoTrat;

    @Bind(R.id.txt_costoConsulta)
    TextView txtCostoConsulta;

    @Bind(R.id.txt_montoConsulta)
    TextView txtMontoConsulta;

    @Bind(R.id.txt_calificacion)
    TextView txtCalificacion;

    @Bind(R.id.txt_consultas_realcionadas)
    TextView txtConsRelacionadas;
    Typeface tf;
    FragmentTransaction FT;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consultas, container, false);
        ButterKnife.bind(this, rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");

        inicializaCampos(rootView);
        return rootView;
    }

    private void inicializaCampos(View rootView) {
        txtNomMedico.setTypeface(tf, Typeface.BOLD);

        lnr_consultas_rel = (LinearLayout) rootView.findViewById(R.id.lnr_consultas_relacionadas);
        lnr_consultas_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentConRelacionadas();
                FT = getFragmentManager().beginTransaction();
                FT.add(R.id.rel_contenedor, fragment);
                FT.commit();

            }
        });
        txtObservaciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentObservaciones();
                FT = getFragmentManager().beginTransaction();
                FT.add(R.id.rel_contenedor, fragment);
                FT.commit();
            }
        });

    }

}
