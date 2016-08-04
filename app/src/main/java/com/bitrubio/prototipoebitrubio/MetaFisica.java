package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.MenuInflater;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import android.util.Log;
import android.widget.LinearLayout;
/**
 * Created by Orion on 26/04/2016.
 */
public class MetaFisica extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    Toolbar toolbar;
    SessionManager session;
    Typeface tf;
    FragmentTransaction FT;
    FrameLayout  mContainerView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas_fisicas);
        Log.w(TAG,"Clase selecionada : " +TAG );
        tf = Typeface.createFromAsset(this.getAssets(), "fonts/avenir-light.ttf");
        session = new SessionManager(getApplicationContext());
        //cambiamo el titulo en la toolbar
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
        Log.e(TAG,"Lista Fisicas");

        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.metasFisicas));
        mTitle.setBackgroundColor(getResources().getColor(R.color.letraVerde1));
        mTitle.setTextSize(16);
        mTitle.setTypeface(tf);
        // Cambiamos el color de la status Bar para la meta
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.letraVerde1));


        mContainerView = (FrameLayout) findViewById(R.id.fragment_tipoMetas);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = getIntent().getExtras().getString("variable");
        }
        // METAS CREADAS
        if (value.equals("1")) {
            Intent intent = new Intent(getBaseContext(), MetaDetalle.class);
            startActivity(intent);
        } else {  // LISTA DE METAS
            Bundle args = new Bundle();
            Fragment fragmentListMEtas = new FragmentListMetas();
            FT = getSupportFragmentManager().beginTransaction();
            FT.add(R.id.fragment_tipoMetas, fragmentListMEtas);
            args.putInt("tipometas", 1);
            fragmentListMEtas.setArguments(args);
            FT.commit();
        }
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
