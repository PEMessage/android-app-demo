package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import android.content.Intent;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass
                // Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT).show(); 
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent); // Declare in Context.startActivity
            }
        });
    }
}
