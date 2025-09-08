package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout); // Activity(View) - setContentView -> R.layout.XXX
    }
}
