package com.bitrubio.prototipoebitrubio;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.Entidades.Anuncios;

import java.util.ArrayList;

/**
 * Created by Orion on 09/06/2016.
 */
public class Tab_promoPromociones extends Fragment {
    private RecyclerView mRecyclerView;
    private PromocionesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Anuncios> anunciosList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_promo_promociones, container, false);

        Drawable int_logchopo = getResources().getDrawable(R.drawable.lab_chopo);
        Drawable int_logolab = getResources().getDrawable(R.drawable.lab_olab);
        Drawable int_logopolanco = getResources().getDrawable(R.drawable.lab_polanco);

        anunciosList = new ArrayList<Anuncios>();
        anunciosList.add(new Anuncios(1, "Laboratorios Chopo",
                "Precios especiales en estudios para la mujer",
                getResources().getDrawable(R.drawable.promociones),
                1500.0f, 750.0f, "12/2/2016", "24/3/2016", 4, 15, 5, 7,int_logchopo));
        anunciosList.add(new Anuncios(1, "Laboratorios Polanco",
                "Check Up Basico",
                getResources().getDrawable(R.drawable.promociones),
                1200.0f, 600.0f, "12/2/2016", "24/3/2016", 1, 17, 2, 9,int_logopolanco));
        anunciosList.add(new Anuncios(1, "Laboratorios Chopo",
                "Precios especiales en estudios para la mujer",
                getResources().getDrawable(R.drawable.promociones),
                1500.0f, 750.0f, "12/2/2016", "24/3/2016", 4, 15, 5, 7,int_logolab));
        anunciosList.add(new Anuncios(1, "Laboratorios Chopo",
                "Check Up Basico",
                getResources().getDrawable(R.drawable.promociones),
                1200.0f, 600.0f, "12/2/2016", "24/3/2016", 1, 17, 2, 9,int_logchopo));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_anuncios);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PromocionesAdapter(anunciosList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
