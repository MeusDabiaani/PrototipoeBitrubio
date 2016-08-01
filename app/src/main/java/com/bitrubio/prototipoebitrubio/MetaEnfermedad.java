package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;

/**
 * Created by Orion on 29/04/2016.
 */
public class MetaEnfermedad extends AppCompatActivity{
    private String TAG = getClass().getName();
    Toolbar toolbar;
    SessionManager session;
    Typeface tf;
    FragmentTransaction FT;
    TextView txt_titulo;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas_fisicas);
        tf = Typeface.createFromAsset(this.getAssets(), "fonts/avenir-light.ttf");
        session = new SessionManager(getApplicationContext());
        //cambiamo el titulo en la toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView mTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        mTitle.setText(getResources().getString(R.string.metasEnfermedad));
        mTitle.setBackgroundColor(getResources().getColor(R.color.solidoRojo));
        mTitle.setTextSize(18);
        mTitle.setTypeface(tf);
        // Cambiamos el color de la status Bar para la meta
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.solidoRojo));
        Bundle args = new Bundle();
        Fragment fragmentListMetas = new FragmentListMetas();
        FT = getSupportFragmentManager().beginTransaction();
        FT.add(R.id.fragment_tipoMetas, fragmentListMetas);
        args.putInt("tipometas", 3);
        fragmentListMetas.setArguments(args);
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
        if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);

        }
        return super.onOptionsItemSelected(item);
    }
}
