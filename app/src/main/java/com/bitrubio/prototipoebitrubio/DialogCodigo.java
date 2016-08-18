package com.bitrubio.prototipoebitrubio;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 22/06/2016.
 * dialog para preguntar la contraseña al entrar en el perfil
 */
public class DialogCodigo extends DialogFragment {

    @Bind(R.id.btn_codigo)
    ImageButton btnCodigo;

    @Bind(R.id.edit_codigo_promocion)
    EditText edit_codigo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_codigo_promociones, container, false);
        ButterKnife.bind(this, rootView);
        final String descuento = "500";

        btnCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String confirmarContraseña ;
                confirmarContraseña = edit_codigo.getText().toString();

                if (confirmarContraseña.equals("123")) {

                    TextView txtDescuentoCodigo = (TextView) getActivity().findViewById(R.id.txt_descuentoCodigo);
                    txtDescuentoCodigo.setText("- $" + descuento);
                    getDialog().dismiss();

                } else {
                    Toast.makeText(getActivity(), "codigoError", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
