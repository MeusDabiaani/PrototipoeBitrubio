package com.bitrubio.prototipoebitrubio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;

import com.bitrubio.prototipoebitrubio.Entidades.Mensajes;

import java.util.ArrayList;

/**
 * Created by Orion on 02/06/2016.
 */
public class FragmentDetalle_Fisico extends Fragment {

    String TAG = getClass().getName();
    private RecyclerView mRecyclerView;
    private MensajesAdadpter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Mensajes> mensajeList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meta_fisic_det_1,container,false);

        cargaRecyclerView(view);
        return view;
    }

    /**
     *
     * @param view
     * arma el recycler view de esta vista
     */

    private void cargaRecyclerView(View view) {
        mensajeList = new ArrayList<Mensajes>();
        mensajeList.add(new Mensajes(1, "Roberto Martinez", "along with a few variations of the drawable/image for different densities", " 1 min", "4"));
        mensajeList.add(new Mensajes(2, "Alejandro Gonzales", "I tried a scaleType of fitCenter and centerCrop ", "1 hr", "2"));
        mensajeList.add(new Mensajes(3, "Vanessa Hernandez", "mensaje mensaje 3", "1.30 hr", "6"));
        mensajeList.add(new Mensajes(4, "Sara Reyes", "I tried a scaleType ", "1 mes", "5"));
        mensajeList.add(new Mensajes(5, "Gustavo Lopez", "mensaje mensaje mensaje 5", "28 dias", "4"));
        mensajeList.add(new Mensajes(6, "Erik Garcia", "mensaje mensaje mensaje 6", "2 semanas", "12"));
        mensajeList.add(new Mensajes(7, "Alberto Chavez", "mensaje mensaje mensaje 7", "8 dias", "12"));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_meta_a);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MensajesAdadpter(mensajeList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
    }
}
