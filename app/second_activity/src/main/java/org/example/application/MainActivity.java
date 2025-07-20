package org.example.application;

import android.net.Uri;

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
        button_2.setText("implicit intent to start 2nd activity");
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("org.example.RUN_SEC_ACTIVITY"); // implicit intent to start Activity
                intent.addCategory("org.example.MY_CATEGORY");
                startActivity(intent); // Declare in Context.startActivity
            }
        });

        Button button_3 = (Button)findViewById(R.id.button_3);
        button_3.setText("predefine implicit Intent.ACTION_VIEW for data (https://www.baidu.com)");
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW); // predefine implicit
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button button_4 = (Button)findViewById(R.id.button_4);
        button_4.setText("predefine implicit Intent.ACTION_DAIL");
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL); // predefine implicit
                intent.setData(Uri.parse("tel:10086")); // no phone for wayland(so will crash, but work on real phone)
                startActivity(intent);
            }
        });
    }
}
