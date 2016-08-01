package com.bitrubio.prototipoebitrubio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Orion on 17/06/2016.
 */
public class TarjetasAdapter extends RecyclerView.Adapter<TarjetasAdapter.ViewHolder> {
    private ArrayList<Tarjetas> datos;
    private Context context;
    private Tarjetas tarjetas;
    private OnItemClickListener mItemClickListener;
    private FragmentManager FragManager;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public TarjetasAdapter(ArrayList<Tarjetas> datos, Context context ) {
        this.datos = datos;
        this.context = context;


    }
    public TarjetasAdapter(ArrayList<Tarjetas> datos, Context context , FragmentManager fr) {
        this.datos = datos;
        this.context = context;
        this.FragManager = fr;

    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getId();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public TarjetasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarjetaView = inflater.inflate(R.layout.row_tarjeta, parent, false);
        ViewHolder viewHolder = new ViewHolder(tarjetaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TarjetasAdapter.ViewHolder holder, int position) {
        tarjetas = datos.get(position);
        holder.getTxtTarjeta().setText(tarjetas.getNombre_tarjeta());
        holder.getTxtnum1().setText("" + tarjetas.getNum1());
        holder.getTxtnum2().setText("" + tarjetas.getNum2());
        holder.getTxtnum3().setText("" + tarjetas.getNum3());
        holder.getTxtnum4().setText("" + tarjetas.getNum4());
        holder.getTxtVigencia().setText(tarjetas.getVigencia());
        Drawable myDrawable = tarjetas.getFondoColor();
        Bitmap fotoTest = ((BitmapDrawable) myDrawable).getBitmap();
        holder.getImgFondo().setImageBitmap(fotoTest);
        holder.getCardView().setCardBackgroundColor(context.getResources().getColor(R.color.white));
        holder.getImgBotonquitar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "quitar", Toast.LENGTH_SHORT).show();

                DialogBorrarTarjeta dialog = new DialogBorrarTarjeta();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
                dialog.show(FragManager, "titulo");
            }
        });

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTarjeta;
        private TextView txtnum1, txtnum2, txtnum3, txtnum4;
        private TextView txtVigencia;
        private CardView cardView;
        private ImageView imgFondo;
        private ImageView imgBotonquitar;

        public ImageView getImgFondo() {
            return imgFondo;
        }

        public TextView getTxtTarjeta() {
            return txtTarjeta;
        }

        public TextView getTxtnum1() {
            return txtnum1;
        }

        public TextView getTxtnum2() {
            return txtnum2;
        }

        public TextView getTxtnum3() {
            return txtnum3;
        }

        public TextView getTxtnum4() {
            return txtnum4;
        }

        public TextView getTxtVigencia() {
            return txtVigencia;
        }

        public CardView getCardView() {
            return cardView;
        }

        public ImageView getImgBotonquitar() {
            return imgBotonquitar;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            txtTarjeta = (TextView) itemView.findViewById(R.id.txtTarjeta);
            txtnum1 = (TextView) itemView.findViewById(R.id.txtnum1);
            txtnum2 = (TextView) itemView.findViewById(R.id.txtnum2);
            txtnum3 = (TextView) itemView.findViewById(R.id.txtnum3);
            txtnum4 = (TextView) itemView.findViewById(R.id.txtnum4);
            txtVigencia = (TextView) itemView.findViewById(R.id.txtVigencia);
            cardView = (CardView) itemView.findViewById(R.id.card_view_tarjeta);
            imgFondo = (ImageView) itemView.findViewById(R.id.img_fondo);
            imgBotonquitar = (ImageView) itemView.findViewById(R.id.btnQuitar);
            imgFondo.setOnClickListener(this);

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
