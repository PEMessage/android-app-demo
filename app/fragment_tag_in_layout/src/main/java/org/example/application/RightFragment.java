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
        // If attachToRoot is false, the parent will become object to provide LayoutParams, or null, if we dont need it
        // See: https://developer.android.com/reference/android/view/LayoutInflater#inflate(int,%20android.view.ViewGroup,%20boolean)
        View view = inflater.inflate(R.layout.right_fragment, null, false);
        return view;
    }
}
