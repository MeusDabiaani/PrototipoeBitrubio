package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Ofertas;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 05/07/2016.
 * adpatador para el recyclerview de las subatas
 */
public class OfertasSubastasAdapter extends RecyclerView.Adapter<OfertasSubastasAdapter.ViewHolder> {
    private ArrayList<Ofertas> data;
    private Context context;
    private Ofertas registros;
    public Constantes mConstantes;
    String TAG = getClass().getSimpleName();

    public OfertasSubastasAdapter(ArrayList<Ofertas> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public OfertasSubastasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subastas, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OfertasSubastasAdapter.ViewHolder holder, final int position) {
        registros = data.get(position);

        Picasso.with(context).load(registros.getImg()).into(holder.imgMedico);
        holder.txtnomMedico.setText(registros.getMedico());
        holder.txtEspecialidad.setText(registros.getEspecialidad());
        holder.txtFechaPuja.setText(registros.getFechaPuja());
        holder.txtMontoPuja.setText("$ " + registros.getMontoPuja() + " sin IVA");
        holder.txtComentarioMedico.setText(registros.getComentarios());
        final Bundle bundle = new Bundle();
        holder.imgDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putInt(mConstantes.C_ID,data.get(position).getId());
                bundle.putString(mConstantes.C_NOMBRE,data.get(position).getMedico() );
                bundle.putString(mConstantes.C_ESPECIALIDAD,data.get(position).getEspecialidad() );
                bundle.putString(mConstantes.C_FECHA,data.get(position).getFechaPuja() );
                bundle.putString(mConstantes.C_MONTO, String.valueOf(data.get(position).getMontoPuja()));
                bundle.putString(mConstantes.C_PAGO,data.get(position).getModoPago() );
                bundle.putString(mConstantes.C_ASEGURADORA,data.get(position).getAseguradora() );
                bundle.putString(mConstantes.C_INCLUYE,data.get(position).getIncluye() );
                bundle.putString(mConstantes.C_INCLUYE,data.get(position).getIncluye() );
                bundle.putString(mConstantes.C_SEGUIMIENTO,data.get(position).getIncluye());
                bundle.putString(mConstantes.C_TECNOLOGIA,data.get(position).getTecUtilizada() );
                bundle.putStringArray(mConstantes.C_REFERENCIAS, data.get(position).getReferencias());
                bundle.putString(mConstantes.C_COMENTARIO,data.get(position).getComentarios() );


                Intent detalle_intent = new Intent(context,DetalleCompletoSubasta.class);
                bundle.putInt("imgMedico",data.get(position).getImg());
                detalle_intent.putExtras(bundle);

                context.startActivity(detalle_intent);
            }
        });
        final Bundle bundle_perfil = new Bundle();
        holder.imgMedico.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e(TAG,"id"+getItemId(position));

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


        @Bind(R.id.mas_detalle)
        ImageView imgDetalle;

        @Bind(R.id.img_PerfilMedico)
        ImageView imgMedico;

        @Bind(R.id.txt_nomMedico)
        TextView txtnomMedico;

        @Bind(R.id.txt_especialidad)
        TextView txtEspecialidad;

        @Bind(R.id.txt_fechaPuja)
        TextView txtFechaPuja;

        @Bind(R.id.txtmontoPuja)
        TextView txtMontoPuja;

        @Bind(R.id.txtComentarioMedico)
        TextView txtComentarioMedico;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }



    }
}
