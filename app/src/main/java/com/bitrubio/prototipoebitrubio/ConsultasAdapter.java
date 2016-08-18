package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Experto;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileExpertosActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 14/06/2016.
 *
 */
public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.ViewHolder> {

    private ArrayList<Consultas> data;
    private Context context;
    private Consultas registros;
    private ArrayList<Experto> expertoList;


    public ConsultasAdapter(ArrayList<Consultas> consultaList, Context context) {

        this.data = consultaList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public ConsultasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recycler_consulta, parent, false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ConsultasAdapter.ViewHolder holder, final int position) {
        registros = data.get(position);
        expertoList = new ArrayList<Experto>();
        expertoList.add(new Experto(1, "Fernando Rojas Tamez", "S/N", "Medicina General", "554446613", 5));
        expertoList.add(new Experto(2, "Ximenna Olvera Ramirez", "S/N", "Medicina General", "55463366", 5));
        expertoList.add(new Experto(3, "Raul Gonzalez", "S/N", "Traumatologia", "5564411121", 5));


        int drawable[] = {R.drawable.doctor_1, R.drawable.doctor_2, R.drawable.doctor_3,R.drawable.doctor_1, R.drawable.doctor_2, R.drawable.doctor_3};

        holder.txtMedico.setText(expertoList.get(registros.getIdMedico() - 1).getNombre());
        holder.txtEspecialidad.setText(expertoList.get(registros.getIdMedico() - 1).getEspecialidad());
        holder.txtConDiagnostico.setText(registros.getDiagnostico());
        holder.txtFecha.setText(registros.getFechaConsulta());

        Picasso.with(context).load(drawable[position]).into(holder.imgMedico);
        holder.imgMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileExpertosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idUsuario", registros.getIdMedico());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.getLnrConsultas().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetalleConsultas.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idUsuario", registros.getIdMedico());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_medico)
        ImageView imgMedico;
        @Bind(R.id.txt_nomMedico)
        TextView txtMedico;
        @Bind(R.id.txt_expMedica)
        TextView txtEspecialidad;
        @Bind(R.id.txt_conceptoDiagnostico)
        TextView txtConDiagnostico;

        @Bind(R.id.txt_fechaConsulta)
        TextView txtFecha;
        LinearLayout lnrConsultas;

        public LinearLayout getLnrConsultas() {
            return lnrConsultas;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            lnrConsultas = (LinearLayout) itemView.findViewById(R.id.lnr_consultaMedica);


        }
    }
}
