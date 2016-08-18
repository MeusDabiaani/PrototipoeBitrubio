package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.Bitrubian.ComentarioActivity;
import com.bitrubio.prototipoebitrubio.Entidades.Comunidad;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 25/02/2016.
 */
public class FragmentFamiliares extends Fragment {
    private ListView listViewAmigos;
    private ArrayList<Comunidad> comunidadList;
    private AmigosAdapter listAmigos;
    Typeface tf;

    @Bind(R.id.section_ami)
    TextView txtSectionAmigos;

    @Bind(R.id.edit_comentario)
    EditText edit_comentrio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_familiares, parentViewGroup, false);
        ButterKnife.bind(this, rootView);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
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
        listViewAmigo(rootView);

        return rootView;
    }

    /**
     *
      * @param view
     * arma la lista de amigos
     */
    public void listViewAmigo(View view ){
        listViewAmigos = (ListView) view.findViewById(R.id.list_amigos);
        listAmigos = new AmigosAdapter(getActivity(), comunidadList,0);
        listViewAmigos.setAdapter(listAmigos);

        edit_comentrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ComentarioActivity.class);
                startActivity(intent);
            }
        });


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
    }
    private TextView setTypeAndSize(TextView txt) {
        txt.setTextSize(17);
        txt.setTypeface(tf);
        return txt;
    }

}
