package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Orion on 22/06/2016.
 */
public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder>{
    private ArrayList<Productos> data;
    private Context context;
    private Productos registros;
    Typeface tf;

    public CarritoAdapter(ArrayList<Productos> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.row_productos,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(CarritoAdapter.ViewHolder holder, int position) {
        registros = data.get(position);
        holder.txtDescripcion.setText(registros.getDescripcion());
        holder.editCantidad.setText(""+registros.getCantidad());
        holder.txtPrecio.setText("$ "+registros.getPrecio());
        holder.txtPrecio.setPaintFlags(holder.txtPrecio.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtDescuento.setText(""+registros.getDescuento()+" %");
        holder.txtSubTotal.setText("$ "+550);
        Drawable myDrawable = registros.getImg();
        Bitmap fotoTest = ((BitmapDrawable) myDrawable).getBitmap();
        holder.imgProducto.setImageBitmap(fotoTest);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProducto;
        public TextView txtDescripcion;
        TextView txtCantidad;
        EditText editCantidad;
        TextView txtPrecio;
        TextView txtDescuento;
        TextView txtSubTotal;
        ImageView imgProducto;


        public ViewHolder(View v) {
            super(v);
            txtDescripcion = (TextView) v.findViewById(R.id.txt_descripcion);
            imgProducto = (ImageView) v.findViewById(R.id.imgProducto);
            txtCantidad = (TextView) v.findViewById(R.id.txt_cantidad);
            editCantidad = (EditText) v.findViewById(R.id.edit_cantidad);
            txtPrecio = (TextView) v.findViewById(R.id.txt_precio);
            txtDescuento = (TextView) v.findViewById(R.id.txt_descuento);
            txtSubTotal = (TextView) v.findViewById(R.id.txt_totalProducto);
            imgProducto = (ImageView) v.findViewById(R.id.imgProducto);


        }
    }
}
