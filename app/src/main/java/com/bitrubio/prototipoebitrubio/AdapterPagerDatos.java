package com.bitrubio.prototipoebitrubio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Orion on 29/02/2016.
 */
public class AdapterPagerDatos extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public AdapterPagerDatos(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb ) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new Tab_datos_generales();
            case 1: //  TAB posicion 2 para todas las pantallas
                return new Tab_datos_medicos();
            case 2: //  TAB posicion 2 para todas las pantallas
                return new Tab_datos_servicios();
            default:
                return new Tab_datos_generales();
        }

    }
    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position].toString();
    }
    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    public int getItemPosition(Object object){
        return POSITION_NONE;
    }



}

