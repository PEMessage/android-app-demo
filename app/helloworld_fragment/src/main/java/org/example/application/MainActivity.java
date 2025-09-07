package org.example.application;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements ItemListFragment.OnItemSelectedListener {
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if we're in two-pane mode
        View listContainer = findViewById(R.id.list_container);
        isTwoPane = (listContainer != null);

        if (isTwoPane) {
            setupTwoPaneLayout();
        } else {
            setupSinglePaneLayout();
        }
    }

    private void setupTwoPaneLayout() {
        FragmentManager fm = getFragmentManager();

        // Add list fragment to left pane
        ItemListFragment listFragment = new ItemListFragment();
        listFragment.setOnItemSelectedListener(this);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.list_container, listFragment);
        ft.commit();

        // Add default content fragment to right pane
        ContentFragment contentFragment = ContentFragment.newInstance(null);
        ft = fm.beginTransaction();
        ft.replace(R.id.content_container, contentFragment);
        ft.commit();
    }

    private void setupSinglePaneLayout() {
        FragmentManager fm = getFragmentManager();

        // Add list fragment as the main content
        ItemListFragment listFragment = new ItemListFragment();
        listFragment.setOnItemSelectedListener(this);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, listFragment);
        ft.commit();
    }

    @Override
    public void onItemSelected(String item) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (isTwoPane) {
            // Replace content fragment in right pane
            ContentFragment contentFragment = ContentFragment.newInstance(item);
            ft.replace(R.id.content_container, contentFragment);
        } else {
            // Replace entire fragment container with content fragment
            ContentFragment contentFragment = ContentFragment.newInstance(item);
            ft.replace(R.id.container, contentFragment);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (!isTwoPane && getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
