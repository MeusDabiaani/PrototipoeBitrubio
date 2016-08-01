package com.bitrubio.prototipoebitrubio;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 28/02/2016.
 */
@SuppressLint("ValidFragment")
class FragDialogSiento extends DialogFragment {

    @Bind(R.id.txt_estadoFisico)
    TextView txtEstadoFisico;

    @Bind(R.id.txt_estadoEmocional)
    TextView txtEstadoEmocional;


    @Bind(R.id.img_mas_fisicamente)
    ImageView imgMasFisicamente;

    @Bind(R.id.img_menos_fisicamente)
    ImageView imgMenosFisciamente;
    @Bind(R.id.img_menosEmocinal)
    ImageView imgMenosEmocional;

    @Bind(R.id.img_masEmocinal)
    ImageView imgMasEmocional;

    @Bind(R.id.btn_aceptar)
    ImageButton imgAceptar;

    private SeekBar seekBarFisico, seekBarEmocional;
    Typeface tf;
    int progresoFisco = 1;
    int progresoEmocional = 1;

    public FragDialogSiento() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public interface ComoMesientoListener {
        void setComomesiento(int comoMeSiento);
    }

    public interface ComoMeSientoEmocianlListener {
        void setComomeSientoEmocional(int emocional);
    }

    public static FragDialogSiento newInstance(String title) {
        FragDialogSiento frag = new FragDialogSiento();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_camara, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");

        seekBarFisico = (SeekBar) view.findViewById(R.id.seek_fisico);
        seekBarFisico.setProgressDrawable(getResources().getDrawable(R.drawable.progres_bar));
        seekBarFisico.setProgress(progresoFisco);
        txtEstadoFisico.setText("normal");

        seekBarEmocional = (SeekBar) view.findViewById(R.id.seek_emocional);
        seekBarEmocional.setProgressDrawable(getResources().getDrawable(R.drawable.progres_bar));
        seekBarEmocional.setProgress(progresoEmocional);
        txtEstadoEmocional.setText("normal");

        imgMenosFisciamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekBarFisico.setProgress(progresoFisco--);
            }
        });
        imgMasFisicamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekBarFisico.setProgress(progresoFisco++);
            }
        });

        imgMenosEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarEmocional.setProgress(progresoEmocional--);
            }
        });
        imgMasEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarEmocional.setProgress(progresoEmocional++);
            }
        });
        seekBarFisico.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresoFisco = progress;
                txtEstadoFisico.setText("" + progress);
                if (progress == 0) {
                    txtEstadoFisico.setText("Mal");

                } else if (progress == 1) {
                    txtEstadoFisico.setText("Normal");

                } else if (progress == 2) {
                    txtEstadoFisico.setText("Bien");

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        seekBarEmocional.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresoEmocional = progress;
                txtEstadoEmocional.setText("" + progress);
                if (progress == 0) {
                    txtEstadoEmocional.setText("Mal");

                } else if (progress == 1) {
                    txtEstadoEmocional.setText("Normal");

                } else if (progress == 2) {
                    txtEstadoEmocional.setText("Bien");

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imgAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComoMesientoListener comoMesientoListener = (ComoMesientoListener) getActivity();
                comoMesientoListener.setComomesiento(progresoFisco);

                ComoMeSientoEmocianlListener comoMeSientoEmocianlListener = (ComoMeSientoEmocianlListener) getActivity();
                comoMeSientoEmocianlListener.setComomeSientoEmocional(progresoEmocional);
                getDialog().dismiss();
            }
        });


    }


}
