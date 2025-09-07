package org.example.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    private static final String ARG_ITEM = "item";

    public static ContentFragment newInstance(String item) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setTextSize(20);
        textView.setPadding(50, 50, 50, 50);
        
        if (getArguments() != null) {
            String item = getArguments().getString(ARG_ITEM);
            textView.setText("Content for: " + item);
        } else {
            textView.setText("Select an item");
        }
        
        return textView;
    }
}
