package com.bitrubio.prototipoebitrubio.Metas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.bitrubio.prototipoebitrubio.Tab_familiares;

/**
 * Created by Orion on 08/08/2016.
 */
public class AdapterArmaEquipo extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public AdapterArmaEquipo(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb ) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new Tab_Bitrubians();
            case 1: //  TAB posicion 2 para todas las pantallas
                return new Tab_facebook();
            default:
                return new Tab_familiares();
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position].toString();
    }
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
