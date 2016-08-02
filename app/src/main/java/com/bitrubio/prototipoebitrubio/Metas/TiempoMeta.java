package com.bitrubio.prototipoebitrubio.Metas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.ClasesExtendidas.SegmentedButton;
import com.bitrubio.prototipoebitrubio.FragmentMetaPeso;
import com.bitrubio.prototipoebitrubio.R;
import com.google.android.gms.vision.text.Line;

import java.lang.reflect.Field;

/**
 * Created by Orion on 25/07/2016.
 */
public class TiempoMeta extends Fragment implements HorizontalPicker.OnItemSelected , HorizontalPicker.OnItemClicked {
    Toolbar toolbar;
    private SlidingDrawer drawer;
    String TAG = getClass().getName();
    Typeface tf;
    int varSeleccion;
    ImageButton imgButton;
    FragmentTransaction FT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.temporal_fisicos, container, false);


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
                getString(R.string.dia),
                getString(R.string.semana));


        // First button is selected
        buttons.setPushedButtonIndex(0);

        // Some example click handlers. Note the click won't get executed
        // if the segmented button is already selected (dark blue)
        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {
                if (index == 0) {
                    Toast.makeText(getActivity(), "dia", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "semana", Toast.LENGTH_SHORT).show();
                }
            }
        });


        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //  // TODO: 02/08/2016 regresamos los valores para entrar a la alata dela meta peso 1,1
                Bundle args = new Bundle();
                final Fragment fragment = new FragmentMetaPeso();
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
        varSeleccion = index ;
    }




}
