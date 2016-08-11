package com.bitrubio.prototipoebitrubio.MenuLateral;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bitrubio.prototipoebitrubio.NuevaTarjeta;
import com.bitrubio.prototipoebitrubio.R;
import com.bitrubio.prototipoebitrubio.Tarjetas;
import com.bitrubio.prototipoebitrubio.TarjetasAdapter;

import java.util.ArrayList;
/**
 * Created by Orion on 17/06/2016.
 */
public class Tarjeta extends AppCompatActivity{
    RecyclerView mRecyclerView;
    TarjetasAdapter mAdaper;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Tarjetas> tarjetaList;
    Toolbar toolbar;
    ImageView imgAgregarTarjeta;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        imgAgregarTarjeta = (ImageView) findViewById(R.id.img_agregarTarjeta);
        imgAgregarTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentNvaTarjeta   = new Intent(Tarjeta.this,NuevaTarjeta.class);
                startActivity(intentNvaTarjeta);

            }
        });

        Drawable fondo1 = getResources().getDrawable(R.drawable.tarjeta_azul);
        Drawable fondo2 = getResources().getDrawable(R.drawable.tarjeta_anaranjada);
        Drawable fondo3 = getResources().getDrawable(R.drawable.tarjeta_negra);

        tarjetaList = new ArrayList<Tarjetas>();
        tarjetaList.add(new Tarjetas(1,"Personal","****","****","****","1234","09/2017",fondo1));
        tarjetaList.add(new Tarjetas(1,"Trabajo","****","****","****","2235","01/2019",fondo2));
        tarjetaList.add(new Tarjetas(1,"Trabajo","****","****","****","7845","01/2025",fondo3));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tarjetas);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdaper = new TarjetasAdapter(tarjetaList,this , fragmentManager);
        mAdaper.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdaper);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
