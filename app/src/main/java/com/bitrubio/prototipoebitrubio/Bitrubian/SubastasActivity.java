package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 04/07/2016.
 * Vsita principal de la seccion de subastas
 */
public class SubastasActivity extends AppCompatActivity{
    Toolbar toolbar;
    String TAG = getClass().getName();
    private ViewPager pager;
    private AdapterPagerSubastas mAdapter;
    private TabLayout mTabs;
    Typeface tf ;
    CharSequence tittleTabs[] = {"Registro" ,"Mis Subastas"};
    int numTabs = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subastas);
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
        textTitle.setText("Subastas");
        textTitle.setTextSize(16);

        mAdapter = new AdapterPagerSubastas(getSupportFragmentManager(),tittleTabs,numTabs);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(0);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(pager);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        mTabs.setTabTextColors(getResources().getColorStateList(R.color.colorAccent));

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
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}
