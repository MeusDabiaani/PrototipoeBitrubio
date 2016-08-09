package com.bitrubio.prototipoebitrubio;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Bitrubian.Comunidad;
import com.bitrubio.prototipoebitrubio.Entidades.GlobalMetaPeso;

import java.util.ArrayList;

/**
 * Created by Orion on 26/05/2016.
 */
public class AmigosAdapter  extends ArrayAdapter<Comunidad> {
    private final Context context;
    private final  ArrayList<Comunidad>  values;
    String TAG = getClass().getSimpleName();

    GlobalMetaPeso globalMeta;
    int[] arrayAmigos;
    int pantalla ;
    StringBuilder stringBuilder;
    public AmigosAdapter(Context context,  ArrayList<Comunidad>  values , int vista) {
        super(context, R.layout.content_amigos_comunidad, values);
        this.context = context;
        this.values = values;
        this.pantalla = vista;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_amigos_comunidad, parent, false);
        ImageView imagePerson = (ImageView) rowView.findViewById(R.id.img_comunidad_amigo);
        TextView textNombre = (TextView) rowView.findViewById(R.id.txt_amigo_nombre);
        TextView textBeats = (TextView) rowView.findViewById(R.id.txt_beats);
        textNombre.setText(values.get(position).getNombre());
        textBeats.setText(""+values.get(position).getNumbeat());
        CheckBox chk_bitrubian = (CheckBox) rowView.findViewById(R.id.chk_bitrubian);
        if (pantalla != 1 ){
            chk_bitrubian.setVisibility(View.GONE);
        }

        stringBuilder = new StringBuilder();
        chk_bitrubian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    stringBuilder.append(arrayAmigos[values.get(position).getId()]);
                    Toast.makeText(getContext(), "check ID"+ values.get(position).getId(), Toast.LENGTH_SHORT).show();
                    globalMeta.setRetaAmigos(stringBuilder);
                }
            }
        });
        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}