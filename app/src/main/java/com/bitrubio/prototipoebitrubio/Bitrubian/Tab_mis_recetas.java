package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.Entidades.Recetas;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

/**
 * Created by Orion on 27/06/2016.
 * tab con las lsita de la recetas
 */
public class Tab_mis_recetas extends Fragment {
    Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private RecetasAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Recetas> recetasList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_mis_recetas,container,false);

        int drawable[] = {R.drawable.doctor_2,R.drawable.doctor_1,R.drawable.doctor_3};

        recetasList = new ArrayList<Recetas>();
        recetasList.add(new Recetas(1,drawable[1],"Fernando Rojas Tamez","Medicina General","24/may/2016","Tafirol Flex","1 tableta c/12 hrs por 1 semana","24/jun/2016"));
        recetasList.add(new Recetas(2,drawable[0],"Ximena Olvera Ruiz","Pediatra","18/abr/2016","Salbutamol sulfato","1 tableta c/12 hrs por 1 semana","18/mar/2016"));
        recetasList.add(new Recetas(3,drawable[0],"Ximena Olvera Ruiz","Pediatra","18/abr/2016","Salbutamol sulfato","1 tableta c/12 hrs por 1 semana","18/mar/2016"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_recetas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecetasAdapter(recetasList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);



        return view;
    }
}
