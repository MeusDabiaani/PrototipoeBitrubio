package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitrubio.prototipoebitrubio.R;

/**
 * Created by Orion on 25/02/2016.
 */
public class FragmentDependientes extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dependientes, parentViewGroup, false);
        return rootView;
    }
}
