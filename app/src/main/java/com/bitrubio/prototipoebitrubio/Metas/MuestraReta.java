package com.bitrubio.prototipoebitrubio.Metas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Entidades.GlobalMetaPeso;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Orion on 16/08/2016.
 * Crea el Dialog con las fotos de los usuarios selecionados en la reta
 */
public class MuestraReta extends DialogFragment {
    String TAG = getClass().getSimpleName();
    String miVar;
    TextView textView;
    ArrayList<String> arrayReta;
    GlobalMetaPeso globalMetaPeso;
    StringBuilder stringBuilderReta;
    ConectaServidor servidor;
    String URL;
    LinearLayout linearLayout;

    public MuestraReta() {

    }

    public static MuestraReta newInstance(Bundle arguments) {
        MuestraReta dialog = new MuestraReta();
        if (arguments != null) {
            dialog.setArguments(arguments);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_reta, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        textView = (TextView) view.findViewById(R.id.txtprueba);
        globalMetaPeso = GlobalMetaPeso.getInstance(); // recupera la informacion que esta seteada en GlobalMetaPeso
        arrayReta = new ArrayList<>();
        stringBuilderReta = new StringBuilder();
        servidor = new ConectaServidor(); // recupera la ruta que se ocupa para enviar o recivir datos del servidor

        URL = servidor.getUrl() + "fotosPerfil";

        linearLayout = (LinearLayout) view.findViewById(R.id.lnr_imagenes);
        stringBuilderReta = globalMetaPeso.getRetaAmigos(); // recupera el string con los id de los usuarios

        for (int i = 0; i < stringBuilderReta.length(); i++) {
            arrayReta.add(String.valueOf(stringBuilderReta.charAt(i)));
            Log.e(TAG, "integer item  " + stringBuilderReta.charAt(i));
            String getFoto = URL + "/" + String.valueOf(stringBuilderReta.charAt(i)) + ".jpg";
            Log.e(TAG, "rutaFoto" + getFoto);
            CircleImageView img = new CircleImageView(getContext());
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(230, 230, 1);
            linearParams.setMargins(36, 0, 36, 0);
            img.setLayoutParams(linearParams);

            Picasso.with(getContext()).load(URL + "/" + String.valueOf(stringBuilderReta.charAt(i)) + ".jpg").into(img);
            linearLayout.addView(img);
        }

    }
}
