package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.Comunidad;
import com.bitrubio.prototipoebitrubio.Bitrubian.ContactoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 25/02/2016.
 * fragmento amigos
 */
public class FragmentAmigos extends Fragment {
    private ListView listViewSolicitudes;
    private ListView listViewAmigos;

    private SolicitudesAdapter listSolicitudes; // revisar
    private AmigosAdapter listAmigos;

    Typeface tf;
    String TAG = getClass().getSimpleName();


    @Bind(R.id.edit_comentario)
    EditText edit_comentrio;


    private RecyclerView mRecyclerView;
    private ContactoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Comunidad> comunidadList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_amigos, parentViewGroup, false);
        ButterKnife.bind(this, rootView);

        comunidadList = new ArrayList<Comunidad>();
        comunidadList.add(new Comunidad(1, "Alberto Rodriguez", 50));
        comunidadList.add(new Comunidad(2, "Vanessa Hernandez", 999));
        comunidadList.add(new Comunidad(3, "Marisol Jimenez", 23));
        comunidadList.add(new Comunidad(4, "Saul Cortez", 14));
        comunidadList.add(new Comunidad(5, "Tania Gonzales", 1));
        comunidadList.add(new Comunidad(6, "Iris Ayala", 2));
        comunidadList.add(new Comunidad(7, "Roberto Altamirano", 1));
        comunidadList.add(new Comunidad(8, "Alberto Rodriguez", 1));
        comunidadList.add(new Comunidad(9, "Vanessa Hernandez", 2));
        comunidadList.add(new Comunidad(10, "Marisol Jimenez", 1));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_contactoComunidad);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mAdapter = new ContactoAdapter(getActivity(),comunidadList);

        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =new ArrayList<>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Mis Solicitudes "));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"Mis Contactos"));
        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        Log.e(TAG,"sections "+dummy);
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getActivity(),R.layout.contacto_header,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));


        mRecyclerView.setAdapter(mSectionedAdapter);


        return rootView;
    }
    private int getLayoutManagerOrientation(int activityOrientation) {
        if (activityOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        } else {
            return LinearLayoutManager.HORIZONTAL;
        }
    }
    private TextView setTypeAndSize(TextView txt) {
        txt.setTextSize(17);
        txt.setTypeface(tf);
        return txt;
    }

}
