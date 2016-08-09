package com.bitrubio.prototipoebitrubio.Metas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AmigosAdapter;
import com.bitrubio.prototipoebitrubio.Bitrubian.ComentarioActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.Comunidad;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileActivity;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

/**
 * Created by Orion on 08/08/2016.
 */
public class Tab_Bitrubians extends Fragment {
    private ListView listViewAmigos;
    private ArrayList<Comunidad> comunidadList;
    private AmigosAdapter listAmigos;
    Typeface tf;

    public static Tab_Bitrubians newInstance (Bundle arguments){
        Tab_Bitrubians fragment = new Tab_Bitrubians();
        if (arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public Tab_Bitrubians(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_bitrubians, container,false);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
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
        comunidadList.add(new Comunidad(11, "Alberto Rodriguez", 50));
        comunidadList.add(new Comunidad(12, "Vanessa Hernandez", 999));
        comunidadList.add(new Comunidad(13, "Marisol Jimenez", 23));
        comunidadList.add(new Comunidad(14, "Saul Cortez", 14));
        comunidadList.add(new Comunidad(15, "Tania Gonzales", 1));
        comunidadList.add(new Comunidad(16, "Iris Ayala", 2));
        comunidadList.add(new Comunidad(17, "Roberto Altamirano", 1));
        comunidadList.add(new Comunidad(18, "Alberto Rodriguez", 1));
        comunidadList.add(new Comunidad(19, "Vanessa Hernandez", 2));
        comunidadList.add(new Comunidad(20, "Marisol Jimenez", 1));
        comunidadList.add(new Comunidad(21, "Alberto Rodriguez", 50));
        comunidadList.add(new Comunidad(22, "Vanessa Hernandez", 999));
        comunidadList.add(new Comunidad(23, "Marisol Jimenez", 23));
        comunidadList.add(new Comunidad(24, "Saul Cortez", 14));
        comunidadList.add(new Comunidad(25, "Tania Gonzales", 1));
        comunidadList.add(new Comunidad(26, "Iris Ayala", 2));
        comunidadList.add(new Comunidad(27, "Roberto Altamirano", 1));
        comunidadList.add(new Comunidad(28, "Alberto Rodriguez", 1));
        comunidadList.add(new Comunidad(29, "Vanessa Hernandez", 2));
        comunidadList.add(new Comunidad(30, "Marisol Jimenez", 1));
        comunidadList.add(new Comunidad(31, "Alberto Rodriguez", 50));
        comunidadList.add(new Comunidad(32, "Vanessa Hernandez", 999));
        comunidadList.add(new Comunidad(33, "Marisol Jimenez", 23));
        comunidadList.add(new Comunidad(34, "Saul Cortez", 14));
        comunidadList.add(new Comunidad(35, "Tania Gonzales", 1));
        comunidadList.add(new Comunidad(36, "Iris Ayala", 2));
        comunidadList.add(new Comunidad(37, "Roberto Altamirano", 1));
        comunidadList.add(new Comunidad(38, "Alberto Rodriguez", 1));
        comunidadList.add(new Comunidad(39, "Vanessa Hernandez", 2));
        comunidadList.add(new Comunidad(40, "Marisol Jimenez", 1));

        listViewAmigos = (ListView) view.findViewById(R.id.listview_amigos);
        listAmigos = new AmigosAdapter(getActivity(), comunidadList ,1);
        listViewAmigos.setAdapter(listAmigos);


        listViewAmigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "myPos " + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("idContacto", "" + id);
                intent.putExtra("varAmigo", 1);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listViewAmigos.setNestedScrollingEnabled(true);

        }
        return view;

    }
}
