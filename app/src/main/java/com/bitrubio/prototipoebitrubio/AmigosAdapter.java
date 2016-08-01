package com.bitrubio.prototipoebitrubio;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.Comunidad;

import java.util.ArrayList;

/**
 * Created by Orion on 26/05/2016.
 */
public class AmigosAdapter  extends ArrayAdapter<Comunidad> {
    private final Context context;
    private final  ArrayList<Comunidad>  values;

    public AmigosAdapter(Context context,  ArrayList<Comunidad>  values) {
        super(context, R.layout.content_amigos_comunidad, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_amigos_comunidad, parent, false);
        ImageView imagePerson = (ImageView) rowView.findViewById(R.id.img_comunidad_amigo);
        TextView textNombre = (TextView) rowView.findViewById(R.id.txt_amigo_nombre);
        TextView textBeats = (TextView) rowView.findViewById(R.id.txt_beats);
        textNombre.setText(values.get(position).getNombre());
        textBeats.setText(""+values.get(position).getNumbeat());
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