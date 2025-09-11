package org.example.application;
import android.util.Log;
import android.os.Bundle;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.example.application.framework.Item;
import org.example.application.framework.ItemAdapter;
import org.example.application.framework.ItemUtils;

@SuppressWarnings("deprecation")
public class ItemFragment extends Fragment {
    final static String TAG = "ItemFragment";
    final static String ARGS_ITEM_PATHS = "item_paths";
    final static String STACK_NAME = "item_stack";

    private Item item;
    private ItemAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            MainActivity main = (MainActivity)getActivity();
            this.item = ItemUtils.findItemByAbsPath(main.mRootItem,
                 getArguments().getStringArrayList(ARGS_ITEM_PATHS)
            );
        } else {
            assert false;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create ListView programmatically
        Context context = getActivity();
        ListView listView = new ListView(context);
        adapter = new ItemAdapter(context, item);
        
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                if (item.children.isEmpty()) {
                    Log.d(TAG, item.toString() + " do not have any child, skip step into it");
                    return;
                }
                ((MainActivity)getActivity()).navigateToItemFragment(item);

            }
        });
        return listView;
    }

}
