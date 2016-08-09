package com.bitrubio.prototipoebitrubio.Metas;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.AdapterPagerDatos;
import com.bitrubio.prototipoebitrubio.R;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Orion on 08/08/2016.
 */
public class ArmarEquipo extends Fragment {

    private ViewPager pager;
    private AdapterArmaEquipo adapter;
    private TabLayout tabs;
    private Typeface tf;
    private int numTabs;
    CharSequence[] titleBars;

    public static ArmarEquipo newInstance(Bundle arguments) {
        ArmarEquipo fragment = new ArmarEquipo();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public ArmarEquipo() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_armar_equipo, container, false);
        ButterKnife.bind(this, view);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenir-light.ttf");
        Bundle args = getArguments();

        if (args != null) {
            int tipoDatos = args.getInt("tipo",0);
            if (tipoDatos == 1){
                titleBars = new String[]{"Contactos"} ;
                numTabs = 1;
            }else{
                titleBars = new String[]{"Contactos", "Facebook"} ;
                numTabs = 2;
            }
        }
        adapter = new AdapterArmaEquipo(getFragmentManager(), titleBars, numTabs);
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        // Assiging the Sliding Tab Layout View
        tabs = (TabLayout) view.findViewById(R.id.tabs_armaEquipo);

        tabs.setupWithViewPager(pager);
        tabs.setTabTextColors(getResources().getColor(R.color.textColorPrimary), getResources().getColor(R.color.letraVerde1));
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.letraVerde1));

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

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
