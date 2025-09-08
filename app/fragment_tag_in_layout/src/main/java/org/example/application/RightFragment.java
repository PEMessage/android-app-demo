package org.example.application;


import android.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.os.Bundle;

public class RightFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fragment, container, false);
        return view;
    }
}
