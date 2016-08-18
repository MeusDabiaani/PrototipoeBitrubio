package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 04/07/2016.
 * la clase muetra el fragmento fundacion dentro de la seccion Donacion de Servicios
 */

public class DonacionActivity extends AppCompatActivity {
    Toolbar toolbar;
    SessionManager session;
    FragmentTransaction FT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donaciones);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView textTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        textTitle.setText("Fundación");

        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putInt("mView", 1);
            FragmentDonacion fragment = new FragmentDonacion();
            FT = getSupportFragmentManager().beginTransaction();
            FT.add(R.id.rel_contenedor, fragment);
            fragment.setArguments(args);
            FT.commit();
        }

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
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

}
