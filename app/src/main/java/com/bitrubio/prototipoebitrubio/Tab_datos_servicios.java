package com.bitrubio.prototipoebitrubio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bitrubio.prototipoebitrubio.Bitrubian.Servicios;
import com.bitrubio.prototipoebitrubio.HelperRecyclerView.OnStartDragListener;
import com.bitrubio.prototipoebitrubio.HelperRecyclerView.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.bitrubio.prototipoebitrubio.ServiciosListaAdapter.*;


/**
 * Created by Orion on 29/02/2016.
 */
public class Tab_datos_servicios extends Fragment implements OnStartDragListener {
    private RecyclerView mRecyclerView;
    private ServiciosListaAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Servicios> serviciosList;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.txt_titulo_servicios)
    TextView txt_tituloServicio;
    Typeface tf;
    public Tab_datos_servicios(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_datos_servicios, container, false);
        ButterKnife.bind(this, v);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_datos_servicios);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ServiciosListaAdapter(getRegisros(), getActivity(),this);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        setTypeAndSize(txt_tituloServicio);

        return v;
    }

    private ArrayList<Servicios> getRegisros(){
        serviciosList = new ArrayList<Servicios>();
        serviciosList.add(new Servicios(1, "Disponibilidad de agenda",1));
        serviciosList.add(new Servicios(2, "Amabilidad en recepcion",2));
        serviciosList.add(new Servicios(3, "Puntualidad en la consulta",3));
        serviciosList.add(new Servicios(4, "Amabilidad en la consulta", 4));
        serviciosList.add(new Servicios(5, "Explicacion del diagnostico y tratamiento durante la consulta", 5));
        serviciosList.add(new Servicios(6, "Efectividad del diagnostico y tratamiento",6));
        serviciosList.add(new Servicios(7, "Estudios y Tratamientos económicos", 7));
        serviciosList.add(new Servicios(8, "Disponibilidad para llamadas",8));
        return serviciosList;
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    private TextView setTypeAndSize(TextView textView) {

        textView.setTypeface(tf);
        textView.setTextSize(16);

        return textView;
    }
}
