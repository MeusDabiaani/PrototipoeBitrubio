package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 21/07/2016.
 */
public class DialogCompletarPerfil extends DialogFragment{
    @Bind(R.id.btn_cancelarPop)
    ImageButton btnCancelar;

    @Bind(R.id.btn_acepatarPop)
    ImageButton btnAceptarPop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.dialog_completa_perfil,container,false);
        ButterKnife.bind(this,view);
               return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAceptarPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "aceptar", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "cancelar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
