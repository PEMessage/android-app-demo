package org.example.application;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        TextView textView = new TextView(this);
        textView.setText("Hello world");
        setContentView(textView);
    }
}
