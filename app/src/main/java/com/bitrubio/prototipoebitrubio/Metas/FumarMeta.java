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
import com.bitrubio.prototipoebitrubio.FragmentMetaSelecionada;
import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 03/08/2016.
 */
public class FumarMeta extends Fragment implements HorizontalPicker.OnItemClicked , HorizontalPicker.OnItemSelected{
    @Bind(R.id.btn_aceptar)
    ImageButton imgButton ;


    HorizontalPicker picker ;
    SegmentedButton buttons ;

    Typeface tf;
    FragmentTransaction FT;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_fumar_menos,container,false);
        ButterKnife.bind(this,view);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        picker = (HorizontalPicker) view.findViewById(R.id.np);
        buttons = (SegmentedButton) view.findViewById(R.id.segmented);

        final String[] array = {"1", "2","3","4","5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};

        picker.setValues(array);
        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);


        buttons.clearButtons();
        buttons.addButtons(
                getString(R.string.pieza),
                getString(R.string.cajetilla));


        // First button is selected
        buttons.setPushedButtonIndex(0);

        // Some example click handlers. Note the click won't get executed
        // if the segmented button is already selected (dark blue)
        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {
                if (index == 0) {
                    Toast.makeText(getActivity(), "piezas", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "cajetilla", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        return  view;

    }

    @Override
    public void onItemClicked(int index) {

    }

    @Override
    public void onItemSelected(int index) {

    }
}

