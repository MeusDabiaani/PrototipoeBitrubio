package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 06/06/2016.
 * muestra el detalle de una meta creada
 */
public class MetaDetalle  extends AppCompatActivity{
    private ViewMetasPagerAdapter metasAdapter;
    private ViewPager mPager;
    Toolbar toolbar;
    @Bind(R.id.txt_siento)
    TextView txt_siento;

    @Bind(R.id.txt_porcentaje)
    TextView txt_porcentaje;

    @Bind(R.id.img_opciones)
    ImageView img_Opciones;

    FragmentTransaction FT;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_meta_fisia_dos);
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
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetaDetalle.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mPager = (ViewPager) findViewById(R.id.viewpagerMetaFisica);
        metasAdapter = new ViewMetasPagerAdapter(getSupportFragmentManager(),3);
        mPager.setAdapter(metasAdapter);

        img_Opciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent   = new Intent(MetaDetalle.this,MetaResultado.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}
