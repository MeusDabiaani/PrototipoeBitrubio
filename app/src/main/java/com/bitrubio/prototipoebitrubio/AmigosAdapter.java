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
import java.util.List;

/**
 * Created by Orion on 26/05/2016.
 */
public class AmigosAdapter  extends ArrayAdapter<Comunidad> {
    private final Context context;
    private final  ArrayList<Comunidad>  values;
    String TAG = getClass().getSimpleName();
    LayoutInflater inflater;
    GlobalMetaPeso globalMeta;

    int pantalla ;


    public AmigosAdapter(Context context,  ArrayList<Comunidad>  values , int vista) {
        super(context, R.layout.content_amigos_comunidad, values);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.values = values;
        this.pantalla = vista;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Comunidad comunidad = (Comunidad) this.getItem(position);
        final CheckBox chk_bitrubian ;

        if (convertView == null ) {
            convertView = inflater.inflate(R.layout.content_amigos_comunidad, null);
            ImageView imagePerson = (ImageView) convertView.findViewById(R.id.img_comunidad_amigo);
            TextView textNombre = (TextView) convertView.findViewById(R.id.txt_amigo_nombre);
            TextView textBeats = (TextView) convertView.findViewById(R.id.txt_beats);
            textNombre.setText(values.get(position).getNombre());
            textBeats.setText("" + values.get(position).getNumbeat());
            chk_bitrubian= (CheckBox) convertView.findViewById(R.id.chk_bitrubian);

            convertView.setTag(new ComunidadViewHolder(chk_bitrubian));
            if (pantalla != 1) {chk_bitrubian.setVisibility(View.GONE);}
            chk_bitrubian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CheckBox cb = (CheckBox) v;
                    values.get(position).setChecked(cb.isChecked());
                }
            });
        }else{
            ComunidadViewHolder viewHolder = (ComunidadViewHolder) convertView.getTag();
            chk_bitrubian = viewHolder.getCheckBox();
        }
        chk_bitrubian.setTag(values);
        chk_bitrubian.setChecked(values.get(position).isChecked());

        return convertView;
    }

    public Object onRetainNonConfigurationInstance() {
        return values;
    }


    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    private static class  ComunidadViewHolder{
        private CheckBox checkBox;

        public ComunidadViewHolder() {

        }

        public ComunidadViewHolder(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }


    }


}