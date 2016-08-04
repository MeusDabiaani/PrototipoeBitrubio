package com.bitrubio.prototipoebitrubio.Metas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.R;
import com.google.android.gms.vision.text.Line;

/**
 * Created by Orion on 04/08/2016.
 */
public class FragmentListVicios extends Fragment implements View.OnClickListener{

    LinearLayout lnrCigarro , lnrBebidas, lnrCafe,lnrAlimentos, lnrVideoJuegos,lnrCelular,lnrOtros;

    public static FragmentListVicios newInstance (Bundle arguments){
        FragmentListVicios fragment = new FragmentListVicios();
        if (arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public FragmentListVicios(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lista_vicios,container,false);

        lnrCigarro = (LinearLayout) v.findViewById(R.id.lnr_vicio_cigarro);
        lnrCigarro.setOnClickListener(this);

        lnrBebidas = (LinearLayout) v.findViewById(R.id.lnr_vicio_bebidas);
        lnrBebidas.setOnClickListener(this);

        lnrCafe = (LinearLayout) v.findViewById(R.id.lnr_vicio_cafe);
        lnrCafe.setOnClickListener(this);

        lnrAlimentos = (LinearLayout) v.findViewById(R.id.lnr_vicio_alimentos);
        lnrAlimentos.setOnClickListener(this);

        lnrVideoJuegos = (LinearLayout) v.findViewById(R.id.lnr_vicio_video);
        lnrVideoJuegos.setOnClickListener(this);

        lnrCelular = (LinearLayout) v.findViewById(R.id.lnr_vicio_celular);
        lnrCelular.setOnClickListener(this);

        lnrOtros = (LinearLayout) v.findViewById(R.id.lnr_vicio_otros);
        lnrOtros.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
//Todo Regresar al fragemtno anterior con el valor selecionado
        switch (v.getId()){

            case R.id.lnr_vicio_cigarro:
                Toast.makeText(getContext(), "cigarro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_bebidas:
                Toast.makeText(getContext(), "bebidas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_cafe:
                Toast.makeText(getContext(), "cafe", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_alimentos:
                Toast.makeText(getContext(), "alimentos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_video:
                Toast.makeText(getContext(), "VideoJuegos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_celular:
                Toast.makeText(getContext(), "celular", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lnr_vicio_otros:
                Toast.makeText(getContext(), "otros", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
