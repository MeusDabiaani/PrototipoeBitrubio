package com.bitrubio.prototipoebitrubio;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bitrubio.prototipoebitrubio.Entidades.Productos;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;
/**
 * Created by Orion on 09/06/2016.
 */
public class Tab_promoMicarrrito extends Fragment {

    private RecyclerView mRecyclerView;
    private CarritoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Productos> productosList;
    private LinearLayout lnr_codigo;
    Typeface tf;

    @Bind(R.id.btn_pagar)
    Button btn_pagar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_promo_carrito, container, false);
        ButterKnife.bind(this,view);
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/avenir-light.ttf");
        recyclerView(view);
        lnr_codigo = (LinearLayout) view.findViewById(R.id.lnr_codigo);
        lnr_codigo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showPop();
            }
        });

        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),PagarCompra.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void recyclerView(View view){
        Drawable drw_prod1 = getResources().getDrawable(R.drawable.promo_1);
        Drawable drw_prod2 = getResources().getDrawable(R.drawable.promo_2);
        Drawable drw_prod3 = getResources().getDrawable(R.drawable.promo_3);
        Drawable drw_prod4 = getResources().getDrawable(R.drawable.promo_4);

        productosList = new ArrayList<>();
        productosList.add(new Productos(1,"Descuento","Precios especiales en estudios para la mujer",drw_prod1,1.0f,1100.0f,50.0f));
        productosList.add(new Productos(2,"Descuento","Paga tu anualidad con 6 meses sin intereses y recibe un descuento del 10%",drw_prod2,2.0f,10000.00f,10.0f));
        productosList.add(new Productos(3,"Descuento","Primera consulta gratis y las siguientes 3  al 50%",drw_prod3,1.0f,600.0f,100.0f));
        productosList.add(new Productos(4,"Descuento","Precios especiales en estudios para la mujer",drw_prod4,1.0f,1100.0f,50.0f));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_compras);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CarritoAdapter(productosList, getActivity());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void showPop() {

        FragmentManager manager = getFragmentManager();
        DialogCodigo dialog = new DialogCodigo();
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
        dialog.show(manager, "titulo");
    }
}
