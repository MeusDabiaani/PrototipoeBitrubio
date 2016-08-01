package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Bitrubian.ComentarioActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.Comunidad;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 25/02/2016.
 */
public class FragmentAmigos extends Fragment {
    private ListView listViewSolicitudes;
    private ListView listViewAmigos;
    private ArrayList<Comunidad> comunidadList;
    private SolicitudesAdapter listSolicitudes;
    private AmigosAdapter listAmigos;
    Typeface tf;

    @Bind(R.id.section_sol)
    TextView txtSectionSolicitudes;

    @Bind(R.id.section_ami)
    TextView txtSectionAmigos;

    @Bind(R.id.edit_comentario)
    EditText edit_comentrio;

    RecyclerView recyclerView ;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_amigos, parentViewGroup, false);
        ButterKnife.bind(this, rootView);

        txtSectionSolicitudes.setText("Solictudes pendientes");
        txtSectionAmigos.setText("Mis amigos");

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

/*        // TODO REVISAR con stycky headers
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_expertos);
        final ContactoAdapter adapter = new ContactoAdapter();
        adapter.add("Animals below!");
       // adapter.addAll(getDummyDataSet());
        recyclerView.setAdapter(adapter);
        // Set layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
       // recyclerView.addItemDecoration(new DividerDecoration(this));

        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(getActivity(), "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.addOnItemTouchListener(touchListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.remove(adapter.getItem(position));
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });*/



        listViewSolicitudes = (ListView) rootView.findViewById(R.id.list_soliciitudes);
        listSolicitudes = new SolicitudesAdapter(getActivity(), comunidadList);
        listViewSolicitudes.setAdapter(listSolicitudes);


        listViewAmigos = (ListView) rootView.findViewById(R.id.list_amigos);
        listAmigos = new AmigosAdapter(getActivity(), comunidadList);
        listViewAmigos.setAdapter(listAmigos);

        edit_comentrio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ComentarioActivity.class);
                startActivity(intent);
            }
        });

        listViewSolicitudes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "myPos " + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("idContacto", "" + id);
                intent.putExtra("varAmigo",0);
                startActivity(intent);
            }
        });

        listViewAmigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "myPos " + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("idContacto", "" + id);
                intent.putExtra("varAmigo",1);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listViewSolicitudes.setNestedScrollingEnabled(true);
            listViewAmigos.setNestedScrollingEnabled(true);

        }
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
