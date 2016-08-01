package com.bitrubio.prototipoebitrubio.Bitrubian;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Orion on 29/07/2016.
 */
/*
public class ContactoAdapter  extends RecyclerView.Adapter<ContactoAdapter.ViewHolder>{
    private ArrayList<Comunidad> data;
    Context context;
    Comunidad registros;
    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public ContactoAdapter(ArrayList<Comunidad> datos, Context context) {
        this.data = datos;
        this.context = context;
    }
    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_solictudes_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        registros = data.get(position);

        holder.getTxtNombre().setText(registros.getNombre());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNombre;
        ImageView imgcontacto;

        public TextView getTxtNombre() {
            return txtNombre;
        }

        public ImageView getImgcontacto() {
            return imgcontacto;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre_sol);
            imgcontacto = (ImageView) itemView.findViewById(R.id.img_comunidad_amigo);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, (int) getItemId());
            }
            return;
        }
    }
}*/
public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<Comunidad> mData;



    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_nombre_sol);
        }
    }

    public ContactoAdapter(Context context, ArrayList data) {
        mContext = context;
        mData = data;
    }
    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.content_solictudes_row, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {

        holder.title.setText(mData.get(position).getNombre());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Position ="+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}