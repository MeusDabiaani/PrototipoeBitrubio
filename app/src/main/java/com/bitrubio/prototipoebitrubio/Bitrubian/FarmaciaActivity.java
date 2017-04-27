package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.AdapterPagerDatos;
import com.bitrubio.prototipoebitrubio.R;

import butterknife.ButterKnife;

/**
 * Created by Orion on 27/06/2016.
 * Muestra la seccion de farmacia con sus tabs
 */
public class FarmaciaActivity extends AppCompatActivity {
    private ViewPager pager;
    private AdapterPagerFarmacia adapter;
    private TabLayout tabs;
    Toolbar toolbar;
    Typeface tf;
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia_home);

        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
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
        textTitle.setText("Farmacia");
        textTitle.setTypeface(tf);
        textTitle.setTextSize(14);

        CharSequence Tittle_tab[]={"Mis recetas","Ofertas","Mi Carrito"};

        int NumTabs = 3;
        adapter =  new AdapterPagerFarmacia(getSupportFragmentManager(),Tittle_tab,NumTabs);
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager_farmacia);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        // Assiging the Sliding Tab Layout View
        tabs = (TabLayout) findViewById(R.id.tab_farmacia);

        tabs.setupWithViewPager(pager);
        tabs.setTabTextColors(getResources().getColor(R.color.textColorPrimary),getResources().getColor(R.color.colorAccent));
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));

        // setea la tipogrfia para poder hacer pornerla en minusculas
        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
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
}
