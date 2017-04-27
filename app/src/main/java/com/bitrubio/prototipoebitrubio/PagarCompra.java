package com.bitrubio.prototipoebitrubio;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Orion on 24/06/2016.
 * Muetsras la vista para selecionar la tarjeta que se usara para pagar
 */
// TODO falta backend
public class PagarCompra extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TarjetasAdapter mAdaper;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Tarjetas> tarjetaList;
    Toolbar toolbar;
    Typeface tf;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pagar_carrito);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView textTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        textTitle.setText("Comprar");
        textTitle.setTypeface(tf);
        textTitle.setTextSize(16);

        Drawable fondo1 = getResources().getDrawable(R.drawable.tarjeta_azul);
        Drawable fondo2 = getResources().getDrawable(R.drawable.tarjeta_anaranjada);
        Drawable fondo3 = getResources().getDrawable(R.drawable.tarjeta_negra);

        tarjetaList = new ArrayList<Tarjetas>();
        tarjetaList.add(new Tarjetas(1, "Personal", "****", "****", "****", "1234", "09/2017", fondo1));
        tarjetaList.add(new Tarjetas(2, "Trabajo", "****", "****", "****", "2235", "01/2019", fondo2));
        tarjetaList.add(new Tarjetas(3, "Trabajo", "****", "****", "****", "7845", "01/2025", fondo3));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_hrz_tarjetas);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdaper = new TarjetasAdapter(tarjetaList, this);
        mAdaper.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdaper);

        mAdaper.SetOnItemClickListener(new TarjetasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("TG", "id" + position);


                showEditDialog();
            }
        });

    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogCompraExitosa editNameDialogFragment = new DialogCompraExitosa();
        editNameDialogFragment.show(fm, "dialog compra exotosa");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
        //noinspection SimplifiableIfStatement
   /*     if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);*/
    }
}
