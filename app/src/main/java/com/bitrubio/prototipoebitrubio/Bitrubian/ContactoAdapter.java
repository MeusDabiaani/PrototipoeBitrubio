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
 * Adaptador de vista para los contactos
 */

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

    public ContactoAdapter(Context context, ArrayList<Comunidad> data) {
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