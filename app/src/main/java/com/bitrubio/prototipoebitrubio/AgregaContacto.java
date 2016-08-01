package com.bitrubio.prototipoebitrubio;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.R;

import java.lang.reflect.Type;

import butterknife.ButterKnife;

/**
 * Created by Orion on 11/07/2016.
 */
public class AgregaContacto extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    SessionManager session;
    Toolbar toolbar;
    Typeface tf;
    AdapterTabs mAdapter;
    ViewPager mPager;
    TabLayout mTabs;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_contactos);
        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        toolBar();
        CharSequence[] titles = {"Buscar contacto", "Registrar"};
        int numTabs = 2;

        mAdapter = new AdapterTabs(getSupportFragmentManager(), titles, numTabs);

        mPager = (ViewPager) findViewById(R.id.pager_datos);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);

        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mPager);
        mTabs.setTabTextColors(getResources().getColor(R.color.textColorPrimary),getResources().getColor(R.color.colorAccent));
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        ViewGroup vg = (ViewGroup) mTabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf, Typeface.NORMAL);
                    ((TextView) tabViewChild).setTextSize(14);
                    ((TextView) tabViewChild).setAllCaps(false);
                }
            }
        }


    }
    @SuppressLint("NewApi")
    public void toolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable( R.drawable.arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView textTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        textTitle.setText("Agregar persona");
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
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
  /*      if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
