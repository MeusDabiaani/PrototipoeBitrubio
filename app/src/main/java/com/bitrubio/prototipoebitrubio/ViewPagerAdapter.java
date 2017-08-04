package com.bitrubio.prototipoebitrubio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mario on 02/02/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Drawable myDrawable;
    Context context;
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, int mNumbOfTabsumb,Context context) {
        super(fm);
        //this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:  //  TAB posicion 1 para todas las pantallas
                return new Tab_familiares();
            case 1: //  TAB posicion 2 para todas las pantallas
                return new Tab_expertos();
            default:
                return new Tab_familiares();
        }

    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                SpannableStringBuilder sb = new SpannableStringBuilder(" ");
                Drawable drawable = context.getResources().getDrawable( R.drawable.ic_tab_familiares_on );
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth()-65, drawable.getIntrinsicHeight()-10);
                ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return sb;
            case 1:
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
                Drawable draw = context.getResources().getDrawable( R.drawable.ic_tab_expertos_off );
                draw.setBounds(0, 0, draw.getIntrinsicWidth()-65, draw.getIntrinsicHeight()-10);
                ImageSpan imgspan = new ImageSpan(draw, ImageSpan.ALIGN_BASELINE);
                spannableStringBuilder.setSpan(imgspan , 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableStringBuilder;
            default:
                return null;
        }

    }
// This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}

