package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.MainActivity;
import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 02/03/2016.
 * muestra los tipos de posteo que pueden existir
 *
 */
public class ComentarioActivity extends AppCompatActivity {
    Typeface tf;
    LinearLayout lnr_header_comentario;
    Toolbar toolbar;

    @Bind(R.id.txt_eleccion)
    TextView txtEleccion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cometario);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.publicarCon));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int opcionSelecionada = bundle.getInt("tipocomentario");

            switch (opcionSelecionada) {
                case 1:
                    txtEleccion.setText("Privado");
                    break;
                case 2:
                    txtEleccion.setText("Mi porra");
                    break;
                case 3:
                    txtEleccion.setText("Tu Comunidad");
                    break;
                case 0:
                    txtEleccion.setText("Publico");
                    break;
            }
        }
        lnr_header_comentario = (LinearLayout) findViewById(R.id.header_comentario);
        lnr_header_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOpciones = new Intent(ComentarioActivity.this, ComentarioOpciones.class);
                intentOpciones.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentOpciones);
            }
        });
    }
    public void onBackPressed() {
        Intent myIntent = new Intent(ComentarioActivity.this, MainActivity.class);//goto whichever activity you want by clearing top of stack.
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);// clear top of stack
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// clear top of stack
        startActivity(myIntent);
        finish();
        return;
    }


}
