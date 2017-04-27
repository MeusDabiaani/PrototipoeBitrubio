package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.Constantes;
import com.bitrubio.prototipoebitrubio.Entidades.Experto;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileExpertosActivity;

import java.util.ArrayList;

/**
 * Created by Mario on 17/02/2016.
 */
public class ExpertosAdapter extends RecyclerView.Adapter<ExpertosAdapter.ViewHolder> {
    private ArrayList<Experto> data;
    private Context context;
    private Experto registros;
    public Constantes mConstantes;

    public ExpertosAdapter(ArrayList<Experto> mData,Context context) {
        this.data = mData;
        this.context = context;
    }
    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }
    @Override
    public ExpertosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conten_recycler_experto, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ExpertosAdapter.ViewHolder holder, final int position) {
        registros = data.get(position);

        holder.getTxt_nombre().setText("Dr. "+registros.getNombre());
        holder.getTxt_especialidad().setText(registros.getEspecialidad());
        holder.getTxt_direccion().setText(registros.getDireccion());
        holder.getTxt_numAmigos().setText(registros.getNumContactos()+" amigos en comun");

        holder.getImg_infoConsulta().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileExpertosActivity.class);
                intent.putExtra("idmedico", getItemId(position));
                context.startActivity(intent);
            }
        });

        final Bundle bundle_perfil = new Bundle();
        holder.getLnr_expertos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle_perfil.putInt(mConstantes.C_ID,data.get(position).getId());
                Intent intent = new Intent(context, ProfileExpertosActivity.class);
                intent.putExtras(bundle_perfil);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nombre;
        private TextView txt_direccion;
        private TextView txt_telefono;
        private TextView txt_especialidad ;
        private TextView txt_numAmigos;
        private ImageView img_infoConsulta;
        private LinearLayout lnr_expertos;

        public LinearLayout getLnr_expertos() {
            return lnr_expertos;
        }

        public TextView getTxt_nombre() {
            return txt_nombre;
        }

        public TextView getTxt_direccion() {
            return txt_direccion;
        }

        public TextView getTxt_telefono() {
            return txt_telefono;
        }

        public TextView getTxt_especialidad() {
            return txt_especialidad;
        }

        public TextView getTxt_numAmigos() {
            return txt_numAmigos;
        }

        public ImageView getImg_infoConsulta() {
            return img_infoConsulta;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            txt_nombre = (TextView) itemView.findViewById(R.id.txt_experto_nombre);
            txt_direccion = (TextView) itemView.findViewById(R.id.txt_dir_experto);
            txt_especialidad = (TextView) itemView.findViewById(R.id.txt_exp_experto);
            txt_numAmigos = (TextView) itemView.findViewById(R.id.txt_num_amigos);
            lnr_expertos = (LinearLayout) itemView.findViewById(R.id.lnr_experto);
            img_infoConsulta = (ImageView) itemView.findViewById(R.id.img_experto_perfil);


        }


    }
}
