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
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 04/08/2016.
 */
public class FragmentMetaAgua extends Fragment implements View.OnClickListener{
    int varSeleccion ;
    LinearLayout lnrObjetivo , lnrTiempo , lnrReta, lnrRedApoyo, lnrPrivacidad;
    FragmentTransaction FT;
    Toolbar toolbar;
    Typeface tf;
    public static FragmentMetaAgua newInstance (Bundle args){
        FragmentMetaAgua fragment = new FragmentMetaAgua();
        if (args != null){
            fragment.setArguments(args);
        }
        return fragment;
    }
    public FragmentMetaAgua() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meta_formulario,container,false);
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
        switch (v.getId()) {

            case R.id.lnr_objetivo:

                mTitle.setTextSize(16);
                mTitle.setTypeface(tf);
                mTitle.setText("Tomar agua");
                arguments.putInt("tipo", 1);
                AguaMeta fragment1 = AguaMeta.newInstance(arguments);
                FT.replace(R.id.fragment_tipoMetas, fragment1);
                FT.commit();
                break;
            case R.id.lnr_tiempo:

                mTitle.setTextSize(16);
                mTitle.setTypeface(tf);
                mTitle.setText("Tiempo");
                arguments.putInt("tipo", 2);
                TiempoMeta fragment2 = TiempoMeta.newInstance(arguments);
                FT.replace(R.id.fragment_tipoMetas, fragment2);
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
