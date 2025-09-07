package org.example.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemListFragment extends Fragment {
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView listView = new ListView(getActivity());

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        listView.setAdapter(new ArrayAdapter<>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            items
        ));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (listener != null) {
                listener.onItemSelected(items[position]);
            }
        });

        return listView;
    }
}
