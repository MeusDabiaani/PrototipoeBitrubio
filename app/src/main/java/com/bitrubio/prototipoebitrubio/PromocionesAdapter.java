package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Paint;
import java.util.ArrayList;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.bitrubio.prototipoebitrubio.Entidades.Anuncios;

/**
 * Created by Orion on 09/06/2016.
 */
public class PromocionesAdapter extends RecyclerView.Adapter<PromocionesAdapter.ViewHolder>{
    private ArrayList<Anuncios> data;
    private Context context;
    private Anuncios registros;
    Typeface tf;

    public PromocionesAdapter(ArrayList<Anuncios> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }
    @Override
    public PromocionesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_anuncios,parent,false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(PromocionesAdapter.ViewHolder holder, int position) {

        registros = data.get(position);
        holder.getTxt_provedor().setText(registros.getNom_proveedor());
        holder.getTxt_titulo().setText(registros.getTitulo());
        holder.getTxt_monto_ini().setText("$ "+registros.getMonto_ini());

        holder.getTxt_monto_ini().setPaintFlags(holder.getTxt_monto_ini().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.getTxt_monto_fin().setText("$ "+registros.getMonto_fin());
        holder.getTxt_calificacion().setText("Calificacion "+registros.getCalificacion());
        holder.getTxt_nm_aplausos().setText(""+registros.getAplausos());
        holder.getTxt_num_msg().setText(""+registros.getMensajes());
        holder.getImg_anuncio().setBackground(registros.getImagen());
        //holder.getTxt_estrellas().setText(""+registros.getCalificacion());

        Drawable myDrawable = registros.getLogo();
        Bitmap fotoTest = ((BitmapDrawable) myDrawable).getBitmap();
        holder.getImg_logo().setImageBitmap(fotoTest);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_provedor;
        TextView txt_titulo;
        TextView txt_monto_ini;
        TextView txt_monto_fin;
        TextView txt_calificacion;
        TextView txt_estrellas;
        TextView txt_num_msg;
        TextView txt_nm_aplausos;
        ImageView img_anuncio;
        ImageView img_logo;
        RatingBar ratingBar;


        public TextView getTxt_provedor() {
            return txt_provedor;
        }

        public TextView getTxt_titulo() {
            return txt_titulo;
        }

        public TextView getTxt_monto_ini() {
            return txt_monto_ini;
        }

        public TextView getTxt_monto_fin() {
            return txt_monto_fin;
        }

        public TextView getTxt_calificacion() {
            return txt_calificacion;
        }

        public TextView getTxt_num_msg() {
            return txt_num_msg;
        }

        public TextView getTxt_nm_aplausos() {
            return txt_nm_aplausos;
        }

        public ImageView getImg_anuncio() {
            return img_anuncio;
        }

        public TextView getTxt_estrellas() {
            return txt_estrellas;
        }

        public ImageView getImg_logo() {
            return img_logo;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            tf = Typeface.createFromAsset(context.getAssets(),"fonts/avenir-light.ttf");
            txt_provedor = (TextView) itemView.findViewById(R.id.txt_provedor_nombre);
            img_anuncio = (ImageView) itemView.findViewById(R.id.img_anuncio);
            txt_titulo = (TextView) itemView.findViewById(R.id.txt_titulo);
            txt_monto_ini = (TextView) itemView.findViewById(R.id.txt_montoIni);
            txt_monto_fin = (TextView) itemView.findViewById(R.id.txt_monto_fin);
            txt_calificacion = (TextView) itemView.findViewById(R.id.txt_calificacion);
            txt_nm_aplausos =  (TextView) itemView.findViewById(R.id.txt_aplausos);
            txt_num_msg = (TextView) itemView.findViewById(R.id.txt_comentarios);
            /*txt_estrellas = (TextView) itemView.findViewById(R.id.txt_estrellas);*/
            img_logo = (ImageView) itemView.findViewById(R.id.img_proveedor);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

            txt_provedor.setTypeface(tf);
            txt_titulo.setTypeface(tf);
            txt_monto_ini.setTypeface(tf);
            txt_monto_fin.setTypeface(tf);
            txt_calificacion.setTypeface(tf);
            txt_num_msg.setTypeface(tf);
            txt_nm_aplausos.setTypeface(tf);
            //txt_estrellas.setTypeface(tf);

            ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 //   txt_estrellas.setText(String.valueOf(rating));
                }
            });

        }





    }
}
