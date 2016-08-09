package com.bitrubio.prototipoebitrubio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;

/**
 * Created by Orion on 03/08/2016.
 */
public class CafeMeta extends Fragment implements HorizontalPicker.OnItemClicked , HorizontalPicker.OnItemSelected {
    String TAG = getClass().getName();
    Typeface tf;
    int varSeleccion;
    ImageButton imgButton;
    FragmentTransaction FT;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beber_cafe,container,false);

        final String[] array = {"1", "2","3","4","5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        imgButton = (ImageButton) v.findViewById(R.id.btn_aceptar);

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.np);

        picker.setValues(array);

        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);




        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //  // TODO: 02/08/2016 regresamos los valores para entrar a la alata dela meta peso 1,1
                Bundle args = new Bundle();
                final Fragment fragment = new FragmentMetaSelecionada();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                args.putInt("tipoMeta",1);
                args.putInt("position", 1);
                fragment.setArguments(args);
                FT.commit();
            }
        });
        return v;
    }

    @Override
    public void onItemClicked(int index) {
        varSeleccion = index;
    }

    @Override
    public void onItemSelected(int index) {
        varSeleccion = index;

    }
}
