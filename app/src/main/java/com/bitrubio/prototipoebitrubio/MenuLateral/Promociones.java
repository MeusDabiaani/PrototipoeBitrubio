package com.bitrubio.prototipoebitrubio.MenuLateral;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bitrubio.prototipoebitrubio.AdapterPagerPromociones;
import com.bitrubio.prototipoebitrubio.Bitrubian.SessionManager;
import com.bitrubio.prototipoebitrubio.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import android.graphics.drawable.ColorDrawable;
/**
 * Created by Orion on 09/06/2016.
 */
public class Promociones extends AppCompatActivity {
    Toolbar toolbar ;

    String TAG = getClass().getName();
    private ViewPager pager;
    private AdapterPagerPromociones adapter;
    private TabLayout tabs;
    Typeface tf;
    SessionManager session;
    CharSequence Titles_tab[]={"Histórico","Promociones","Mi carrito"};
    int num_tabs = 3 ;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        ButterKnife.bind(this);
        tf = Typeface.createFromAsset(getAssets(), "fonts/avenir-light.ttf");
        //toolbar
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
        textTitle.setText("Promociones");
        textTitle.setTypeface(tf);
        textTitle.setTextSize(16);
        // set status bar color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        adapter = new AdapterPagerPromociones(getSupportFragmentManager(),Titles_tab,num_tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabs.setTabTextColors(getResources().getColorStateList(R.color.textColorPrimary));
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
   /*     if (id == R.id.action_helpBeety) {
            findViewById(R.id.action_helpBeety).setVisibility(View.GONE);

        }*/
        return super.onOptionsItemSelected(item);
    }





}
