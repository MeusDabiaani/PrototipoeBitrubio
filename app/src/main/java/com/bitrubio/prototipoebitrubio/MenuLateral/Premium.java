package com.bitrubio.prototipoebitrubio.MenuLateral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.AgregaContacto;
import com.bitrubio.prototipoebitrubio.R;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by Orion on 08/07/2016.
 */
public class Premium extends AppCompatActivity {
    Toolbar toolbar;

    @Bind(R.id.button_agregar_mas)
    Button btn_agregar_mas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView txtTtitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        txtTtitle.setText("Planes");


        btn_agregar_mas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Premium.this, AgregaContacto.class);
                startActivity(intent);
            }
        });

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
