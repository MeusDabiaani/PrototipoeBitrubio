package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.Servicios;
import com.bitrubio.prototipoebitrubio.HelperRecyclerView.ItemTouchHelperAdapter;
import com.bitrubio.prototipoebitrubio.HelperRecyclerView.ItemTouchHelperViewHolder;
import com.bitrubio.prototipoebitrubio.HelperRecyclerView.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by Orion on 15/03/2016.
 */
public class ServiciosListaAdapter extends RecyclerView.Adapter<ServiciosListaAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Servicios> data;
    private Context context;
    private final OnStartDragListener mDragStartListener;
    Typeface tf ;
    public ServiciosListaAdapter(ArrayList<Servicios> mDatta, Context context , OnStartDragListener dragStartListener) {
        this.context = context;
        this.data = mDatta;
        mDragStartListener = dragStartListener;
    }

    @Override
    public ServiciosListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_servicios_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ServiciosListaAdapter.ViewHolder holder, final int position) {
         tf = Typeface.createFromAsset(context.getAssets(),"fonts/avenir-light.ttf");
        int lugar = position+1;
        holder.txt_nomServicio.setText(data.get(position).getNombre());
        holder.txt_nomServicio.setTypeface(tf);
        holder.txt_idOrden.setText(""+lugar);
        holder.txt_idOrden.setTypeface(tf);
        holder.btn_move.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_MOVE) {
                    mDragStartListener.onStartDrag(holder);

                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size()-1);
        notifyDataSetChanged();
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

        Collections.swap(data, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemRangeChanged(fromPosition,toPosition+1);
        notifyDataSetChanged();
        return false;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        public final TextView txt_nomServicio;
        public final TextView txt_idOrden;
        public ImageButton btn_move;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_nomServicio = (TextView) itemView.findViewById(R.id.txt_nom_servicio);
            txt_idOrden = (TextView) itemView.findViewById(R.id.txt_id_orden);
            btn_move = (ImageButton) itemView.findViewById(R.id.img_move_item);

        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.TRANSPARENT);

        }
        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }


    }


}

