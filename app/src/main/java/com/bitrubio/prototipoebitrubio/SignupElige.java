package com.bitrubio.prototipoebitrubio;


import android.annotation.TargetApi;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Slidetabs.SlidingTabLayout;

import org.w3c.dom.Text;

/**
 * Created by Mario on 02/02/2016.
 * panatalla eleije que quieres hacer primero
 */

public class SignupElige extends AppCompatActivity {

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private TextView msg,txtBold,txtPart,txtMedium,txtLow;
    private   int[] tabIcons = {R.drawable.ic_usuario,R.drawable.ic_action_eres_experto};

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_tabs);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

      //  CharSequence Tittle_tab[]={"Familiares y amigos"," Expertos en Salud"};

        msg = (TextView) findViewById(R.id.txt_elijeAmigos);
        msg.setText("Crea tu ");

        txtBold = (TextView) findViewById(R.id.txt_bitrubio);
        txtBold.setText("red de apoyo ");

        txtPart = (TextView) findViewById(R.id.txt_part);
        txtPart.setText("agregando");

        txtMedium = (TextView) findViewById(R.id.txt_medium);
        txtMedium.setText("3 contactos de emergencia, por");

        txtLow = (TextView) findViewById(R.id.txt_low);
        txtLow.setText("ejemplo algún familiar o vecino");

        int NumTabs = 2;
        // Initializing Toolbar and setting it as the actionbar
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),NumTabs,this);
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        pager.setCurrentItem(0);
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {


            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);    //define any color in xml resources and set it here, I have used white
            }

            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.white);
            }

        });
    }


}
