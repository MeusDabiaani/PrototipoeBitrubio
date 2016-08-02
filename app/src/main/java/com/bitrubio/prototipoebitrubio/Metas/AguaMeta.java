package com.bitrubio.prototipoebitrubio.Metas;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.ClasesExtendidas.SegmentedButton;
import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 02/08/2016.
 */
public class AguaMeta extends Fragment implements HorizontalPicker.OnItemSelected , HorizontalPicker.OnItemClicked{

    Typeface tf;
    int varSeleccion;
    ImageButton imgButton;
    FragmentTransaction FT;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_tomar_agua,container,false);

        final String[] array = {"1", "2","3","4","5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        imgButton = (ImageButton) v.findViewById(R.id.btn_aceptar);

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.np);

        picker.setValues(array);

        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);

        SegmentedButton buttons = (SegmentedButton)v.findViewById(R.id.segmented);
        buttons.clearButtons();
        buttons.addButtons(
                getString(R.string.vasos),
                getString(R.string.litros));


        // First button is selected
        buttons.setPushedButtonIndex(0);

        // Some example click handlers. Note the click won't get executed
        // if the segmented button is already selected (dark blue)
        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {
                if (index == 0) {
                    Toast.makeText(getActivity(), "vasos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "litros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    @Override
    public void onItemClicked(int index) {

    }

    @Override
    public void onItemSelected(int index) {

    }
}
