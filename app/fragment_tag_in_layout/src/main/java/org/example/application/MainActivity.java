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

        var button_1 = (Button)findViewById(R.id.button_1); // If cast to error type, crash at runtime
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
