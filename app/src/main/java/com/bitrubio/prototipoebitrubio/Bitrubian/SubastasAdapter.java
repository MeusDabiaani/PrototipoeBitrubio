package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Subastas;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 05/07/2016.
 * adaptador de la vista de als subastas
 */
public class SubastasAdapter extends RecyclerView.Adapter<SubastasAdapter.ViewHolder> {
    private ArrayList<Subastas> data;
    private Context context;
    private Subastas registro;

    public SubastasAdapter(ArrayList<Subastas> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public SubastasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mis_subastas,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(SubastasAdapter.ViewHolder holder, int position) {
        registro = data.get(position);
        holder.txtOperacion.setText(registro.getNombre());
        holder.txtCirugia.setText(registro.getTipoNombre());
        holder.txtVencimineto.setText(""+registro.getFechaFin()+" dias");
        holder.txtMensaje.setText(registro.getComentario());
        holder.txtNumVotos.setText(""+registro.getComentarios());
        holder.imgDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        holder.cardSubasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetalle = new Intent(context,DetalleSubastas.class);
                context.startActivity(intentDetalle);
            }
        });

        if(registro.getImagen()!=0){
            Picasso.with(context).load(registro.getImagen()).into(holder.imgPost);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_verPujas)
        ImageView imgverPujas;

        @Bind(R.id.txtOperacion)
        TextView txtOperacion;

        @Bind(R.id.txtCirugia)
        TextView txtCirugia;

        @Bind(R.id.txtVence)
        TextView txtVencimineto;

        @Bind(R.id.txtComentario)
        TextView txtMensaje;

        @Bind(R.id.txtNumvotos)
        TextView txtNumVotos;

        @Bind(R.id.img_post)
        ImageView imgPost;

        @Bind(R.id.img_Detalle)
        ImageView imgDetalle;

        @Bind(R.id.card_view_subasta)
        CardView cardSubasta;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
