package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.Entidades.Ofertas;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Orion on 05/07/2016.
 * Fragment con los datos de los las ofertas
 */
public class FragmentSubastas extends Fragment{

    private RecyclerView mRecyclerView;
    private OfertasSubastasAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Ofertas> ofertasList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subastas_lista,container,false);
        ButterKnife.bind(this,view);
        int drawable[] = {R.drawable.doctor_2, R.drawable.doctor_1, R.drawable.doctor_3};

        String referencias[] = {"Dra.Alejandra Carrera López","Dr.Agustín Romo Jiménez"};

        ofertasList = new ArrayList<>();
        ofertasList.add(new Ofertas(1,drawable[0],"Ximena Olvera Rubio","Cirujana Estética",
                "27/jul/2016",
                "Hola, yo te puedo ayudar me especializo en casos como el tuyo, tengo un 98% de éxito en un procedimiento como el tuyo.",
                86000.0f,true,"Tarjeta de crédito","AXA,Mamfre,Metlife","Material para Cirugia , anestesia , enfermeria , 5 conultas de seguimiento","Microcirugia,Camara Hyperbarica",referencias));
        ofertasList.add(new Ofertas(2,drawable[1],"Fernando Rojas Tamez","Cirujano","26/jul/2016",
                "Hola, yo te puedo ayudar me especializo en casos como el tuyo, tengo un 98% de éxito en un procedimiento como el tuyo.",
                89500.0f,true,"Tarjeta de débito", "AXA,Mamfre,GNP","Material quirurgico, 5 consultas de seguimiento","Lasser,Ultrasonidos",referencias));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_lista_subastas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OfertasSubastasAdapter(ofertasList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
