package com.bitrubio.prototipoebitrubio.Bitrubian;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 26/02/2016.
 * esta clase debe solicitar ayuda para otro no bitrubian
 */
public class AyudarOtroActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_otro);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setTitle("Ayuda a Otro");
        setSupportActionBar(toolbar);
    }
}
