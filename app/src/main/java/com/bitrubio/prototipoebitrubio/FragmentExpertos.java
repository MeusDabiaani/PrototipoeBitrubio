package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bitrubio.prototipoebitrubio.Entidades.Experto;

import java.util.ArrayList;

/**
 * Created by Orion on 26/02/2016.
 */
public class FragmentExpertos extends Fragment {
    private RecyclerView mRecyclerView;
    private ExpertosAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Experto> expertoList;

    EditText busqueda;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_expertos, parentViewGroup, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        busqueda = (EditText) rootView.findViewById(R.id.editBuscar);
        busqueda.setTypeface(font,Typeface.ITALIC);
        expertoList = new ArrayList<Experto>();
        expertoList.add(new Experto(1, "Fernando Rojas", "Ciudad de Mexico", "Medicina General", "553455661", 2));
        expertoList.add(new Experto(2, "Ximena Olvera", "Ciudad de Mexico", "Cardiologo", "553455661", 3));
        expertoList.add(new Experto(3, "Roberto Medrano", "Ciudad de Mexico", "Medicina General", "553455661", 4));
        expertoList.add(new Experto(4,"Alicia Gonzales","Ciudad de Mexico","Pediatria","553455661",1));
        expertoList.add(new Experto(5,"Fernando Rojas","Ciudad de Mexico","Medicina General","553455661",7));
        expertoList.add(new Experto(6,"Ximena Olvera","Ciudad de Mexico","Cardiologo","553455661",12));
        expertoList.add(new Experto(7,"Roberto Medrano","Ciudad de Mexico","Medicina General","553455661",2));
        expertoList.add(new Experto(8,"Alicia Gonzales","Ciudad de Mexico","Pediatria","553455661",4));
        armaRecyclerView(rootView);
        return rootView;
    }
    public void armaRecyclerView(View v){
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_expertos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExpertosAdapter(expertoList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
