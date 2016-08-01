package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Metas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Orion on 26/04/2016.
 */
public class TipoMetasAdapter extends RecyclerView.Adapter<TipoMetasAdapter.ViewHolder>{
    private OnItemClickListener mItemClickListener;
    private ArrayList<Metas> data;
    private Metas registros;
    private Context context;


    public interface OnItemClickListener{
        void onItemClick(View view ,int position);
    }
    public TipoMetasAdapter(ArrayList<Metas> modelData, Context context ) {
        data = modelData;
        this.context = context;

    }
    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }
    @Override
    public TipoMetasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tipo_metas, parent, false);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(TipoMetasAdapter.ViewHolder holder, int position) {
        registros = data.get(position);
        //final String idMeta = String.valueOf(data.get(position).getId());
     //   holder.getTxt_titulo().setText(registros.getTitulo());
        if(registros.getTitulo().equals("Peso")){
            Picasso.with(context).load(R.drawable.fondo_peso).into(holder.getFondo_card());
        }else if (registros.getTitulo().equals("Alimentacion")){
            Picasso.with(context).load(R.drawable.fondo_alimentacion).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Ejercicio")){
            Picasso.with(context).load(R.drawable.fondo_ejercicio).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Tomar agua")){
            Picasso.with(context).load(R.drawable.fondo_agua).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Sueño")){
            Picasso.with(context).load(R.drawable.fondo_sueno).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Quitar Vicios")){
            Picasso.with(context).load(R.drawable.fondo_vicios).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Ayudar")){
            Picasso.with(context).load(R.drawable.fondo_ayudar).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Desarrollarme")){
            Picasso.with(context).load(R.drawable.fondo_desarrollarme).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Reflexionar")){
            Picasso.with(context).load(R.drawable.fondo_reflexionar).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Divertirme")){
            Picasso.with(context).load(R.drawable.divertirme).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Estar con otros")){
            Picasso.with(context).load(R.drawable.fondo_otros).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Medicamentos")){
            Picasso.with(context).load(R.drawable.fondo_medicamentos).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Laboratorio")){
            Picasso.with(context).load(R.drawable.fondo_laboratorios).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Niveles")){
            Picasso.with(context).load(R.drawable.fondo_terapias).into(holder.getFondo_card());
        }else if(registros.getTitulo().equals("Chequeo")){
            Picasso.with(context).load(R.drawable.fondo_chequeo).into(holder.getFondo_card());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_titulo;
        private CardView cardView;
        private ImageView fondo_card;

        public CardView getCardView() {
            return cardView;
        }

        public ImageView getFondo_card() {
            return fondo_card;
        }

        public TextView getTxt_titulo() {
            return txt_titulo;
        }

        public ViewHolder(View itemView) {
            super(itemView);

           // txt_titulo = (TextView) itemView.findViewById(R.id.txt_titulo_meta);
            fondo_card = (ImageView) itemView.findViewById(R.id.fondo_card);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor( context.getResources().getColor(R.color.white));

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, (int) getItemId());
            }
            return;
        }
    }
}