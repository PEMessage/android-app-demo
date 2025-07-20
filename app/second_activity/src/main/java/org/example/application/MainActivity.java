package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import android.widget.Toast;
import android.view.View;

import android.content.Intent;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setText("explict intent to start 2nd activity");
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class); // explict intent to start Activity
                startActivity(intent); // Declare in Context.startActivity
            }
        });

        Button button_2 = (Button)findViewById(R.id.button_2);
        button_2.setText("implict intent to start 2nd activity");
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("org.example.RUN_SEC_ACTIVITY"); // implict intent to start Activity
                startActivity(intent); // Declare in Context.startActivity
            }
        });
    }
}
