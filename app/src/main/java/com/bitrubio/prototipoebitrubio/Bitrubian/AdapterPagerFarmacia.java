package com.bitrubio.prototipoebitrubio.Bitrubian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bitrubio.prototipoebitrubio.Tab_datos_generales;
import com.bitrubio.prototipoebitrubio.Tab_datos_medicos;
import com.bitrubio.prototipoebitrubio.Tab_datos_servicios;

/**
 * Created by Orion on 27/06/2016.
 * adaptadpr slide seccion farmacia
 */
public class AdapterPagerFarmacia extends FragmentStatePagerAdapter{
    CharSequence Titles[];
    int numOfTabs;

    public AdapterPagerFarmacia(FragmentManager fm, CharSequence[] titles, int numOfTabs) {
        super(fm);
        Titles = titles;
        this.numOfTabs = numOfTabs;
    }

    public AdapterPagerFarmacia(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new Tab_mis_recetas();
            case 1: //  TAB posicion 2 para todas las pantallas
                return new Tab_ofertas();
            case 2: //  TAB posicion 2 para todas las pantallas
                return new Tab_miCarrito();
            default:
                return new Tab_mis_recetas();
        }
    }
    public CharSequence getPageTitle(int position) {
        return Titles[position].toString();
    }
    @Override
    public int getCount() {
        return numOfTabs;
    }
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
