package com.bitrubio.prototipoebitrubio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Orion on 09/06/2016.
 */
public class AdapterPagerPromociones extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    public AdapterPagerPromociones(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public AdapterPagerPromociones(FragmentManager fm, CharSequence[] titles_tab, int num_tabs) {
        super(fm);
        this.Titles = titles_tab;
        this.NumbOfTabs = num_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tab_promoHistorico();
            case 1:
                return new Tab_promoPromociones();
            case 2:
                return new Tab_promoMicarrrito();
            default:
                return null;
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
