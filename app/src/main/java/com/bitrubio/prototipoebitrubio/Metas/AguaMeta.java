package com.bitrubio.prototipoebitrubio.Metas;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.ClasesExtendidas.SegmentedButton;
import com.bitrubio.prototipoebitrubio.R;

import java.awt.font.NumericShaper;
import java.lang.reflect.Field;

/**
 * Created by Orion on 02/08/2016.
 */
public class AguaMeta extends Fragment implements NumberPicker.OnValueChangeListener {

    Typeface tf;
    int varSeleccion;

    NumberPicker np ;
    ImageButton imgButton;
    FragmentTransaction FT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tomar_agua, container, false);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        imgButton = (ImageButton) v.findViewById(R.id.btn_aceptar);

         np = (NumberPicker) v.findViewById(R.id.np_agua);

        np.setMaxValue(100); // max value 100
        np.setMinValue(0);   // min value 0
        np.setValue(10);

        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        setDividerColor(np);
        setNumberPickerTextColor(np, getResources().getColor(R.color.textColorPrimary), tf, 75);

        SegmentedButton buttons = (SegmentedButton) v.findViewById(R.id.segmented);
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

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "picker" + String.valueOf(np.getValue()), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
    public boolean setNumberPickerTextColor(NumberPicker numberPicker, int color, Typeface tf, int textS) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);

            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    // edita el valor en la casilla selecionada
                    EditText editText = (EditText) child;
                    editText.setTextSize(textS);
                    editText.setTypeface(tf);
                    editText.setFocusable(false);
                    editText.setTextColor(color);
                    //edita el valor de los valores restantes
                    ((EditText) child).setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTypeface(tf);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTextSize(70);

                    setDividerColor(numberPicker);
                    numberPicker.invalidate();
                    return true;
                } catch (NoSuchFieldException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalAccessException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalArgumentException e) {
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

    private void setDividerColor(NumberPicker picker) {
        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    pf.set(picker,getActivity().getResources().getColor(R.color.transparenteBlanco));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}



