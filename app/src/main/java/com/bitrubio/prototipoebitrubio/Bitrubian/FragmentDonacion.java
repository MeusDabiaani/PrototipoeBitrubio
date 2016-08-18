package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.R;

import butterknife.ButterKnife;
import butterknife.Bind;
/**
 * Created by Orion on 06/07/2016.
 * Fragemento vista
 */
public class FragmentDonacion extends Fragment {

    @Bind(R.id.btn_donar)
    Button btnDonar;
    @Bind(R.id.btn_donarme)
    Button btnDonarme;
    String TAG = getClass().getSimpleName();

    View view ;
    FragmentTransaction FT;
    public FragmentDonacion() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_donacion, container, false);
        ButterKnife.bind(this, view);

        btnDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),QuieroDonar.class);
                getActivity().startActivity(intent);
            }
        });

        btnDonarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "boton 2", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
