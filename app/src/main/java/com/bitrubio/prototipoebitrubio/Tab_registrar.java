package com.bitrubio.prototipoebitrubio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 11/07/2016.
 */
public class Tab_registrar extends Fragment {

    @Bind(R.id.btn_signup)
    ImageButton btnAceptar;

    @Bind(R.id.btn_cancel)
    ImageButton btnCancelar;

    String TAG = getClass().getSimpleName();
    FragmentTransaction FT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        ButterKnife.bind(this,v);
        // Vista fragmen_pagar_persona
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),PagarPersona.class);
                startActivity(intent);

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
