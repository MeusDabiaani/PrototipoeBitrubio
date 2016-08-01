package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

/**
 * Created by Orion on 04/07/2016.
 */
public class Tab_misSubastas extends Fragment {

    private RecyclerView mRecyclerView;
    private SubastasAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Subastas> subastas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_mis_subastas,container,false);
        int drawable[] = {R.drawable.img_subastas, R.drawable.img_subastas, R.drawable.img_subastas};
        subastas = new ArrayList<>();
        subastas.add(new Subastas(1,2,
                "Operación de naríz","Cirugia estetica","12/06/2016","10",
                "Tengo problemas para respirar y ronco mucho, ya probé de todo pero es inutil.",
                drawable[0],2));
        subastas.add(new Subastas(2,1,"Bypass Gástrico","Cirugia funcional","12/06/2016","16",
                "Peso 180 kg y quiero bajr de peso, soy padre de familia de 3 niños pequeños y me gustaria jugar con ellos.",
                0,4));
        subastas.add(new Subastas(2,1,"Bypass Gástrico","Cirugia funcional","12/06/2016","16",
                "Peso 180 kg y quiero bajr de peso, soy padre de familia de 3 niños pequeños y me gustaria jugar con ellos.",
                drawable[1],1));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_misSubatas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubastasAdapter(subastas, getContext());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
