package com.bitrubio.prototipoebitrubio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Orion on 11/07/2016.
 */
public class AdapterTabs  extends FragmentStatePagerAdapter{
    CharSequence mTitulos[];
    int numTabs;

    public AdapterTabs(FragmentManager fm, CharSequence[] mTitulos, int numTabs) {
        super(fm);
        this.mTitulos = mTitulos;
        this.numTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new Tab_familiares();
            case 1: //  TAB posicion 2 para todas las pantallas
                return new Tab_registrar();
            default:
                return new Tab_familiares();
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitulos[position].toString();
    }
    @Override
    public int getCount() {
        return numTabs;
    }
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
