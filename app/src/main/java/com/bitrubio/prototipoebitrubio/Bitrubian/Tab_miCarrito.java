package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.Entidades.Medicamento;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;

/**
 * Created by Orion on 04/07/2016.
 * vuista del fragmento carrito para medicamentos
 */

public class Tab_miCarrito extends Fragment {

    private RecyclerView mRecyclerView;
    private CarritoMedicamentoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Medicamento> medicamentoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito_farmacia, container, false);

        int drawable[] = {R.drawable.promo_1, R.drawable.promo_2, R.drawable.promo_3};
        medicamentoList = new ArrayList<>();
        medicamentoList.add(new Medicamento(1, drawable[0], "Tafirol Flex 30 tabletas",
                "Parecetamol 300mg", "Asofarma", 2.0f,700.0f));

        medicamentoList.add(new Medicamento(2, drawable[1], "Ranitidina 20 tabletas",
                "Clorihidrato de ranitidina 150mg", "Genericos-Genvita", 1.0f,14.0f));

        medicamentoList.add(new Medicamento(3, drawable[2], "Diclofenaco Sodico 30 tabletas",
                "diclofencaco Sodico 50mg", "Genericos-Genvita", 1.0f,75.90f));


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_carrito_farmacia);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CarritoMedicamentoAdapter(medicamentoList, getContext());
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
