package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.FragmentConsultas;
import com.bitrubio.prototipoebitrubio.R;

import butterknife.ButterKnife;

/**
 * Created by Orion on 05/07/2016.
 */
public class DetalleSubastas  extends AppCompatActivity{

    Toolbar toolbar;
    SessionManager session;
    FragmentTransaction FT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_subastas);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText("Detalle");
        mTitle.setTextSize(16);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        FragmentSubastas fragment = new FragmentSubastas();
        FT = getSupportFragmentManager().beginTransaction();
        FT.add(R.id.rel_contenedor, fragment);
        FT.commit();

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
