package com.bitrubio.prototipoebitrubio;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Orion on 26/02/2016.
 */
public class FragmentPatrocinadores extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_patrocinadores, parentViewGroup, false);

        return rootView;
    }
}
