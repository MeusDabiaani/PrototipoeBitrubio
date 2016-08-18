package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Recetas;
import com.bitrubio.prototipoebitrubio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Orion on 27/06/2016.
 * adaptador de la lista de recetas
 */
// TODO agregar back
public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.ViewHolder>{

    private ArrayList<Recetas> data;
    private Context context;
    private Recetas registros;

    public RecetasAdapter(ArrayList<Recetas> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public RecetasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conten_recycler_receta, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecetasAdapter.ViewHolder holder, int position) {
        registros = data.get(position);
        Picasso.with(context).load(registros.getImg()).into(holder.getImg_doctor());
        holder.getTxtNomDoctor().setText("Dr."+registros.getMedico());
        holder.getTxtEspecialidad().setText(registros.getEspecialidad());
        holder.getTxtFecha().setText(registros.getFecha());

        holder.getTxtMedicamento().setText( registros.getNomMedicamento());
        holder.getTxtDosis().setText(registros.getDosis());
        holder.getLnr_receta().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentDetallaReceta = new Intent(context ,DetalleReceta.class);
                context.startActivity(intentDetallaReceta);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout lnr_receta;
        private ImageView img_doctor,imgDetalles;
        private TextView  txtNomDoctor,txtEspecialidad,txtFecha,txtMedicamento,txtDosis,txtVigencia,txtPunto;

        public ImageView getImg_doctor() {
            return img_doctor;
        }

        public ImageView getImgDetalles() {
            return imgDetalles;
        }

        public TextView getTxtNomDoctor() {
            return txtNomDoctor;
        }

        public TextView getTxtEspecialidad() {
            return txtEspecialidad;
        }

        public TextView getTxtFecha() {
            return txtFecha;
        }

        public TextView getTxtMedicamento() {
            return txtMedicamento;
        }

        public TextView getTxtDosis() {
            return txtDosis;
        }

        public TextView getTxtVigencia() {
            return txtVigencia;
        }

        public TextView getTxtPunto() {
            return txtPunto;
        }

        public LinearLayout getLnr_receta() {
            return lnr_receta;
        }

        public ViewHolder(View v) {
            super(v);
            img_doctor = (ImageView) v.findViewById(R.id.img_doctor);
            imgDetalles = (ImageView) v.findViewById(R.id.img_detalles);
            txtNomDoctor = (TextView) v.findViewById(R.id.txt_medico);
            txtEspecialidad = (TextView) v.findViewById(R.id.txt_especialidad);
            txtFecha = (TextView) v.findViewById(R.id.txt_fecha);
            txtMedicamento = (TextView) v.findViewById(R.id.txt_medicamento);
            txtDosis = (TextView) v.findViewById(R.id.txt_dosis);
            txtVigencia=  (TextView) v.findViewById(R.id.txt_fechaVigencia);
            lnr_receta = (LinearLayout) v.findViewById(R.id.lnr_receta);

        }
    }
}
