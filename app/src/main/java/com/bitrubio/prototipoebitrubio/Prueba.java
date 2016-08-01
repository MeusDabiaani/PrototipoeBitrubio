package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;

/**
 * Created by Orion on 25/07/2016.
 */
public class Prueba extends AppCompatActivity {
    Toolbar toolbar;
    private SlidingDrawer drawer;
    private Button handle, clickMe;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporal_perfil);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        context = this.getApplicationContext();

        handle = (Button) findViewById(R.id.handle);

        clickMe = (Button) findViewById(R.id.click);

        drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);





    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(this,MainActivity.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

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
        if (id == R.id.action_helpBeety) {

            if(drawer.isOpened()){
                drawer.close();

            }else{
                drawer.open();
                findViewById(R.id.action_helpBeety).setVisibility(View.GONE);

                clickMe.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.action_helpBeety).setVisibility(View.VISIBLE);
                       drawer.close();
                    }
                });

            }
        }
        return super.onOptionsItemSelected(item);
    }
}
