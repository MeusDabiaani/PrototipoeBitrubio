package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.bitrubio.prototipoebitrubio.Entidades.Metas;

import java.util.ArrayList;

/**
 * Created by Orion on 27/04/2016.
 */
public class FragmentListMetas extends Fragment {
    private RecyclerView mRecyclerView;
    private TipoMetasAdapter mAdapter;
    //  private RecyclerView.LayoutManager mLayoutManager;
    Typeface tf;
    TextView txt_cabecera;
    FragmentTransaction FT;
    private String TAG = getClass().getName();
    private GridLayoutManager mLayoutManager;
    ArrayList<Metas> metas;
    int tipoMetas;
    Toolbar toolbar;

    Context context;

    public FragmentListMetas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_metas, parentViewGroup, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);

        context = getActivity();

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        Bundle bundle = this.getArguments();
        tipoMetas= bundle.getInt("tipometas", 0);

        metas = new ArrayList<>();
        if(tipoMetas==1) {
            mTitle.setText(getResources().getString(R.string.metasFisicas));
            metas.add(new Metas(1, "Peso"));
            metas.add(new Metas(2, "Alimentacion"));
            metas.add(new Metas(3, "Ejercicio"));
            metas.add(new Metas(4, "Tomar agua"));
            metas.add(new Metas(5, "Sueño"));
            metas.add(new Metas(6, "Quitar Vicios"));
        }else  if(tipoMetas==2) {
            mTitle.setText(getResources().getString(R.string.metasBienestar));
            metas.add(new Metas(7, "Ayudar"));
            metas.add(new Metas(8, "Desarrollarme"));
            metas.add(new Metas(9, "Reflexionar"));
            metas.add(new Metas(10, "Divertirme"));
            metas.add(new Metas(11, "Estar con otros"));
        }else if(tipoMetas==3){
            mTitle.setText(getResources().getString(R.string.metasEnfermedad));
            metas.add(new Metas(12,"Medicamentos"));
            metas.add(new Metas(13,"Laboratorio"));
            metas.add(new Metas(14,"Niveles"));
            metas.add(new Metas(15,"Chequeo"));
        }

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_tipo_metas);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TipoMetasAdapter(metas, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new TipoMetasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int idmetas) {

                Bundle args = new Bundle();
                final Fragment fragment = new FragmentMetaPeso();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                args.putInt("tipoMeta",tipoMetas);
                args.putInt("position", idmetas);
                fragment.setArguments(args);
                FT.commit();
            }
        });
        txt_cabecera = (TextView) rootView.findViewById(R.id.txt_cabecera);
        txt_cabecera.setTypeface(tf);

        return rootView;
    }


}


