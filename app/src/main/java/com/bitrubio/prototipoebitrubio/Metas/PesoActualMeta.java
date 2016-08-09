package com.bitrubio.prototipoebitrubio.Metas;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.ClasesExtendidas.SegmentedButton;
import com.bitrubio.prototipoebitrubio.FragmentMetaPeso;
import com.bitrubio.prototipoebitrubio.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Orion on 03/08/2016.
 */
public class PesoActualMeta extends Fragment implements HorizontalPicker.OnItemClicked, HorizontalPicker.OnItemSelected {
    String TAG = getClass().getName();
    Typeface tf;
    int varSeleccion;
    ImageButton imgButton;
    FragmentTransaction FT;
    String[] array;

    int var1 , var2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_peso_actual, container, false);

        array = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
                "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                "51", "52", "53", "54", "55", "56","57","58","59","60","61","62","63","64","65","66","67","68","69","70"};

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        imgButton = (ImageButton) v.findViewById(R.id.btn_aceptar);

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.np1);
        picker.setValues(array);
        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(new HorizontalPicker.OnItemSelected() {
            @Override
            public void onItemSelected(int index) {
                var1 = index ;
            }
        });


        HorizontalPicker picker2 = (HorizontalPicker) v.findViewById(R.id.np2);
        picker2.setValues(array);
        picker2.setOnItemClickedListener(this);
        picker2.setOnItemSelectedListener(new HorizontalPicker.OnItemSelected() {
            @Override
            public void onItemSelected(int index) {
                var2 = index;
            }
        });

        SegmentedButton buttons = (SegmentedButton) v.findViewById(R.id.segmented);
        buttons.clearButtons();
        buttons.addButtons(
                getString(R.string.kilos),
                getString(R.string.libras));
        // First button is selected
        buttons.setPushedButtonIndex(0);
        // Some example click handlers. Note the click won't get executed
        // if the segmented button is already selected (dark blue)
        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {
                if (index == 0) {
                    Toast.makeText(getActivity(), "kilos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "libras", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  // TODO: 02/08/2016 regresamos los valores para entrar a la alata dela meta peso 1,1

                Log.e(TAG,"var 1" + var1);
                Log.e(TAG,"var 2" + var2);
              /*  Bundle args = new Bundle();
                final Fragment fragment = new FragmentMetaPeso();
                FT = getFragmentManager().beginTransaction();
                FT.replace(R.id.fragment_tipoMetas, fragment);
                FT.addToBackStack(null);
                args.putInt("tipoMeta", 1);
                args.putInt("position", 1);
                fragment.setArguments(args);
                FT.commit();*/


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
