package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Bitrubian.AlertaActvity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ComentarioActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ComunidadActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.ExpertosActivity;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.Slidetabs.SlidingTabLayout;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Orion on 29/02/2016.
 * Muestras los tabs con las seciones:
 * completar el perfil medico del ususario
 * lista de consulta medicas
 * lista de parametros para parametros de busqueda de expertos o medicos
 */
public class CompletaPerfilActivity extends AppCompatActivity{
    private ViewPager pager;
    private AdapterPagerDatos adapter;
    private TabLayout tabs;
    Toolbar toolbar;
    Typeface tf;
    SessionManager session;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_completa_datos);
        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        toolbar.setNavigationIcon(getResources().getDrawable( R.drawable.arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        TextView textTitle = (TextView) toolbar.findViewById(R.id.txt_titleToolbar);
        textTitle.setText("Perfil");
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));


        CharSequence Tittle_tab[]={"Perfil general","Historial clínico","Servicio"};

        int NumTabs = 3;
        adapter =  new AdapterPagerDatos(getSupportFragmentManager(),Tittle_tab,NumTabs);
        // Assigning ViewPager View and setting the adapter

        pager = (ViewPager) findViewById(R.id.pager_datos);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        // Assiging the Sliding Tab Layout View
        tabs = (TabLayout) findViewById(R.id.tabs_completa_datos);

        tabs.setupWithViewPager(pager);
        tabs.setTabTextColors(getResources().getColor(R.color.textColorPrimary),getResources().getColor(R.color.colorAccent));
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        // set typografia en el texto de los tabs
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

        return super.onOptionsItemSelected(item);
    }


}
