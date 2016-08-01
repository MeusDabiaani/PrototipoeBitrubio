package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 06/06/2016.
 */
public class MetaResultado extends AppCompatActivity {
    Toolbar toolbar;
    @Bind(R.id.txt_peso_actual)
    TextView _input_pesoActual;

    @Bind(R.id.edit_objetivo)
    TextView _input_objetivo;

    @Bind(R.id.edit_tiempo)
    TextView _input_tiempoMeta;

    @Bind(R.id.edit_retarAmigos)
    TextView _input_retaAmigos;

    @Bind(R.id.edit_armarEquipo)
    TextView _input_armaEquipo;

    @Bind(R.id.edit_privacidad)
    TextView _input_privacidad;
    Typeface tf;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_meta);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        //setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.letraVerde1));

        toolbar.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        //setSupportActionBar(toolbar);
       /* toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetaResultado.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        _input_pesoActual.setTypeface(tf);
        _input_objetivo.setTypeface(tf);
        _input_tiempoMeta.setTypeface(tf);
        _input_retaAmigos.setTypeface(tf);
        _input_armaEquipo.setTypeface(tf);
        _input_privacidad.setTypeface(tf);

    }
}
