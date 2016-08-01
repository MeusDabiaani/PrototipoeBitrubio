package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.CarritoAdapter;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Orion on 04/07/2016.
 */
public class CarritoMedicamentoAdapter extends RecyclerView.Adapter<CarritoMedicamentoAdapter.ViewHolder> {
    private ArrayList<Medicamento> data;
    private Context context;
    private Medicamento registros;

    public CarritoMedicamentoAdapter(ArrayList<Medicamento> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public CarritoMedicamentoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_medicamentos_carrito,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarritoMedicamentoAdapter.ViewHolder holder, int position) {
        registros = data.get(position);
        Picasso.with(context).load(registros.getImg()).into(holder.getImgMedicamento());
        holder.getTxtMedicamneto().setText(registros.getNombre());
        holder.getTxtActivo().setText(registros.getActivo());
        holder.getTxtActivo().setText(registros.getLaboratorio());
        holder.getTxtCantidad().setText(""+registros.getCantidad());
        holder.getTxtCosto().setText(""+registros.getCosto());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMedicamento;
        private TextView txtMedicamneto,txtActivo,txtLaboratorio ,txtCantidad,txtCosto;

        public ImageView getImgMedicamento() {
            return imgMedicamento;
        }

        public TextView getTxtMedicamneto() {
            return txtMedicamneto;
        }

        public TextView getTxtActivo() {
            return txtActivo;
        }

        public TextView getTxtLaboratorio() {
            return txtLaboratorio;
        }

        public TextView getTxtCantidad() {
            return txtCantidad;
        }

        public TextView getTxtCosto() {
            return txtCosto;
        }

        public ViewHolder(View v) {
            super(v);

            imgMedicamento = (ImageView) v.findViewById(R.id.img_medicamento);
            txtMedicamneto = (TextView) v.findViewById(R.id.txt_nomMedicamento);
            txtActivo = (TextView) v.findViewById(R.id.txt_nomActivo);
            txtLaboratorio =  (TextView) v.findViewById(R.id.txt_nomLaboratorio);
            txtCantidad = (TextView) v.findViewById(R.id.txt_numMedicamtos);
            txtCosto = (TextView) v.findViewById(R.id.txt_costo);
        }
    }
}
