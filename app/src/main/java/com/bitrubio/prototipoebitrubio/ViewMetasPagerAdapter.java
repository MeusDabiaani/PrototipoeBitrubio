package com.bitrubio.prototipoebitrubio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Orion on 01/06/2016.
 */
public class ViewMetasPagerAdapter extends FragmentPagerAdapter {
    int numPages;
    public ViewMetasPagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.numPages = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new FragmentDetalle_Fisico();
            case 1: //  TAB posicion 2 para todas las pantallas

                return new FragmentDetall_Agradecer();
            case 2:
                return new FragmentDetalle_Grafico();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numPages;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
