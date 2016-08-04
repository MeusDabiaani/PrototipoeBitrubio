package com.bitrubio.prototipoebitrubio.Metas;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.ButterKnife;

/**
 * Created by Orion on 04/08/2016.
 */
public class FragmentMetaEjercicio extends Fragment implements View.OnClickListener {

    LinearLayout lnrObjetivo , lnrTiempo , lnrReta, lnrRedApoyo, lnrPrivacidad;
    FragmentTransaction FT;
    Toolbar toolbar;
    Typeface tf;
    public static FragmentMetaEjercicio newInstance(Bundle arguments){
        FragmentMetaEjercicio fragment = new FragmentMetaEjercicio();
        if (arguments!=null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }
    public FragmentMetaEjercicio(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meta_formulario,container,false);
        ButterKnife.bind(this,v);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        final TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);

        lnrObjetivo = (LinearLayout) v.findViewById(R.id.lnr_objetivo);
        lnrObjetivo.setOnClickListener(this);

        lnrTiempo = (LinearLayout) v.findViewById(R.id.lnr_tiempo);
        lnrTiempo.setOnClickListener(this);

        lnrReta = (LinearLayout) v.findViewById(R.id.lnr_reta);
        lnrReta.setOnClickListener(this);

        lnrRedApoyo = (LinearLayout) v.findViewById(R.id.lnr_red_apoyo);
        lnrRedApoyo.setOnClickListener(this);

        lnrPrivacidad = (LinearLayout) v.findViewById(R.id.lnr_privacidad);
        lnrPrivacidad.setOnClickListener(this);

        FT = getFragmentManager().beginTransaction();
        FT.setTransition(FragmentTransaction.TRANSIT_NONE);
        FT.addToBackStack(null);


        return v;
    }
    @Override
    public void onClick(View v) {
        Bundle arguments = new Bundle();
        final TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        switch (v.getId()){

            case R.id.lnr_objetivo:

                mTitle.setTextSize(16);
                mTitle.setTypeface(tf);
                mTitle.setText("Objetivo");
                arguments.putInt("tipo", 1);
                TiempoMeta fragment1 = TiempoMeta.newInstance(arguments);
                FT.replace(R.id.fragment_tipoMetas,fragment1);
                FT.commit();
                break;
            case R.id.lnr_tiempo:

                mTitle.setTextSize(16);
                mTitle.setTypeface(tf);
                mTitle.setText("Tiempo");

                arguments.putInt("tipo", 2);
                TiempoMeta fragment2 = TiempoMeta.newInstance(arguments);
                FT.replace(R.id.fragment_tipoMetas,fragment2);
                FT.commit();
                break;
            case R.id.lnr_reta:
                break;
            case R.id.lnr_red_apoyo:
                break;
            case R.id.lnr_privacidad:
                break;
            case R.id.btn_cancel:
                Toast.makeText(getContext(), "Cancelar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_guardar:
                Toast.makeText(getContext(), "Guardar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }




}
