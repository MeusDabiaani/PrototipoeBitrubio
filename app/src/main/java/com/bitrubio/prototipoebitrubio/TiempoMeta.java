package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.ClasesExtendidas.HorizontalPicker;
import com.bitrubio.prototipoebitrubio.ClasesExtendidas.SegmentedButton;
import com.google.android.gms.vision.text.Line;

import java.lang.reflect.Field;

/**
 * Created by Orion on 25/07/2016.
 */
public class TiempoMeta extends AppCompatActivity implements HorizontalPicker.OnItemSelected , HorizontalPicker.OnItemClicked {
    Toolbar toolbar;
    private SlidingDrawer drawer;
    String TAG = getClass().getName();
    Typeface tf;
    int varSeleccion;
    ImageButton imgButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.temporal_fisicos);
        setContentView(R.layout.activity_form_tiempo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.metasFisicas));
        mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.letraVerde1));

        final String[] array = {"1", "2","3","4","5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        tf = Typeface.createFromAsset(this.getAssets(), "fonts/avenir-light.ttf");
        imgButton = (ImageButton) findViewById(R.id.btn_aceptar);

        HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.np);

        picker.setValues(array);

        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);

        SegmentedButton buttons = (SegmentedButton)findViewById(R.id.segmented);
        buttons.clearButtons();
        buttons.addButtons(
                getString(R.string.dia),
                getString(R.string.semana));


        // First button is selected
        buttons.setPushedButtonIndex(0);

        // Some example click handlers. Note the click won't get executed
        // if the segmented button is already selected (dark blue)
        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {
                if (index == 0) {
                    Toast.makeText(TiempoMeta.this, "dia", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TiempoMeta.this, "semana", Toast.LENGTH_SHORT).show();
                }
            }
        });


        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Log.e(TAG,"seleccion "+ varSeleccion );
            }
        });

    }
    @Override
    public void onItemClicked(int index) {
        varSeleccion = index;
    }

    @Override
    public void onItemSelected(int index) {
        varSeleccion = index ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.VISIBLE);
            //    addItem();
            Log.e(TAG,"click");
        }
        return super.onOptionsItemSelected(item);
    }


}
